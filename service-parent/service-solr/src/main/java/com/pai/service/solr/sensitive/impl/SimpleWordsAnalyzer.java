package com.pai.service.solr.sensitive.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.pai.service.solr.sensitive.WordsAnalyzer;
import com.pai.service.solr.sensitive.WordsLoader;

public class SimpleWordsAnalyzer implements WordsAnalyzer {

	private WordsLoader loader;
	private Node root;

	public void setLoader(WordsLoader loader) {
		this.loader = loader;
	}

	public Map<String, Integer> search(String text) {
		char[] chars = text.toCharArray();
		Node node = root;
		
		StringBuffer buf = new StringBuffer();
		Map<String, Integer> result = new HashMap<String, Integer>();
		
		char curr;
		int a = 0;
		
		while( a < chars.length){
			curr = chars[a];
			node = findNode(node, curr);
			
			if(node == null){
				node = root;
				a = a - buf.length();
				
				buf = new StringBuffer();
			}else if(node.end == true){
				buf.append( curr );
				procMap(result, buf.toString());
				a = a - buf.length() + 1;
				
				node = root;
				buf = new StringBuffer();
			}else{
				buf.append( curr );
			}
			
			a++;
		}
		
		return result;
	}

	private void procMap(Map<String, Integer> map, String text){
		if(map.containsKey(text)){
			map.put(text, map.get(text) + 1);
		}else{
			map.put(text, 1);
		}
	}
	
	public void init(){
		root = new Node('R');
		createTree();
	}
	
	
	private void createTree(){
		List<String> words = loader.loadWords();
		for(String word : words){
			char[] cs = word.toCharArray();
			insertNode(root, cs, 0);
		}
	}
	
	private void insertNode(Node node, char[] cs, int index){
		Node n = findNode(node, cs[index]);
		if(n == null){
			n = new Node( cs[index] );
			node.getNodes().add(n);
		}
		
		if(index == cs.length - 1){
			n.setEnd(true);
		}
		
		index++;
		
		if(index < cs.length){
			insertNode(n, cs, index);
		}
	}
	
	private Node findNode(Node node, char c){
		List<Node> nodes = node.getNodes();
		Node m = null;
		for(Node n : nodes){
			if(c == n.getC()){
				m = n;
				break;
			}
		}
		return m;
	}
	
	@Override
	public List<Word> findWord(String text) {
		List<Word> rt = new ArrayList<Word>();
		Map<String, Integer> map = search(text);
		Set<String> keys = map.keySet();
		Word w;
		for(String key : keys){
			w = new Word();
			w.setText(key);
			w.setFreq(map.get(key));
			rt.add(w);
		}
		return rt;
	}

	@Override
	public Set<String> find(String text) {
		List<String> rt = new ArrayList<String>();
		Map<String, Integer> map = search(text);
		return map.keySet();
	}
	
	/***************************Inner Class *********************************/
	class Node{
		private char c;
		private boolean end = false;
		private ArrayList<Node> nodes = new ArrayList<Node>();
		
		public Node(char c){
			this.c = c;
			end = false;
		}

		public char getC() {
			return c;
		}

		public void setC(char c) {
			this.c = c;
		}

		public boolean isEnd() {
			return end;
		}

		public void setEnd(boolean end) {
			this.end = end;
		}

		public ArrayList<Node> getNodes() {
			return nodes;
		}

		public void setNodes(ArrayList<Node> nodes) {
			this.nodes = nodes;
		}
		
	}
	
	public static class Word{
		private String text;
		private int freq;
		public String getText() {
			return text;
		}
		public void setText(String text) {
			this.text = text;
		}
		public int getFreq() {
			return freq;
		}
		public void setFreq(int freq) {
			this.freq = freq;
		}
		@Override
		public String toString() {
			return "Word [text=" + text + ", freq=" + freq + "]";
		}
		
	}

}
