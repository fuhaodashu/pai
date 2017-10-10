
package com.pai.app.web.core.framework.excel;

import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFComment;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/** 
 * @ClassName ExportExcel  
 * @Description TODO 
 *   
 */

public class ExcelUtil<T> extends AbstractXlsOperator<T>{
	
	private static Logger log = LoggerFactory.getLogger(ExcelUtil.class);
	
	public void exportExcel(Collection<T> dataset, OutputStream out) {
        exportExcel("EXCEL文档", null, dataset, out, "yyyy-MM-dd");
    }

    public void exportExcel(String[] headers, Collection<T> dataset,OutputStream out) {
        exportExcel("EXCEL文档", headers, dataset, out, "yyyy-MM-dd");
    }

    public void exportExcel(String[] headers, Collection<T> dataset,OutputStream out, String pattern) {
        exportExcel("EXCEL文档", headers, dataset, out, pattern);
    }
    public void exportExcelHeadersAndFieldNames(String[] filters, String[] headers,Integer[]widths,String fieldNames[], Collection<T> dataset,OutputStream out) {
    	exportExcelHeadersAndFieldNames("EXCEL文档",filters, headers, widths, fieldNames,dataset, out, "yyyy-MM-dd");
    }
    public void exportExcelHeadersAndFieldNames(String[] filters, String[] headers,Integer[]widths,String fieldNames[], Collection<T> dataset,OutputStream out, String pattern) {
    	exportExcelHeadersAndFieldNames("EXCEL文档",filters, headers, widths, fieldNames,dataset, out, pattern);
    }
    
	/**
	 * 生成excel文件
	 * @param fliePath 生成的文件路径
	 * @param sheetName sheet名称
	 * @param list 数据集
	 * description TODO
	 * author chenqian 
	 * date 2012-6-6 上午11:38:45
	*/
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void exportExcel(String sheetName,String[] headers,Collection<T> dataset,OutputStream os,String formatter) {
		// 工作簿
		XSSFWorkbook hssfworkbook = new XSSFWorkbook();
		// 创建sheet页
		XSSFSheet hssfsheet = hssfworkbook.createSheet();
		// 设置sheet名称
		if (StringUtils.isBlank(sheetName)) { // 设置sheet的名字
			hssfworkbook.setSheetName(0, "sheet1");
		} else {
			hssfworkbook.setSheetName(0, sheetName);
		}
		//设置表格默认列宽度为15个字节
		hssfsheet.setDefaultColumnWidth((short) 20);
		//字体  
		XSSFFont font = hssfworkbook.createFont();  
		font.setColor(HSSFColor.VIOLET.index);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font.setFontHeightInPoints((short)12); //设置字体大小
		
		// 生成一个样式
		XSSFCellStyle cellStyle = hssfworkbook.createCellStyle();
		
		cellStyle.setFillForegroundColor(HSSFColor.BLUE.index);
		cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cellStyle.setFont(font);
		
		cellStyle.setWrapText(true);       
		
		// 生成并设置另一个样式
		XSSFCellStyle style2 = hssfworkbook.createCellStyle();
        style2.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
        style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        // 生成另一个字体
        XSSFFont font2 = hssfworkbook.createFont();
        font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
        // 把字体应用到当前的样式
        style2.setFont(font2);
		
        // 声明一个画图的顶级管理器
        XSSFDrawing patriarch = hssfsheet.createDrawingPatriarch();
        // 定义注释的大小和位置,详见文档
        XSSFComment comment = patriarch.createCellComment(new XSSFClientAnchor(0,0, 0, 0, (short) 4, 2, (short) 6, 5));
        // 设置注释作者，当鼠标移动到单元格上是可以在状态栏中看到该内容.
        comment.setAuthor("通联支付");
        
        // 产生表格标题行
        XSSFRow row = hssfsheet.createRow(0);
        if(headers != null){
        	for (short i = 0; i < headers.length; i++) {
            	XSSFCell cell = row.createCell(i);
            	hssfsheet.setColumnWidth(i, headers[i].getBytes().length*2*256);
                cell.setCellStyle(cellStyle);
                XSSFRichTextString text = new XSSFRichTextString(headers[i]);
                cell.setCellValue(text);
            }
        }
        
		// 写入所有内容行
        Iterator<T> it = dataset.iterator();
        int index = 0;
        while (it.hasNext()) {
        	 index++;
             row = hssfsheet.createRow(index);
             T t = (T) it.next();
             List<Object>list = new ArrayList<Object>();
             if(t instanceof Map){
            	Map<String, Object> obj = (Map<String, Object>) t;
     			Set<Map.Entry<String, Object>> set1 = obj.entrySet();
     	        for (Iterator<Map.Entry<String, Object>> it1 = set1.iterator(); it1.hasNext();) {
     	        	 Map.Entry<String, Object> entry = (Map.Entry<String, Object>) it1.next();
     	        	list.add(entry.getValue());
     	        }
             }else{ // 利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值
            	 Field[] fields = t.getClass().getDeclaredFields();
                 for (short i = 0; i < fields.length; i++) {
                	 Field field = fields[i];
                  	 String fieldName = field.getName();
                  	 String getMethodName = "get"+ fieldName.substring(0, 1).toUpperCase()+ fieldName.substring(1);
                  	 Class tCls = t.getClass();
                     Object value = null;
                    try {
     					Method getMethod = tCls.getMethod(getMethodName, new Class[] {});
     					value = getMethod.invoke(t, new Object[] {});
     				} catch (SecurityException e) {
     					e.printStackTrace();
     				} catch (NoSuchMethodException e) {
     					e.printStackTrace();
     				} catch (IllegalArgumentException e) {
     					e.printStackTrace();
     				} catch (IllegalAccessException e) {
     					e.printStackTrace();
     				} catch (InvocationTargetException e) {
     					e.printStackTrace();
     				}
                    list.add(value);
                 }
             }
             for(short i = 0; i < list.size(); i++){
            	Object obj =  list.get(i);
            	XSSFCell cell = row.createCell(i);
              	cell.setCellStyle(style2);
              	 String textValue = null;
              	 if(obj == null){
              		textValue = "";
              	 }else if (obj instanceof Date){
                	 Date date = (Date) obj;
                	 SimpleDateFormat sdf = new SimpleDateFormat(formatter);
                	 textValue = sdf.format(date);
                 }else if (obj instanceof Boolean){
                	 boolean bValue = (Boolean) obj;
                	 if(bValue)
                		 textValue="是";
                	 else
                		 textValue="否";
                 } else if (obj instanceof byte[]) {
                     // 有图片时，设置行高为60px;
                     row.setHeightInPoints(60);
                     // 设置图片所在列宽度为80px,注意这里单位的一个换算
                     hssfsheet.setColumnWidth(i, (short) (35.7 * 80));
                     // sheet.autoSizeColumn(i);
                     byte[] bsValue = (byte[]) obj;
                     XSSFClientAnchor anchor = new XSSFClientAnchor(0, 0,1023, 255, (short) 6, index, (short) 6, index);
                     anchor.setAnchorType(2);
                     patriarch.createPicture(anchor, hssfworkbook.addPicture( bsValue, XSSFWorkbook.PICTURE_TYPE_JPEG));
                 } else {
                     // 其它数据类型都当作字符串简单处理
                     textValue = obj.toString();
                 }
                 
                 // 如果不是图片数据，就利用正则表达式判断textValue是否全部由数字组成
                 if (textValue != null) {
                	 /*if(FuncUtil.isNumber(textValue)){
                		 // 是数字当作double处理
                         cell.setCellValue(Double.parseDouble(textValue));
                	 }else{*/
                		 XSSFRichTextString richString = new XSSFRichTextString(textValue);
                         XSSFFont font3 = hssfworkbook.createFont();
                         font3.setColor(HSSFColor.BLUE.index);
                         richString.applyFont(font3);
                         cell.setCellValue(richString);
                	// }
                 }
             }
        }
		try {
			hssfworkbook.write(os);
			os.flush();
		} catch (Exception e) {
			log.error("[Excel写入]写入excel出错", e);
		} finally {
			try {
				os.close();
			} catch (Exception e2) {
				if (log.isErrorEnabled())
					log.error("关闭连接错误", e2);
			}

		}
	}
	/**
	 * 
	 * @param sheetName sheet名称
	 * @param headers  产生表格标题行数组
	 * @param fieldNames 对象属性名称数组
	 * @param dataset  数据集
	 * @param os 输出流
	 * @param formatter 日期转换格式
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void exportExcelHeadersAndFieldNames(String sheetName,String[] filters,String[] headers,Integer[] widths,String fieldNames[],Collection<T> dataset,OutputStream os,String formatter) {
		//日期格式化 
		SimpleDateFormat sdf = new SimpleDateFormat(formatter);
		// 工作簿
		XSSFWorkbook hssfworkbook = new XSSFWorkbook();
		// 创建sheet页
		XSSFSheet hssfsheet = hssfworkbook.createSheet();
		// 设置sheet名称
		if (StringUtils.isBlank(sheetName)) { // 设置sheet的名字
			hssfworkbook.setSheetName(0, "sheet1");
		} else {
			hssfworkbook.setSheetName(0, sheetName);
		}
		

		//字体  
		XSSFFont font = hssfworkbook.createFont();  
		font.setColor(HSSFColor.VIOLET.index);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font.setFontHeightInPoints((short)12); //设置字体大小
		
		// 生成一个样式
		XSSFCellStyle cellStyle = hssfworkbook.createCellStyle();
		cellStyle.setFillForegroundColor(HSSFColor.BLUE.index);
		cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cellStyle.setFont(font);
		
		// 生成并设置另一个样式
		//文字样式（左对齐）
		XSSFCellStyle style2 = hssfworkbook.createCellStyle();
        style2.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
        style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style2.setAlignment(HSSFCellStyle.ALIGN_LEFT);
        style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        // 生成另一个字体
        XSSFFont font2 = hssfworkbook.createFont();
        font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
        // 把字体应用到当前的样式
        style2.setFont(font2);
        
        //金额样式（右对齐）
        XSSFCellStyle style3 = hssfworkbook.createCellStyle();
        style3.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
        style3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style3.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style3.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style3.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style3.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style3.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
        style3.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
         // 把字体应用到当前的样式
        style3.setFont(font2);
        // 声明一个画图的顶级管理器
        XSSFDrawing patriarch = hssfsheet.createDrawingPatriarch();
        int index = 0;
        // 产生查询条件行
        if(filters != null){
        	XSSFRow row = hssfsheet.createRow(index);
        	index ++;
        	for (short i = 0; i < filters.length; i++) {
            	XSSFCell cell = row.createCell(i);
            	if(widths == null || widths[i] == null){
            		hssfsheet.setColumnWidth(i, filters[i].getBytes().length*2*256);
            	}else{
            		hssfsheet.setColumnWidth(i, widths[i]*256);
            	}
                cell.setCellStyle(cellStyle);
                XSSFRichTextString text = new XSSFRichTextString(filters[i]);
                cell.setCellValue(text);
            }
        	index++;
        }
        // 产生表格标题行
        XSSFRow row = hssfsheet.createRow(index);
        if(headers != null){
        	for (short i = 0; i < headers.length; i++) {
            	XSSFCell cell = row.createCell(i);
            	if(widths == null || widths[i] == null){
            		hssfsheet.setColumnWidth(i, headers[i].getBytes().length*2*256);
            	}else{
            		hssfsheet.setColumnWidth(i, widths[i]*256);
            	}
                cell.setCellStyle(cellStyle);
                XSSFRichTextString text = new XSSFRichTextString(headers[i]);
                cell.setCellValue(text);
            }
        }
		// 写入所有内容行
        Iterator<T> it = dataset.iterator();
        while (it.hasNext()) {
        	 index++;
             row = hssfsheet.createRow(index);
             T t = (T) it.next();
             List<Object>list = new ArrayList<Object>();
             if(t instanceof Map){
            	Map<String, Object> obj = (Map<String, Object>) t;
     			Set<Map.Entry<String, Object>> set1 = obj.entrySet();
     	        for (Iterator<Map.Entry<String, Object>> it1 = set1.iterator(); it1.hasNext();) {
     	        	 Map.Entry<String, Object> entry = (Map.Entry<String, Object>) it1.next();
     	        	list.add(entry.getValue());
     	        }
             }else{ // 利用反射，根据fieldNames属性先后顺序，动态调用getXxx()方法得到属性值
                 for (short i = 0; i < fieldNames.length; i++) {
                  	 String fieldName = fieldNames[i];
                  	 String getMethodName = "get"+ fieldName.substring(0, 1).toUpperCase()+ fieldName.substring(1);
                  	 Class tCls = t.getClass();
                     Object value = null;
                    try {
     					Method getMethod = tCls.getMethod(getMethodName, new Class[] {});
     					value = getMethod.invoke(t, new Object[] {});
     				} catch (SecurityException e) {
     					e.printStackTrace();
     				} catch (NoSuchMethodException e) {
     					e.printStackTrace();
     				} catch (IllegalArgumentException e) {
     					e.printStackTrace();
     				} catch (IllegalAccessException e) {
     					e.printStackTrace();
     				} catch (InvocationTargetException e) {
     					e.printStackTrace();
     				}
                    list.add(value);
                 }
             }
             for(short i = 0; i < list.size(); i++){
            	Object obj =  list.get(i);
            	XSSFCell cell = row.createCell(i);
              	 String textValue = null;
              	 if(obj == null){
              		textValue = "";
              	 }else if (obj instanceof Date){
                	 Date date = (Date) obj;
                	 textValue = sdf.format(date);
                 }else if (obj instanceof Boolean){
                	 boolean bValue = (Boolean) obj;
                	 if(bValue)
                		 textValue="是";
                	 else
                		 textValue="否";
                 } else if (obj instanceof byte[]) {
                     // 有图片时，设置行高为60px;
                     row.setHeightInPoints(60);
                     // 设置图片所在列宽度为80px,注意这里单位的一个换算
                     hssfsheet.setColumnWidth(i, (short) (35.7 * 80));
                     // sheet.autoSizeColumn(i);
                     byte[] bsValue = (byte[]) obj;
                     XSSFClientAnchor anchor = new XSSFClientAnchor(0, 0,1023, 255, (short) 6, index, (short) 6, index);
                     anchor.setAnchorType(2);
                     patriarch.createPicture(anchor, hssfworkbook.addPicture( bsValue, XSSFWorkbook.PICTURE_TYPE_JPEG));
                 } else {
                     // 其它数据类型都当作字符串简单处理
                     textValue = obj.toString();
                 }
                 
                 // 如果不是图片数据，就利用正则表达式判断textValue是否全部由数字组成
                 if (textValue != null) {
                	 if(isMoney(textValue)){
                 		cell.setCellStyle(style3);
                 	}else{
                 		cell.setCellStyle(style2);
                 	}
            		 XSSFRichTextString richString = new XSSFRichTextString(textValue);
                     XSSFFont font3 = hssfworkbook.createFont();
                     font3.setColor(HSSFColor.BLUE.index);
                     richString.applyFont(font3);
                     cell.setCellValue(richString);
                 }
             }
        }
		try {
			hssfworkbook.write(os);
			os.flush();
		} catch (Exception e) {
			log.error("[Excel写入]写入excel出错", e);
		} finally {
			try {
				os.close();
			} catch (Exception e2) {
				if (log.isErrorEnabled())
					log.error("关闭连接错误", e2);
			}

		}
	}

	@Override
	protected T convert(HSSFRow hssfRow) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static boolean isMoney(String str){
		Pattern pattern = Pattern.compile("^(-)?(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){1,2})?$");
		Matcher matcher = pattern.matcher(str);
		return matcher.matches();
	}
	
}
