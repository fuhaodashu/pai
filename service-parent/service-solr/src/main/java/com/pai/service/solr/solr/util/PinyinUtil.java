package com.pai.service.solr.solr.util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;

/**
 * <pre> 
 * 描述：拼音 工具类
 * 开发公司:π
 * 开发人员:FUHAO
 * 创建时间:2016-09-29 18:00:11
 * </pre>
 */
public class PinyinUtil {
	
	public static void main(String[] args) {
		String str = "中国人";
		String pinyin = chineseToPinyin(str);
		String sort_pinyin = chineseToShortPinyin(str);
		System.out.println(pinyin);
		System.out.println(sort_pinyin);
	}
	
	/**
	 * 中文 转 拼音
	 * @param str
	 * @return String
	 * @exception 
	 * @since  1.0.0
	 */
	public static String chineseToPinyin(String str) {
		char[] nameChar = str.replaceAll("[0-9]", "").toCharArray();
		StringBuilder pinyin = new StringBuilder();
		HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
		defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		for (int i = 0; i < nameChar.length; i++) {
			if (nameChar[i] > 128) {
				try {
					pinyin.append(PinyinHelper.toHanyuPinyinStringArray(
							nameChar[i], defaultFormat)[0]);
				} catch (Exception e) {
					continue;
				}
			} else {
				pinyin.append(nameChar[i]);
			}
		}
		return pinyin.toString().replaceAll("[\\pP]", "");
	}
	
	/**
	 * 获取 中文 拼音缩写
	 * @param str
	 * @return String
	 * @exception 
	 * @since  1.0.0
	 */
	public static String chineseToShortPinyin(String str) {
		StringBuilder pinyin_abb = new StringBuilder();
		for (int j = 0; j < str.length(); j++) {
			char word = str.toLowerCase().charAt(j);
			String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
			if (pinyinArray != null) {
				pinyin_abb.append(pinyinArray[0].charAt(0));
			} else {
				pinyin_abb.append(word);
			}
		}
		return pinyin_abb.toString().replaceAll("[\\pP]", "");
	}

}
