package com.pai.service.solr.sensitive;

import org.springframework.beans.factory.FactoryBean;

import com.pai.service.solr.sensitive.impl.SimpleWordsAnalyzer;

public class WordsAnalyzerFactoryBean implements FactoryBean<WordsAnalyzer> {

	private WordsLoader loader;
	
	public WordsLoader getLoader() {
		return loader;
	}

	public void setLoader(WordsLoader loader) {
		this.loader = loader;
	}

	@Override
	public WordsAnalyzer getObject() throws Exception {
		SimpleWordsAnalyzer analyzer = new SimpleWordsAnalyzer();
		analyzer.setLoader(loader);
		analyzer.init();
		return analyzer;
	}

	@Override
	public Class<?> getObjectType() {
		return WordsAnalyzer.class;
	}

	@Override
	public boolean isSingleton() {
		return false;
	}

}
