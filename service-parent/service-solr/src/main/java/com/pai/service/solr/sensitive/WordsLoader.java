package com.pai.service.solr.sensitive;

import java.util.List;

public interface WordsLoader {

	/**
	 * 加载敏感词列表
	 * @return
	 */
	List<String> loadWords();
}
