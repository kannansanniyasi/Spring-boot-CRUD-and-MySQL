package com.crud.app.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.crud.app.entity.UserDto;

public class ExcelGeneration {

	public static ByteArrayInputStream generateExcel(List<UserDto> userDtoList) {
		try(Workbook workbook = new XSSFWorkbook()){
			Sheet sheet = workbook.createSheet("user");
			
			Row row = sheet.createRow(0);
	        CellStyle headerCellStyle = workbook.createCellStyle();
	        headerCellStyle.setFillForegroundColor(IndexedColors.AQUA.getIndex());
	        headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	        // Creating header
	        Cell cell = row.createCell(0);
	        cell.setCellValue("Name");
	        cell.setCellStyle(headerCellStyle);
	        
	        cell = row.createCell(1);
	        cell.setCellValue("Email");
	        cell.setCellStyle(headerCellStyle);
	
	        cell = row.createCell(2);
	        cell.setCellValue("Phone");
	        cell.setCellStyle(headerCellStyle);
	        
	        // Creating data rows for each customer
	        for(int i = 0; i < userDtoList.size(); i++) {
	        	Row dataRow = sheet.createRow(i + 1);
	        	dataRow.createCell(0).setCellValue(userDtoList.get(i).getName());
	        	dataRow.createCell(1).setCellValue(userDtoList.get(i).getEmail());
	        	dataRow.createCell(2).setCellValue(userDtoList.get(i).getPhone());
	        }
	
	        // Making size of column auto resize to fit with data
	        sheet.autoSizeColumn(0);
	        sheet.autoSizeColumn(1);
	        sheet.autoSizeColumn(2);
	        sheet.autoSizeColumn(3);
	        
	        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	        workbook.write(outputStream);
	        return new ByteArrayInputStream(outputStream.toByteArray());
		} catch (IOException ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
}
