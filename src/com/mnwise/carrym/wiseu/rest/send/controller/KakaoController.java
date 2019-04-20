package com.mnwise.carrym.wiseu.rest.send.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
public class KakaoController extends CarrymController{
	
	Gson gson = new Gson();
	
	Logger logger = Logger.getLogger(MailController.class);
	@Autowired
	private SendService sendService;
	
	@Autowired
	private RestUserService restUserService;
	
	@Autowired
	private MessageSourceAccessor messageSourceAccessor;
	
	/**
	 * 알림톡 발송
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/at")
	public ModelAndView at_send(HttpServletRequest request) throws Exception {
		logger.info("send alimtalk start()");
		NvRestUser userVo = (NvRestUser)request.getAttribute("userVo");
		JsonObject jsonObject = new JsonObject();
		
		String json = RequestParamUtil.getJsonString(request);   // request -> jsonString
		JsonElement el = JsonUtil.stringToJsonElement(json);
		String message = JsonUtil.defaultFieldValue(el, "MESSAGE");	// MESSAGE FIELD
		String tmplCd = JsonUtil.defaultFieldValue(el, "TMPL_CD");	// TMPL_CD
		String smsSndYn = JsonUtil.defaultFieldValue(el, "SMS_SND_YN");	// SMS_SND_YN
		String subject = JsonUtil.defaultFieldValue(el, "SUBJECT");	// SUBJECT
		String button = JsonUtil.defaultFieldValue(el, "BUTTON");	// BUTTON
		String smsSndMsg = JsonUtil.defaultFieldValue(el, "SMS_SND_MSG");	// SMS_SND_MSG
		
		if(StringUtil.isEmpty(message))	// message is empty
			return ResultDto.getMessage(Constants.Result.NO_VALUE, "MESSAGE");
		else if(StringUtil.isEmpty(tmplCd))	// tmplCd is empty
			return ResultDto.getMessage(Constants.Result.NO_VALUE, "TMPL_CD");
		
		jsonObject.addProperty("MESSAGE", message);	// JONMUN add message
		jsonObject.addProperty("TMPL_CD", tmplCd);	// JONMUN add tmplCd
		jsonObject.addProperty("SMS_SND_YN", smsSndYn);	// JONMUN add smsSndYn
		jsonObject.addProperty("SUBJECT", subject);	// JONMUN add subject
		jsonObject.addProperty("BUTTON", button);	// JONMUN add button
		jsonObject.addProperty("SMS_SND_MSG", smsSndMsg);	// JONMUN add smsSndMsg
			
		NvRealtimeAccept nvrealtimeaccept = RequestParamUtil.jsonToNvrealtimeacceptVo(json);
		nvrealtimeaccept.setREQ_USER_ID(userVo.getID());
		nvrealtimeaccept.setJONMUN(gson.toJson(jsonObject));
		nvrealtimeaccept.setCHANNEL("A");
		nvrealtimeaccept.setSUBJECT(nvrealtimeaccept.getSUBJECT() == null ? "" : nvrealtimeaccept.getSUBJECT());
		
		Map result = isVoColumnVal(nvrealtimeaccept, "SEQ", "RECEIVER", "JONMUN");
		boolean isColumnVal = Boolean.parseBoolean(result.get("result").toString());
		logger.info("accept column valid check = " + isColumnVal);
		if(!isColumnVal){	// 컬럼 유효성 체크
			return ResultDto.getMessage(Constants.Result.NO_VALUE, result.get("columnNm").toString());
		}
		if(sendService.isDuplicateSeq(nvrealtimeaccept)) {		// SEQ 중복체크
			return ResultDto.getMessage(Constants.Result.DUPL_SEQ);
		}
		
		sendService.insertNvrealtimeAccept(nvrealtimeaccept);
		
		return ResultDto.getMessage(Constants.Result.SUCCESS);
	}
	
	/**
	 * 친구톡 발송
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/ft")
	public ModelAndView ft_send(HttpServletRequest request) throws Exception {
		logger.info("send friendTalk start()");
		NvRestUser userVo = (NvRestUser)request.getAttribute("userVo");
		JsonObject jsonObject = new JsonObject();
		
		String json = RequestParamUtil.getJsonString(request);   // request -> jsonString
		JsonElement el = JsonUtil.stringToJsonElement(json);
		String message = JsonUtil.defaultFieldValue(el, "MESSAGE");	// MESSAGE FIELD
		String tmplCd = JsonUtil.defaultFieldValue(el, "TMPL_CD");	// TMPL_CD
		String smsSndYn = JsonUtil.defaultFieldValue(el, "SMS_SND_YN");	// SMS_SND_YN
		String subject = JsonUtil.defaultFieldValue(el, "SUBJECT");	// SUBJECT
		String button = JsonUtil.defaultFieldValue(el, "BUTTON");	// BUTTON
		String smsSndMsg = JsonUtil.defaultFieldValue(el, "SMS_SND_MSG");	// SMS_SND_MSG
		
		if(StringUtil.isEmpty(message))	// message is empty
			return ResultDto.getMessage(Constants.Result.NO_VALUE, "MESSAGE");
		else if(StringUtil.isEmpty(tmplCd))	// tmplCd is empty
			return ResultDto.getMessage(Constants.Result.NO_VALUE, "TMPL_CD");
		
		jsonObject.addProperty("MESSAGE", message);	// JONMUN add message
		jsonObject.addProperty("TMPL_CD", tmplCd);	// JONMUN add tmplCd
		jsonObject.addProperty("SMS_SND_YN", smsSndYn);	// JONMUN add smsSndYn
		jsonObject.addProperty("SUBJECT", subject);	// JONMUN add subject
		jsonObject.addProperty("BUTTON", button);	// JONMUN add button
		jsonObject.addProperty("SMS_SND_MSG", smsSndMsg);	// JONMUN add smsSndMsg
		jsonObject.addProperty("IMG_URL", JsonUtil.defaultFieldValue(el, "IMG_URL"));	// JONMUN add imgUrl
		jsonObject.addProperty("IMG_LINK", JsonUtil.defaultFieldValue(el, "IMG_LINK"));	// JONMUN add imgLink
			
		NvRealtimeAccept nvrealtimeaccept = RequestParamUtil.jsonToNvrealtimeacceptVo(json);
		nvrealtimeaccept.setREQ_USER_ID(userVo.getID());
		nvrealtimeaccept.setJONMUN(gson.toJson(jsonObject));
		nvrealtimeaccept.setCHANNEL("C");
		nvrealtimeaccept.setSUBJECT(nvrealtimeaccept.getSUBJECT() == null ? "" : nvrealtimeaccept.getSUBJECT());
		
		Map result = isVoColumnVal(nvrealtimeaccept, "SEQ", "RECEIVER", "JONMUN");
		boolean isColumnVal = Boolean.parseBoolean(result.get("result").toString());
		logger.info("accept column valid check = " + isColumnVal);
		if(!isColumnVal){	// 컬럼 유효성 체크
			return ResultDto.getMessage(Constants.Result.NO_VALUE, result.get("columnNm").toString());
		}
		if(sendService.isDuplicateSeq(nvrealtimeaccept)) {		// SEQ 중복체크
			return ResultDto.getMessage(Constants.Result.DUPL_SEQ);
		}
		
		sendService.insertNvrealtimeAccept(nvrealtimeaccept);
		
		return ResultDto.getMessage(Constants.Result.SUCCESS);
	}
}
