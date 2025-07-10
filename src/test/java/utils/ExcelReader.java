package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.File;
import java.io.IOException;

public class ExcelReader {
    public static String[][] readDateRanges(String fileName) throws IOException {
        // Adjust the base path to point to 'src/test/java' directory
        File file = new File("src/test/java/" + fileName);

        if (!file.exists()) {
            throw new IOException("Excel file not found: " + file.getAbsolutePath());
        }

        FileInputStream fis = new FileInputStream(file);
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheetAt(0);

        int rows = sheet.getPhysicalNumberOfRows() - 1;
        int cols = sheet.getRow(0).getLastCellNum();

        String[][] data = new String[rows][cols];

        for (int i = 1; i <= rows; i++) {
            Row row = sheet.getRow(i);
            for (int j = 0; j < cols; j++) {
                Cell cell = row.getCell(j);
                data[i - 1][j] = (cell != null) ? cell.toString() : "";
            }
        }

        workbook.close();
        fis.close();
        return data;
    }
}
