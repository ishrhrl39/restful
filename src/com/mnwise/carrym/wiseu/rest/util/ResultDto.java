package com.mnwise.carrym.wiseu.rest.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.web.servlet.ModelAndView;


public class ResultDto{
	
	static Logger logger = Logger.getLogger(ResultDto.class);
	
	private static MessageSourceAccessor messageSourceAccessor;
	
	public static void setMessageSourceAccessor(MessageSourceAccessor messageSourceAccessor) {
		ResultDto.messageSourceAccessor = messageSourceAccessor;
	}

	public static ModelAndView getMessage(String resultCode) {
		logger.info("messageSourceAccessor => " + messageSourceAccessor);
		
		ModelAndView mav = new ModelAndView();
		
		Map returnData = new HashMap();
		returnData.put("result", resultCode);
		returnData.put("message", messageSourceAccessor.getMessage("result.message." + resultCode));
		
		mav.addAllObjects(returnData);
		mav.setViewName("jsonView");
		return mav;
	}
	
}
