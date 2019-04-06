package com.mnwise.carrym.wiseu.rest.util;

public class StringUtil {
	public static String defaultString(Object object, String defaultValue) {
		if(object == null) {
			return defaultValue;
		}else {
			return object.toString();
		}
	}
	
	
	public static boolean isEmpty(String str) {
		if(str == null) {
			return true;
		}else if(str.trim().equals("")) {
			return true;
		}
		return false;
	}
}
