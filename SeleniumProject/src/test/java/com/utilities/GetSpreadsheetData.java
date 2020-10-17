package com.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class GetSpreadsheetData {
	
	public static XSSFWorkbook wb;
	public static XSSFSheet sheet;
	public FileInputStream fis= null;
	public FileOutputStream fileOut= null;
	public XSSFRow row = null;
	public XSSFCell cell = null;
	public String filePath;
	
	public GetSpreadsheetData(String excelPath) throws IOException {  //contractor
		try {
		filePath= excelPath;
		File file = new File (this.filePath);
		
			FileInputStream fis = new FileInputStream(file);
			
				wb= new XSSFWorkbook(fis);
			
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		
	}
	
	// Function to get string cell data from excel
	
	public String getCellData(String sheetName, int row,String columnName) {
		sheet =wb.getSheet(sheetName);
		XSSFRow rowHeader = wb.getSheet(sheetName).getRow(0);
		int col = 0 ;
		for(int i=0;i<rowHeader.getLastCellNum();i++) {
			if(rowHeader.getCell(i).getStringCellValue().equals(columnName)) {
				col=i;
				break;
			}
		}
		String data= sheet.getRow(row).getCell(col).getStringCellValue();
		return data;
				
			}
		
		
		//Function to count the row in excel
	
	public int getRowCount(String sheetName) {
		
		int row = wb.getSheet(sheetName).getLastRowNum();
		row = row +1;
		return row;
		
	}
	
	// Get numeric cell data from excel
	public int getNumericCellData (String sheetName, int row, int columnName) {
		sheet =wb.getSheet(sheetName);
		XSSFRow rowHeader = wb.getSheet(sheetName).getRow(0);
		int col = 0 ;
		for(int i=0;i<rowHeader.getLastCellNum();i++) {
			if(rowHeader.getCell(i).getStringCellValue().equals(columnName)) {
				col=i;
				break;
			}
		}
		int data= (int)sheet.getRow(row).getCell(col).getNumericCellValue();
		return data;
		
	}
	
	

}
