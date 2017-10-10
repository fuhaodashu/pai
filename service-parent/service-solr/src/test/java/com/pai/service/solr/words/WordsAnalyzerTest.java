package com.pai.service.solr.words;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pai.service.solr.sensitive.WordsAnalyzer;
import com.pai.service.solr.sensitive.impl.SimpleWordsAnalyzer.Word;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:conf/service-solr.xml")
public class WordsAnalyzerTest extends AbstractJUnit4SpringContextTests{
	
	@Resource
	private WordsAnalyzer wordsAnalyzer;
	
	@Test
	public void analyzeTest() throws Exception{
		InputStreamReader read;
		StringBuffer buf = new StringBuffer();
		
		try {
			read = new InputStreamReader(
					ClassLoader.getSystemResourceAsStream("com/pai/service/solr/words/input.txt"),"UTF-8");
			
			BufferedReader bufferedReader = new BufferedReader(read);
            String lineTxt = null;
            
            while((lineTxt = bufferedReader.readLine()) != null){
                buf.append(lineTxt);
            }
            
            read.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		int len = 10;
		long count = 0;
		WordsAnalyzer analyzer = wordsAnalyzer;
		for(int i = 0 ; i < len; i++){
			long start = System.currentTimeMillis();
			List<Word> result = analyzer.findWord( buf.toString() );
			long end = System.currentTimeMillis();
			long x = end - start;
			System.out.println("耗时(毫秒): " + x);
			count += x;
			System.out.println("查找结果: " + result);
		}
		System.out.println("平均耗时(毫秒):  " + count / len);
		
	}

	public static void main(String[] args)  throws Exception{}
	
}
