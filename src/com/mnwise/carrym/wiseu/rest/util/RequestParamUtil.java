package com.mnwise.carrym.wiseu.rest.util;

import java.io.BufferedReader;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.mnwise.carrym.wiseu.rest.send.model.NvRealtimeAccept;

public class RequestParamUtil {

	static Logger logger = Logger.getLogger(RequestParamUtil.class);
	
	/**
	 * String -> VO
	 * @param request
	 * @return
	 */
	public static NvRealtimeAccept jsonToNvrealtimeacceptVo(String json) {
		Gson gson = new Gson();
	    NvRealtimeAccept nvrealtimeAccept = gson.fromJson(json, NvRealtimeAccept.class);
	    return nvrealtimeAccept;
	}
	
	public static String getJsonString(HttpServletRequest request) {
		StringBuffer json = new StringBuffer();
	    String line = null;
	 
	    try {
	        BufferedReader reader = request.getReader();
	        while((line = reader.readLine()) != null) {
	            json.append(line);
	        }
	    }catch(Exception e) {
	        e.printStackTrace();
	    }
	    return json.toString();
	}
	
}
