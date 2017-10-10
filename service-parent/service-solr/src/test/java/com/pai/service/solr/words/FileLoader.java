package com.pai.service.solr.words;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.pai.service.solr.sensitive.WordsLoader;

public class FileLoader implements WordsLoader {

	@Override
	public List<String> loadWords() {
		List<String> rt = new ArrayList<String>();
		/*
		rt.add("他妈的");
		rt.add("他爷爷的");
		rt.add("操你妈");
		rt.add("妈了个逼的");
		rt.add("马勒戈壁");
		rt.add("日你妈");
		rt.add("干你妹");
		rt.add("习近平");
		rt.add("共产主义");
		rt.add("共产党");
		rt.add("王利杰");
		rt.add("路线");
		*/
		rt.add("习近平");
		InputStreamReader read;
		
		
		try {
			read = new InputStreamReader(
					getClass().getClassLoader().getResourceAsStream("com/pai/service/solr/words/sensitive-word.dict"),"UTF-8");
			
			BufferedReader bufferedReader = new BufferedReader(read);
            String lineTxt = null;
            while((lineTxt = bufferedReader.readLine()) != null){
                rt.add(lineTxt);
            }
            
            read.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
        
		return rt;
	}

}
