package com.test.arvato.ArvatoUIAutomation.excelReader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

	public String path;
	public FileOutputStream fous = null;
	public FileInputStream fis;
	public XSSFWorkbook workbook ;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	
	public ExcelReader(String path){
		this.path = path;
		try {
			fis = new FileInputStream(path);
			workbook = new XSSFWorkbook(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings({ "deprecation", "static-access" })
	public String[][] getDataFromSheet(String excelFileName, String sheetName){
		String dataSets[][] = null;
		try {
			XSSFSheet sheet = workbook.getSheet(sheetName);
			int totalRow = sheet.getLastRowNum()+1;
			int totalColumn = sheet.getRow(0).getLastCellNum();
			dataSets = new String[totalRow-1][totalColumn];
			for(int i =1;i<totalRow;i++){
				XSSFRow rows = sheet.getRow(i);
				for(int j=0;j<totalColumn;j++){
					XSSFCell cell = rows.getCell(j);
					if(cell.getCellType() == cell.CELL_TYPE_STRING)
						dataSets[i-1][j] = cell.getStringCellValue();
					else if(cell.getCellType() == cell.CELL_TYPE_NUMERIC){
						String cellText = String.valueOf(cell.getNumericCellValue());
						dataSets[i-1][j] = cellText;
					}else
						dataSets[i-1][j] = String.valueOf(cell.getBooleanCellValue());
				}
			} return dataSets;
			
		} catch (Exception e) {
			System.out.println("Exception in Excel Reader"+ e.getMessage());
			e.printStackTrace();
		}
		return dataSets;	
	}
	
	@SuppressWarnings({ "deprecation", "static-access" })
	public String getCellData(String sheetName, String colName, int rowNum){
		try {
			int col_Num = 0;
			int index = workbook.getSheetIndex(sheetName);
			sheet = workbook.getSheetAt(index);
			XSSFRow row = sheet.getRow(0);
			for(int i =0;i<row.getLastCellNum();i++){
				if(row.getCell(i).getStringCellValue().equals(colName)){
					col_Num = i;
					break;
				}
			}
			row = sheet.getRow(rowNum-1);
			XSSFCell cell = row.getCell(col_Num);
			if(cell.getCellType() == cell.CELL_TYPE_STRING)
				return cell.getStringCellValue();
			else if(cell.getCellType() == cell.CELL_TYPE_BLANK){
				return "";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}return null;
	}
}
