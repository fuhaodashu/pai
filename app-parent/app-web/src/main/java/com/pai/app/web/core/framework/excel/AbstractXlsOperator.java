package com.pai.app.web.core.framework.excel;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.functions.T;

public abstract class AbstractXlsOperator<T> {
	public List<T> readXls(String path){
		List<T> results = new ArrayList<T>();
		try{
			HSSFSheet hssfSheet = readHssfSheet(path);
			List<HSSFRow> hssfRows = readHssfRows(hssfSheet);
			for(HSSFRow hssfRow:hssfRows){
				T entity = convert(hssfRow);
				if(entity!=null)
					results.add(entity);
			}
		}catch (IOException e) {
			e.printStackTrace();
		}		
		return results;
	}
	
	protected abstract T convert(HSSFRow hssfRow);
	
	private HSSFSheet readHssfSheet(String path) throws IOException {
		HSSFWorkbook hssfWorkbook = getHSSFWorkbook(path);
		List<HSSFSheet> hssfSheets = getHSSFSheets(hssfWorkbook);
		return hssfSheets.get(0);
	}
	
	private HSSFSheet readHssfSheet(String path,int sheetNum) throws IOException {
		HSSFWorkbook hssfWorkbook = getHSSFWorkbook(path);
		List<HSSFSheet> hssfSheets = getHSSFSheets(hssfWorkbook);
		if(hssfSheets.size()>=sheetNum+1){
			return hssfSheets.get(sheetNum);
		}
		return hssfSheets.get(0);
	}
	
	private List<HSSFRow> readHssfRows(HSSFSheet hssfSheet){
		List<HSSFRow> hssfRows = new ArrayList<HSSFRow>();
		for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
			HSSFRow hssfRow = hssfSheet.getRow(rowNum);
			hssfRows.add(hssfRow);
		} 
		return hssfRows;
	} 
	
	private HSSFWorkbook getHSSFWorkbook(String path) throws IOException{
		InputStream is = new FileInputStream(path);
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
		return hssfWorkbook;
	}
	
	private List<HSSFSheet> getHSSFSheets(HSSFWorkbook hssfWorkbook){
		List<HSSFSheet> hssfSheets = new ArrayList<HSSFSheet>();
		for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
			HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
			if (hssfSheet == null) {
				continue;
			}
			hssfSheets.add(hssfSheet);
		}
		return hssfSheets;
	} 
}
