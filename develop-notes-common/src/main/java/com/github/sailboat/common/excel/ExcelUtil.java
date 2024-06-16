package com.github.sailboat.common.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.poi.ss.usermodel.*;

public class ExcelUtil {

    /**
     * 导
     */
    public void importExcel(File file) throws IOException {

        FileInputStream inputStream = new FileInputStream(file);

        Workbook wb = WorkbookFactory.create(inputStream);

        int countSheets = wb.getNumberOfSheets();

        for (int numSheet = 0; numSheet < countSheets; numSheet++) {
            Sheet hssfSheet = wb.getSheetAt(numSheet);
            if (hssfSheet == null) {
                continue;
            }
            // 循环行Row
            for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
                Row hssfRow = hssfSheet.getRow(rowNum);
                if (hssfRow == null) {
                    continue;
                }

                Iterator<Cell> iterator = hssfRow.iterator();

                while (iterator.hasNext()) {
                    Cell content = iterator.next();
                    System.out.println(content.getStringCellValue());
                }

                // 第一行数据
                // Cell content=hssfRow.getCell(1);
                // 第二行数据
                // ....以此类推
            }
        }
    }

    public void exportExcel() {}


//    public static void main(String[] args) {
//        try {
//            new ExcelUtil().importExcel(new File("D:\\手机型号打标测试.xlsx"));
//        } catch (IOException e) {
//            System.out.println(ExceptionUtils.getMessage(e));
//        }
//    }

}
