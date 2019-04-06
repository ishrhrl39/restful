package com.mnwise.carrym.wiseu.rest.util;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class CarrymController {
	public CarrymController() {
		
	}
	
	
	
	private static Map checkVo(Object object, String ... columnList) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		Map returnData = new HashMap();
		for(String columnNm : columnList) {
			Field field = object.getClass().getDeclaredField(columnNm);
			field.setAccessible(true);
			String value = (String)field.get(object);
			if(value == null || value.equals("")) {
				returnData.put("result", false);
				returnData.put("columnNm", columnNm);
				return returnData;
			}else {
				continue;
			}
		}
		
		returnData.put("result", true);
		return returnData;
	}
}
