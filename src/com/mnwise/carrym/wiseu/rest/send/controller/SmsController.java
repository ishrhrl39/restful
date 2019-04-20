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
import com.google.gson.JsonObject;
import com.mnwise.carrym.wiseu.rest.send.model.NvRealtimeAccept;
import com.mnwise.carrym.wiseu.rest.send.model.NvRestFile;
import com.mnwise.carrym.wiseu.rest.send.model.NvRestUser;
import com.mnwise.carrym.wiseu.rest.send.service.RestFileService;
import com.mnwise.carrym.wiseu.rest.send.service.RestUserService;
import com.mnwise.carrym.wiseu.rest.send.service.SendService;
import com.mnwise.carrym.wiseu.rest.util.CarrymController;
import com.mnwise.carrym.wiseu.rest.util.Constants;
import com.mnwise.carrym.wiseu.rest.util.FileUtil;
import com.mnwise.carrym.wiseu.rest.util.JsonUtil;
import com.mnwise.carrym.wiseu.rest.util.RequestParamUtil;
import com.mnwise.carrym.wiseu.rest.util.ResultDto;
import com.mnwise.carrym.wiseu.rest.util.StringUtil;

@Controller
public class SmsController extends CarrymController{
	
	Gson gson = new Gson();
	
	Logger logger = Logger.getLogger(MailController.class);
	@Autowired
	private SendService sendService;
	
	@Autowired
	private RestUserService restUserService;
	
	@Autowired
	private MessageSourceAccessor messageSourceAccessor;
	
	@Autowired
	private RestFileService restFileService;
	
	@RequestMapping("/sms")
	public ModelAndView sms_send(HttpServletRequest request) throws Exception {
		logger.info("send sms start()");
		NvRestUser userVo = (NvRestUser)request.getAttribute("userVo");
		JsonObject jsonObject = new JsonObject();
		
		String json = RequestParamUtil.getJsonString(request);   // request -> jsonString
		String message = JsonUtil.defaultFieldValue(JsonUtil.stringToJsonElement(json), "MESSAGE");	// MESSAGE FIELD
		
		if(StringUtil.isEmpty(message))	// message is empty
			return ResultDto.getMessage(Constants.Result.NO_VALUE, "MESSAGE");
		
		jsonObject.addProperty("MESSAGE", message);	// JONMUN add message
		
		NvRealtimeAccept nvrealtimeaccept = RequestParamUtil.jsonToNvrealtimeacceptVo(json);
		nvrealtimeaccept.setREQ_USER_ID(userVo.getID());
		nvrealtimeaccept.setJONMUN(gson.toJson(jsonObject));
		nvrealtimeaccept.setCHANNEL("S");
		
		Map result = isVoColumnVal(nvrealtimeaccept, "SEQ", "RECEIVER", "SENDER", "JONMUN");
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
	
	
	
	
	@RequestMapping("/lms")
	public ModelAndView lms_send(HttpServletRequest request) throws Exception {
		logger.info("send lms start()");
		NvRestUser userVo = (NvRestUser)request.getAttribute("userVo");
		JsonObject jsonObject = new JsonObject();
		
		String json = RequestParamUtil.getJsonString(request);   // request -> jsonString
		String message = JsonUtil.defaultFieldValue(JsonUtil.stringToJsonElement(json), "MESSAGE");	// MESSAGE FIELD
		
		if(StringUtil.isEmpty(message))	// message is empty
			return ResultDto.getMessage(Constants.Result.NO_VALUE, "MESSAGE");
		
		jsonObject.addProperty("MESSAGE", message);	// JONMUN add message
		
		NvRealtimeAccept nvrealtimeaccept = RequestParamUtil.jsonToNvrealtimeacceptVo(json);
		nvrealtimeaccept.setREQ_USER_ID(userVo.getID());
		nvrealtimeaccept.setJONMUN(gson.toJson(jsonObject));
		nvrealtimeaccept.setCHANNEL("T");
		nvrealtimeaccept.setSUBJECT(nvrealtimeaccept.getSUBJECT() == null ? "" : nvrealtimeaccept.getSUBJECT());
		
		Map result = isVoColumnVal(nvrealtimeaccept, "SEQ", "RECEIVER", "SENDER", "JONMUN");
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
	 * MMS 발송
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/mms")
	public ModelAndView mms_send(HttpServletRequest request) throws Exception {
		logger.info("send mms start()");
		NvRestUser userVo = (NvRestUser)request.getAttribute("userVo");
		JsonObject jsonObject = new JsonObject();
		
		String json = RequestParamUtil.getJsonString(request);   // request -> jsonString
		String message = JsonUtil.defaultFieldValue(JsonUtil.stringToJsonElement(json), "MESSAGE");	// MESSAGE FIELD
		String imageId = JsonUtil.defaultFieldValue(JsonUtil.stringToJsonElement(json), "IMAGE");	// IMAGE
		
		if(StringUtil.isEmpty(message))	// message is empty
			return ResultDto.getMessage(Constants.Result.NO_VALUE, "MESSAGE");
		if(StringUtil.isEmpty(imageId))	// image is empty
			return ResultDto.getMessage(Constants.Result.NO_VALUE, "IMAGE");
		
		NvRestFile nvRestFile = new NvRestFile();
		nvRestFile.setID(userVo.getID());
		nvRestFile.setFILE_ID(imageId);
		
		logger.info("image id => " + nvRestFile.getFILE_ID());
		nvRestFile = restFileService.selectRestFile(nvRestFile);
		if(nvRestFile == null)		// 파일이 DB내 존재하지 않음.
			return ResultDto.getMessage(Constants.Result.NOFILE_DB);
		if(!FileUtil.isFileExist(nvRestFile.getFILE_PATH()))		// 파일 존재여부 체크
			return ResultDto.getMessage(Constants.Result.NOFILE_PATH);
		if(!FileUtil.isImageFileExt(nvRestFile.getFILE_NM())) 	// 이미지 파일 체크
			return ResultDto.getMessage(Constants.Result.NOIMAGEFILE);

		jsonObject.addProperty("MESSAGE", message);	// JONMUN add message
		
		NvRealtimeAccept nvrealtimeaccept = RequestParamUtil.jsonToNvrealtimeacceptVo(json);
		nvrealtimeaccept.setREQ_USER_ID(userVo.getID());
		nvrealtimeaccept.setJONMUN(gson.toJson(jsonObject));
		nvrealtimeaccept.setCHANNEL("T");
		nvrealtimeaccept.setSUBJECT(nvrealtimeaccept.getSUBJECT() == null ? "" : nvrealtimeaccept.getSUBJECT());
		nvrealtimeaccept.setFILE_PATH1(nvRestFile.getFILE_PATH());
		
		Map result = isVoColumnVal(nvrealtimeaccept, "SEQ", "RECEIVER", "SENDER", "JONMUN");
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
