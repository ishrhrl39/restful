package com.mnwise.carrym.wiseu.rest.send.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mnwise.carrym.wiseu.rest.send.model.NvRealtimeAccept;
import com.mnwise.carrym.wiseu.rest.send.model.NvRestUser;
import com.mnwise.carrym.wiseu.rest.send.service.RestUserService;
import com.mnwise.carrym.wiseu.rest.send.service.SendService;
import com.mnwise.carrym.wiseu.rest.util.CarrymController;
import com.mnwise.carrym.wiseu.rest.util.Constants;
import com.mnwise.carrym.wiseu.rest.util.JsonUtil;
import com.mnwise.carrym.wiseu.rest.util.RequestParamUtil;
import com.mnwise.carrym.wiseu.rest.util.ResultDto;
import com.mnwise.carrym.wiseu.rest.util.StringUtil;

@Controller
public class MailController extends CarrymController{
	
	Gson gson = new Gson();
	
	Logger logger = Logger.getLogger(MailController.class);
	@Autowired
	private SendService sendService;
	
	@Autowired
	private RestUserService restUserService;
	
	@Autowired
	private MessageSourceAccessor messageSourceAccessor;
	
	@RequestMapping("/mail")
	public ModelAndView send(HttpServletRequest request) throws Exception {
		logger.info("send mail start()");
		NvRestUser userVo = (NvRestUser)request.getAttribute("userVo");
		JsonObject jsonObject = new JsonObject();
		
		String json = RequestParamUtil.getJsonString(request);   // request -> jsonString
		String html = JsonUtil.defaultFieldValue(JsonUtil.stringToJsonElement(json), "HTML");	// HTML FIELD
		
		if(StringUtil.isEmpty(html))	// html is empty
			return ResultDto.getMessage(Constants.Result.NO_VALUE, "HTML");
		
		jsonObject.addProperty("HTML", html);	// JONMUN add html
		
		NvRealtimeAccept nvrealtimeaccept = RequestParamUtil.jsonToNvrealtimeacceptVo(json);
		nvrealtimeaccept.setJONMUN(gson.toJson(jsonObject));
		
		Map result = isVoColumnVal(nvrealtimeaccept, "SEQ", "RECEIVER_NM", "RECEIVER", "SENDER_NM", "SENDER", "SUBJECT", "JONMUN");
		boolean isColumnVal = Boolean.parseBoolean(result.get("result").toString());
		logger.info("accept column valid check = " + isColumnVal);
		if(!isColumnVal){
			return ResultDto.getMessage(Constants.Result.NO_VALUE, result.get("columnNm").toString());
		}
		
		logger.info(nvrealtimeaccept.getCHANNEL());
//		sendService.insertNvrealtimeAccept(nvrealtimeaccept);
		
		return ResultDto.getMessage(Constants.Result.SUCCESS);
	}
}
