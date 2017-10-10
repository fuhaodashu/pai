package com.pai.app.web.core.framework.excel;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.pai.base.core.util.date.DateConverter;

public abstract class AbstractXlsxOperator<T> {
	private FormulaEvaluator evaluator = null;  
	/**
	 * 数据默认从第二行开始。
	 * @param path
	 * @return
	 */
	public List<T> readXlsx(String path){
		List<T> results = new ArrayList<T>();
		try{
			XSSFSheet xssfSheet = readXssfSheet(path);
			List<XSSFRow> xssfRows = readXssfRows(xssfSheet);
			int len=xssfRows.size();
			for(int i=0;i<len;i++){
				T entity = convert(xssfRows.get(i));
				if(entity!=null)
					results.add(entity);				
			}
		}catch (IOException e) {
			e.printStackTrace();
		}		
		return results;
	}
	
	protected abstract T convert(XSSFRow xssfRow);
	
	protected String getValue(XSSFCell xssfCell) {
		try{
			switch (xssfCell.getCellType()){
			case	XSSFCell.CELL_TYPE_BOOLEAN:  
				return String.valueOf(xssfCell.getBooleanCellValue());
			case XSSFCell.CELL_TYPE_NUMERIC:
				if (HSSFDateUtil.isCellDateFormatted(xssfCell)) {
					Date d = xssfCell.getDateCellValue();
					if(d!=null){
						return DateConverter.toString(d);
					}
					return "";
				}else {
					return String.valueOf(xssfCell.getStringCellValue());				
				}
			case XSSFCell.CELL_TYPE_FORMULA:   
				evaluator.evaluateFormulaCell(xssfCell);
				return String.valueOf(xssfCell.getNumericCellValue());
			case XSSFCell.CELL_TYPE_STRING:
				try {
					String value = String.valueOf(xssfCell.getStringCellValue());				
					return value;	
				} catch (Exception e) {
					e.printStackTrace();
					return "";
				}		
			}
			return String.valueOf(xssfCell.getStringCellValue());
		}catch (Exception e) { 
			e.printStackTrace();
			return "";
		}
	}	
	
	protected Date getDateValue(XSSFCell xssfCell) {
		if (xssfCell.getCellType() == xssfCell.CELL_TYPE_NUMERIC) {
			if (HSSFDateUtil.isCellDateFormatted(xssfCell)) {
				Date d = xssfCell.getDateCellValue();
				return d;
			}	
		}
		return null;
	}
	
	private XSSFSheet readXssfSheet(String path) throws IOException {
		XSSFWorkbook xssfWorkbook = getXSSFWorkbook(path);
		evaluator = xssfWorkbook.getCreationHelper().createFormulaEvaluator();
		List<XSSFSheet> xssfSheets = getXSSFSheets(xssfWorkbook);
		return xssfSheets.get(0);
	}
	
	private XSSFSheet readXssfSheet(String path,int sheetNum) throws IOException {
		XSSFWorkbook xssfWorkbook = getXSSFWorkbook(path);
		List<XSSFSheet> xssfSheets = getXSSFSheets(xssfWorkbook);
		if(xssfSheets.size()>=sheetNum+1){
			return xssfSheets.get(sheetNum);
		}
		return xssfSheets.get(0);
	}
	
	private List<XSSFRow> readXssfRows(XSSFSheet xssfSheet){
		List<XSSFRow> xssfRows = new ArrayList<XSSFRow>();
		for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
			XSSFRow xssfRow = xssfSheet.getRow(rowNum);
			xssfRows.add(xssfRow);
		} 
		return xssfRows;
	} 
	
	private XSSFWorkbook getXSSFWorkbook(String path) throws IOException{
		InputStream is = new FileInputStream(path);
		XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
		return xssfWorkbook;
	}
	
	private List<XSSFSheet> getXSSFSheets(XSSFWorkbook xssfWorkbook){
		List<XSSFSheet> xssfSheets = new ArrayList<XSSFSheet>();
		for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
			XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
			if (xssfSheet == null) {
				continue;
			}
			xssfSheets.add(xssfSheet);
		}
		return xssfSheets;
	} 
}
