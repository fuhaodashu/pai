package com.pai.service.solr.sensitive;

import java.util.List;
import java.util.Set;

import com.pai.service.solr.sensitive.impl.SimpleWordsAnalyzer.Word;

public interface WordsAnalyzer {

	/**
	 * 查找文本中的敏感词
	 * @param text
	 * @return
	 */
	Set<String> find(String text);
	
	/**
	 * 查找文本中的敏感词，详细
	 * @param text
	 * @return
	 */
	List<Word> findWord(String text);
}
