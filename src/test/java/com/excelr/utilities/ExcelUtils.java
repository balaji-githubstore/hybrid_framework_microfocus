package com.excelr.utilities;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class ExcelUtils {

    public static Object[][] getSheetIntoTwoDimensionalArray(String fileDetail, String sheetName) throws IOException {

        FileInputStream file = new FileInputStream(fileDetail);
        XSSFWorkbook book = new XSSFWorkbook(file);
        XSSFSheet sheet = book.getSheet(sheetName);

        int rowCount = sheet.getPhysicalNumberOfRows();
        int cellCount = sheet.getRow(0).getPhysicalNumberOfCells();

        Object[][] data = new Object[rowCount - 1][cellCount];
        DataFormatter format = new DataFormatter();

        for (int r = 1; r < rowCount; r++) {
            for (int c = 0; c < cellCount; c++) {
                //row and cell index
                String value = format.formatCellValue(sheet.getRow(r).getCell(c));
                data[r - 1][c] = value;
            }
        }

        book.close();
        file.close();

        return data;
    }
}
