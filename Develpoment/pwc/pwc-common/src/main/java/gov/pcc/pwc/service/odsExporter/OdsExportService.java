package gov.pcc.pwc.service.odsExporter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class OdsExportService {
    HSSFRow row;

    HSSFWorkbook workbook = new HSSFWorkbook();

    HSSFSheet spreadsheet = workbook.createSheet("sheet1");

    public void exportByParam(Map<Integer, List<String>> paramMap, String path, String fileName)
            throws IOException {
        // 展開取得map內容
        paramMap.forEach((k, v) -> {
            // 取得輸入列
            row = spreadsheet.createRow(k);
            List<String> contentList = paramMap.get(k);
            int cellId = 0;
            for (String contentElement : contentList) {
                // 取得輸入儲存格
                Cell cell = row.createCell(cellId++);
                cell.setCellValue(contentElement);
            }
        });

        File file = new File(path + fileName + ".xls");
        FileOutputStream destination = new FileOutputStream(file);
        workbook.write(destination);
        destination.close();
        File fileToOds = new File(path + fileName + ".ods");
        file.renameTo(fileToOds);
    }

    public static void exportExcel(HSSFWorkbook workbook, int sheetNum,
                                   String sheetTitle, String[] headers, List<List<String>> result,
                                   OutputStream out, Integer maxColumnWidth, List<Integer> columnWidth) {

//        匯出Excel的寬度係數
        int widthFactor = 256;

        //預設最大長度20個字
        if(maxColumnWidth==null){
            maxColumnWidth=20;
        }
        // 生成一個表格
        HSSFSheet sheet = workbook.createSheet();
        workbook.setSheetName(sheetNum, sheetTitle);
        // 生成一個樣式
        HSSFCellStyle style = workbook.createCellStyle();
        // 設定這些樣式
        HSSFFont font = workbook.createFont();
        font.setFontHeightInPoints((short) 14);
        font.setFontName("宋體");
        font.setBold(true);
        style.setFont(font);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setAlignment(HorizontalAlignment.CENTER);

        Map<Integer,Integer> maxWidth = new HashMap<>(headers.length);
        // 產生表格標題行
        HSSFRow row = sheet.createRow(0);
        for (int i = 0; i < headers.length; i++) {
            HSSFCell cell = row.createCell((short) i);
            cell.setCellStyle(style);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text.toString());
            maxWidth.put(i,text.toString().getBytes().length*widthFactor+400);
        }
        // 遍歷集合資料，產生資料行
        if (result != null) {
//            記錄 需要合併的單元格
            List<Integer> hbList = new ArrayList<>();

            int index = 1;
            HSSFCellStyle styleContent = workbook.createCellStyle();
            // 設定這些樣式
            HSSFFont fontContent = workbook.createFont();
            fontContent.setFontHeightInPoints((short) 12);
            fontContent.setFontName("宋體");
            styleContent.setFont(fontContent);
            styleContent.setWrapText(true);
            styleContent.setVerticalAlignment(VerticalAlignment.CENTER);
            styleContent.setAlignment(HorizontalAlignment.CENTER);

            for (List<String> m : result) {

//                當空值時，記錄 需要合併的單元格
                if(StringUtils.isBlank(m.get(1))){
                    hbList.add(index);
                }

                row = sheet.createRow(index);
                int cellIndex = 0;
                for (String str : m) {

                    HSSFCell cell = row.createCell((short) cellIndex);
                    cell.setCellStyle(styleContent);
                    cell.setCellValue(str);
                    int length = 0;
                    if(StringUtils.isNotBlank(str)){
                        length = str.getBytes().length  * widthFactor;
                    }
                    length = length + 400;
                    if (length>(maxColumnWidth*(widthFactor*3))){
                        length = (maxColumnWidth*(widthFactor*3));
                    }
                    maxWidth.put(cellIndex,Math.max(length,maxWidth.get(cellIndex)));
                    cellIndex++;
                }

                index++;
            }

//            迴圈合併單元格
            if(CollectionUtils.isNotEmpty(hbList)){
                for (Integer integer : hbList){
                    CellRangeAddress region = new CellRangeAddress(integer, integer, 0, 6);
                    sheet.addMergedRegion(region);
                }
            }

        }
        for (int i= 0; i<headers.length;i++){
            if(CollectionUtils.isNotEmpty(columnWidth)){
                sheet.setColumnWidth(i,columnWidth.get(i)*(widthFactor*3));
            }else {
                sheet.setColumnWidth(i,maxWidth.get(i));
            }
        }
    }
}
