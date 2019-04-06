package com.mnwise.carrym.wiseu.rest.util;

public class StringUtils {
	public static String defaultString(Object object, String defaultValue) {
		if(object == null) {
			return defaultValue;
		}else {
			return object.toString();
		}
	}
}
