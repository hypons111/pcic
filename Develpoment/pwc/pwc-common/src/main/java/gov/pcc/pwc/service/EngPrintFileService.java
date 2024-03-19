package gov.pcc.pwc.service;

import com.deepoove.poi.XWPFTemplate;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import gov.pcc.pwc.common.enums.eng.ApplyItemEnum;
import gov.pcc.pwc.common.enums.eng.ApplyTypeEnum;
import gov.pcc.pwc.common.web.io.DownloadableResource;
import gov.pcc.pwc.service.dto.EngPrintFileDTO;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service
public class EngPrintFileService {

//    private final String tempDir = "D:\\Git\\repo\\Develpoment\\pwc\\pwc-web\\src\\main\\resources\\templates\\ENG\\套印函稿\\證書";
    private final String tempDir = ResourceUtils.getURL("classpath:").getPath() + "\\templates\\ENG\\套印函稿\\證書";
    private final String targetDir = "D:\\STSDAT\\pwc\\ENG\\ENG_ENGR_CERTIFICATE\\";

    public EngPrintFileService() throws FileNotFoundException {
    }

    public DownloadableResource getPrintFile(@NotNull EngPrintFileDTO engPrintFileDTO) throws IOException, TemplateException {
        List<String> docxList = new ArrayList<>();
        List<String> diList = new ArrayList<>();

        if(engPrintFileDTO.getApplyItems().contains(ApplyItemEnum.NEW_APPLY.getCode())) {
            docxList.add("證書套印.docx");
            if(Objects.equals(engPrintFileDTO.getApplyType(), ApplyTypeEnum.PAPER_APPLY.getCode())) {
                docxList.add("證書函_紙本申請用.docx");
                diList.add("證書函_紙本申請用.di");
            }else if(Objects.equals(engPrintFileDTO.getApplyType(), ApplyTypeEnum.ONLINE_APPLY.getCode())) {
                docxList.add("證書函_線上申請用.docx");
                diList.add("證書函_線上申請用.di");
            }
        }else if(engPrintFileDTO.getApplyItems().contains(ApplyItemEnum.REISSUE.getCode())) {
            docxList.add("補發證書套印.docx");
            if(Objects.equals(engPrintFileDTO.getApplyType(), ApplyTypeEnum.PAPER_APPLY.getCode())) {
                docxList.add("證書函_紙本申請用.docx");
                diList.add("證書函_紙本申請用.di");
            }else if(Objects.equals(engPrintFileDTO.getApplyType(), ApplyTypeEnum.ONLINE_APPLY.getCode())) {
                docxList.add("證書函_線上申請用.docx");
                diList.add("證書函_線上申請用.di");
            }
        }else if(engPrintFileDTO.getApplyItems().contains(ApplyItemEnum.APPLY_EN.getCode())) {
            docxList.add("英文證明書.docx");
            docxList.add("證書函_英文證書用.docx");
            diList.add("證書函_英文證書用.di");
        }

        ObjectMapper objectMapper = new ObjectMapper();
//        TypeReference<Map<String, String>> mapType = new TypeReference<>(){};
//        Map<String, String> map = objectMapper.convertValue(engPrintFileDTO, mapType);
        Map<String, String> map = objectMapper.convertValue(engPrintFileDTO, Map.class);
        makeDocx(map, docxList);
        makeDi(map, diList);

        File zipFile = makeZip(map);
        Path zipPath = Paths.get(zipFile.getAbsolutePath());
        final ByteArrayResource fileResource = new ByteArrayResource(Files.readAllBytes(zipPath));
        return new DownloadableResource(fileResource, zipFile.getName());
    }

    public void makeDocx(Map<String, String> map, List<String> docxList) throws IOException {
        String pk = map.get("id") + "_" + map.get("version");
        Path path = Paths.get(targetDir + pk + File.separator + "套印" + File.separator + "temp");
        if(!Files.exists(path)) Files.createDirectories(path);

        for (String docxFileName : docxList) {
            XWPFTemplate temp = XWPFTemplate.compile(tempDir + File.separator + docxFileName).render(map);
            String newFileName = map.get("receiveNo") + "_" + map.get("chName") + "_" + map.get("chApplyItemsList") + "_" + docxFileName;
            temp.writeAndClose(new FileOutputStream(path + File.separator + newFileName));
        }
    }

    public void makeDi(Map<String, String> map, List<String> diList) throws IOException, TemplateException {
        String pk = map.get("id") + "_" + map.get("version");
        Path path = Paths.get(targetDir + pk + File.separator + "套印" + File.separator + "temp");
        if(!Files.exists(path)) Files.createDirectories(path);

        Configuration configuration = new Configuration(Configuration.VERSION_2_3_31);
        configuration.setDirectoryForTemplateLoading(new File(tempDir));
        for(String diFileName : diList) {
            Template template = configuration.getTemplate(diFileName);
            String newFileName = map.get("receiveNo") + "_" + map.get("chName") + "_" + map.get("chApplyItemsList") + "_" + diFileName;
            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path + File.separator + newFileName)));
            template.process(map, out);
            out.close();
        }
    }

    public File makeZip(Map<String, String> map) throws IOException {
        String pk = map.get("id") + "_" + map.get("version");
        Path path = Paths.get(targetDir + pk + File.separator + "套印");
        if(!Files.exists(path)) Files.createDirectories(path);

        String zipFileName = "技師證書_" + map.get("id") +".zip";
        File zipFile = new File(path + File.separator + zipFileName);
        ZipOutputStream zipOutput = new ZipOutputStream(new FileOutputStream(zipFile));
        File dir = new File(path + File.separator + "temp");
        File[] files = dir.listFiles();
        if(files != null) {
            for (File file : files) {
                InputStream input = new BufferedInputStream(new FileInputStream(file));
                zipOutput.putNextEntry(new ZipEntry(file.getName()));
                byte[] b = new byte[4 * 1024];
                int len;
                while((len = input.read(b)) != -1) {
                    zipOutput.write(b, 0, len);
                }
                input.close();
            }
        }
        zipOutput.close();

        Files.walk(dir.toPath())
            .sorted(Comparator.reverseOrder())
            .forEach(p -> {
                try {
                    Files.delete(p);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

        return zipFile;
    }
}
