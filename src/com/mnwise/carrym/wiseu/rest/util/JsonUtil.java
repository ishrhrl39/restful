package com.mnwise.carrym.wiseu.rest.util;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class JsonUtil {
	/**
	 * String -> jsonElement
	 * @param request
	 * @return
	 */
	public static JsonElement stringToJsonElement(String json) {
		JsonParser parser = new JsonParser();
		return parser.parse(json);
	}
	
	/**
	 * 필드값에 대하여 기본값을 셋팅한다.
	 * @param element
	 * @param columnNm
	 * @return
	 */
	public static String defaultFieldValue(JsonElement element, String columnNm) {
		Object obj = element.getAsJsonObject().get(columnNm);
		if(obj == null) {
			return "";
		}else {
//			return obj.toString().trim();
			return ((JsonElement)obj).getAsString().trim();
		}
	}
}
