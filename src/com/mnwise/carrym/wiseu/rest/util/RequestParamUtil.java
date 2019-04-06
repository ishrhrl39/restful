package com.mnwise.carrym.wiseu.rest.util;

import java.io.BufferedReader;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.mnwise.carrym.wiseu.rest.send.controller.MailController;
import com.mnwise.carrym.wiseu.rest.send.model.NvRealtimeAccept;

public class RequestParamUtil {

	static Logger logger = Logger.getLogger(RequestParamUtil.class);
	
	/**
	 * Request -> Json -> Vo
	 * @param request
	 * @return
	 */
	public static NvRealtimeAccept requestToVo(HttpServletRequest request) {
		StringBuffer json = new StringBuffer();
	    String line = null;
	    Gson gson = new Gson();
	 
	    try {
	        BufferedReader reader = request.getReader();
	        while((line = reader.readLine()) != null) {
	            json.append(line);
	        }
	    }catch(Exception e) {
	        e.printStackTrace();
	    }

	    NvRealtimeAccept nvrealtimeAccept = gson.fromJson(json.toString(), NvRealtimeAccept.class);
	    return nvrealtimeAccept;
	}
	
}
