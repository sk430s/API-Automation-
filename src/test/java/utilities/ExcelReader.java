package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {
	//Workbook workbook;

	Sheet xlsheet=null;
	Workbook workbook=null;

	
	Logger logger;

	public ExcelReader() {
		
	}

	public ExcelReader(String filePath, String fileName, String sheetname) throws Exception {
		workbook = this.getWorkbook(filePath, fileName);
		xlsheet = workbook.getSheet(sheetname);
		logger = Logs.getLogger("ExcelReader");
	}

	public Workbook getWorkbook(String filePath, String fileName) throws Exception {
		
		String fullPath = System.getProperty("user.dir") + filePath + "//" + fileName;
		File file = new File(fullPath);
		FileInputStream fis = new FileInputStream(file);
		String fileType = fileName.substring(fileName.indexOf(".")+1);
		if (fileType.equalsIgnoreCase("xls")) {
			workbook = new HSSFWorkbook(fis);
		} else if (fileType.equalsIgnoreCase("xlsx")) {
			workbook = new XSSFWorkbook(fis);
		}
		
		return workbook;
	}
	
	public Sheet getSheet(Workbook workbook, String sheetname) {
		xlsheet = workbook.getSheet(sheetname);
		return xlsheet;
	}

	public Sheet getSheet(String filePath, String fileName, String sheetname) throws Exception {
		workbook = this.getWorkbook(filePath, fileName);
		xlsheet = workbook.getSheet(sheetname);
		return xlsheet;
	}

	public String getCellData(Sheet sheet, int RowNum, int ColNum) throws Exception {
		try {
			Cell cell = sheet.getRow(RowNum).getCell(ColNum);
			String CellData = cell.getStringCellValue();
			return CellData;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	public String getCellData(int RowNum, int ColNum) throws Exception {
		try {
			Cell cell = xlsheet.getRow(RowNum).getCell(ColNum);
			String CellData = cell.getStringCellValue();
			return CellData;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	public String getCellData(String filePath, String fileName, String sheetname, int RowNum, int ColNum) throws Exception {
		try {
			Sheet sheet = getSheet(filePath, fileName, sheetname);
			Cell cell = sheet.getRow(RowNum).getCell(ColNum);
			String CellData = cell.getStringCellValue();
			return CellData;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	
	public int getLastRowOfColumn(Sheet sheet, int columnIndex) {
		int lastRow = sheet.getLastRowNum();
		while (lastRow >= 0 && xlsheet.getRow(lastRow).getCell(columnIndex).getStringCellValue().isEmpty()) {
			lastRow--;
		}
		return lastRow;
	}
	
	
	
	public ArrayList<String> getColData(int columnIndex) {
		int lastRow = getLastRowOfColumn(xlsheet, columnIndex);
		ArrayList<String> arrList = new ArrayList<String>();
		for (int i=1; i<=lastRow; i++) {
			String Data = xlsheet.getRow(i).getCell(columnIndex).getStringCellValue();
			//String Data = xlsheet.getRow(i).getCell(columnIndex).getce
			logger.info(Data);
			arrList.add(Data);
		}
		return arrList;
	}
	
	// returns the row count in a sheet
	public int getRowCount(String sheetName){
		int index = workbook.getSheetIndex(sheetName);
		if(index==-1)
			return 0;
		else{
			xlsheet = workbook.getSheetAt(index);
			int number=xlsheet.getLastRowNum()+1;
			return number;
		}
		
	}

}
