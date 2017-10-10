package com.pai.service.image.comparator;

import java.util.Comparator;
import java.util.Hashtable;

import com.pai.service.image.constants.FileConstants;

public class NameComparator implements Comparator {
	public int compare(Object a, Object b) {
		Hashtable hashA = (Hashtable)a;
		Hashtable hashB = (Hashtable)b;
		if (((Boolean)hashA.get(FileConstants.IS_DIR)) && !((Boolean)hashB.get(FileConstants.IS_DIR))) {
			return -1;
		} else if (!((Boolean)hashA.get(FileConstants.IS_DIR)) && ((Boolean)hashB.get(FileConstants.IS_DIR))) {
			return 1;
		} else {
			return ((String)hashA.get(FileConstants.FILE_NAME)).compareTo((String)hashB.get(FileConstants.FILE_NAME));
		}
	}
}
