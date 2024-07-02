package com.example.students.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;
import com.example.students.entity.Student;

public class ExcelUploader {
    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    static String SHEET = "student";

    public static boolean hasExcelFormat(MultipartFile file) {
        if (!TYPE.equals(file.getContentType())) {
            return false;
        }
        return true;
    }
    public static List<Student> excelToStudentList(InputStream is) {
        try {
            Workbook workbook = new XSSFWorkbook(is);
            Sheet sheet = workbook.getSheet(SHEET);
            Iterator<Row> rows = sheet.iterator();
            List<Student> studentList = new ArrayList<Student>();
            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }
                Iterator<Cell> cellsInRow = currentRow.iterator();
                Student student = new Student();
                int cellIndex = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();
                    switch (cellIndex) {
                        case 1:
                            student.setStudentName(currentCell.getStringCellValue());
                            break;
                        case 2:
                            student.setStudentClass(currentCell.getStringCellValue());
                            break;
                        case 3:
                            student.setStudentSubject(currentCell.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                    cellIndex++;
                }
                studentList.add(student);
            }
            workbook.close();
            return studentList;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }
}

