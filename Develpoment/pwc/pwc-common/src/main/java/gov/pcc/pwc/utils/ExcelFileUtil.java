package gov.pcc.pwc.utils;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ExcelFileUtil {


    public static String parseFlieListData(List<MultipartFile> uploadFile) throws Exception {
        String fileData = "";
        try {
            MultipartFile multipartFile = uploadFile.get(0);
            fileData = getFlieListData(multipartFile);
        } catch (Exception e1) {
            throw new RuntimeException("檔案取得失敗, 錯誤原因: " + e1.getMessage(), e1);
        }

        return fileData;
    }

    public static String getFlieListData(MultipartFile uploadFile) throws Exception {
        final String dir = "D:\\STSDAT\\pwc\\TempFile\\";
        String fileListDataStr = "";
        InputStream file = null;
        String fe = "";
        //檔案名稱
        String fileName = uploadFile.getOriginalFilename();
        //取得副檔名
        if (fileName.contains(".")) {
            fe = fileName.substring(fileName.lastIndexOf(".") + 1);
        }
        file = uploadFile.getInputStream();

        //調整ODS檔名為XLS
        if("ods".equals(fe)){
            Path path = Paths.get(dir);
            File file1 = new File(path + File.separator + fileName);
            //檢查有無資料夾 沒有則建立
            if (!file1.exists()  && !file1.isDirectory()) {
                file1.mkdir();
            }
            File fileToXls = new File(path + File.separator +  fileName.substring(0,fileName.lastIndexOf(".")) + ".xls");
            file1.renameTo(fileToXls);
            uploadFile.transferTo(fileToXls);
            //檔案名稱
            String fileName1 = fileToXls.getName();
            //取得副檔名
            if (fileName1.contains(".")) {
                fe = fileName1.substring(fileName1.lastIndexOf(".") + 1);
            }
            file = new FileInputStream(fileToXls);
        }

        //EXCEL
        if ("xls".equals(fe) || "xlsx".equals(fe)) {

            // 工作表
            Workbook workbook = WorkbookFactory.create(file);
            // 表個數。
            int numberOfSheets = workbook.getNumberOfSheets();
            // 遍歷表。
            for (int i = 0; i < numberOfSheets; i++) {
                Sheet sheet = workbook.getSheetAt(i);
                // 行數。
                int rowNumbers = sheet.getLastRowNum() + 1;
                // Excel第一行。
                Row temp = sheet.getRow(0);
                if (temp == null) {
                    continue;
                }
                int cells = temp.getPhysicalNumberOfCells();

                // 讀資料。
                for (int row = 0; row < rowNumbers; row++) {
                    String sheetData = "";
                    Row r = sheet.getRow(row);
                    for (int col = 0; col < cells; col++) {
                        sheetData = sheetData + r.getCell(col).toString().replaceAll("\r|\n", " ") + " ";
                    }
                    fileListDataStr = fileListDataStr + sheetData +"\n";
                }
            }
        }
//        System.out.println(fileListDataStr);
        return fileListDataStr;
    }
}