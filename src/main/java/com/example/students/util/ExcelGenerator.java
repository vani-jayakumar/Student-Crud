package com.example.students.util;

import com.example.students.entity.Student;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelGenerator {
    private List < Student > studentList;
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    public ExcelGenerator(List < Student > studentList) {
        this.studentList = studentList;
        workbook = new XSSFWorkbook();
    }
    private void writeHeader() {
        sheet = workbook.createSheet("Student");
        Row row = sheet.createRow(0);
        createCell(row, 0, "ID");
        createCell(row, 1, "Name");
        createCell(row, 2, "Class");
        createCell(row, 3, "Subject");
        createCell(row, 4, "Email");
    }
    private void createCell(Row row, int columnCount, Object valueOfCell) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (valueOfCell instanceof Integer) {
            cell.setCellValue((Integer) valueOfCell);
        } else if (valueOfCell instanceof Long) {
            cell.setCellValue((Long) valueOfCell);
        } else if (valueOfCell instanceof String) {
            cell.setCellValue((String) valueOfCell);
        } else {
            cell.setCellValue((Boolean) valueOfCell);
        }
    }
    private void write() {
        int rowCount = 1;
        for (Student record: studentList) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
            createCell(row, columnCount++, record.getStudentId());
            createCell(row, columnCount++, record.getStudentName());
            createCell(row, columnCount++, record.getStudentClass());
            createCell(row, columnCount++, record.getStudentSubject());
            createCell(row, columnCount++, record.getStudentEmail());
        }
    }
    public void generateExcelFile(HttpServletResponse response) throws IOException {
        writeHeader();
        write();
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }
}