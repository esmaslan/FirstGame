package com.example.FirstGame;


import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ExcelReader {

    public void readExcelFile(String filePath) {
        try {
            FileInputStream excelFile = new FileInputStream(new File(filePath));
            Workbook workbook = WorkbookFactory.create(excelFile);

            // İlk sayfayı al
            Sheet sheet = workbook.getSheetAt(0);

            // Her bir satırı döngü ile al
            for (Row row : sheet) {
                // Her bir hücreyi döngü ile al
                for (Cell cell : row) {
                    switch (cell.getCellType()) {
                        case STRING:
                            System.out.print(cell.getStringCellValue() + "\t");
                            break;
                        case NUMERIC:
                            System.out.print(cell.getNumericCellValue() + "\t");
                            break;
                        case BOOLEAN:
                            System.out.print(cell.getBooleanCellValue() + "\t");
                            break;
                        case BLANK:
                            System.out.print("BLANK\t");
                            break;
                        default:
                            System.out.print("DEFAULT\t");
                    }
                }
                System.out.println(); // Her satırın sonunda bir alt satıra geç
            }

            workbook.close();
            excelFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ExcelReader reader = new ExcelReader();
        reader.readExcelFile("java/com/example/FirstGame/words.xlsx");
    }
}
