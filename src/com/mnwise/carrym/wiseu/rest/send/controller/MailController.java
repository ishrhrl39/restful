package com.mnwise.carrym.wiseu.rest.send.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mnwise.carrym.wiseu.rest.listener.PropertiesListener;
import com.mnwise.carrym.wiseu.rest.send.model.NvRealtimeAccept;
import com.mnwise.carrym.wiseu.rest.send.model.NvRestFile;
import com.mnwise.carrym.wiseu.rest.send.model.NvRestUser;
import com.mnwise.carrym.wiseu.rest.send.service.RestFileService;
import com.mnwise.carrym.wiseu.rest.send.service.RestUserService;
import com.mnwise.carrym.wiseu.rest.send.service.SendService;
import com.mnwise.carrym.wiseu.rest.util.AjaxFileUploadMultipartResolver;
import com.mnwise.carrym.wiseu.rest.util.CarrymController;
import com.mnwise.carrym.wiseu.rest.util.Constants;
import com.mnwise.carrym.wiseu.rest.util.FileUtil;
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
	
	@Autowired
	private RestFileService restFileService;
	
	@RequestMapping("/mail")
	public ModelAndView send(HttpServletRequest request) throws Exception {
		logger.info("send mail start()");
		NvRestUser userVo = (NvRestUser)request.getAttribute("userVo");
		JsonObject jsonObject = new JsonObject();
		
		String json = RequestParamUtil.getJsonString(request);   // request -> jsonString
		//  {	"SEQ" : 12312344456411112311,	"RECEIVER_NM" : "����",	"RECEIVER" : "Hanyena1134@mnwise.com",	"SENDER_NM" : "�ѿ���",	"SENDER" : "����2",	"SECU_KEY" : "941128",	"SUBJECT" : "�����Դϴ�",	"HTML" : "ddddddd",	"FILE1" : "20190420145302"	}
		
		String html = JsonUtil.defaultFieldValue(JsonUtil.stringToJsonElement(json), "HTML");	// HTML FIELD
		if(StringUtil.isEmpty(html))	// html is empty
			return ResultDto.getMessage(Constants.Result.NO_VALUE, "HTML");
		
		jsonObject.addProperty("HTML", html);	// JONMUN add html
		
		NvRealtimeAccept nvrealtimeaccept = RequestParamUtil.jsonToNvrealtimeacceptVo(json);
		nvrealtimeaccept.setREQ_USER_ID(userVo.getID());
		nvrealtimeaccept.setJONMUN(gson.toJson(jsonObject));
		nvrealtimeaccept.setCHANNEL("M");
		
		// ÷������ ���� ���� �ľ� �� ����
		String isAttachFile = nvFileAttach(userVo.getID(), nvrealtimeaccept, json);
		if(isAttachFile.equals(Constants.Result.NOFILE_DB)) {
			return ResultDto.getMessage(Constants.Result.NOFILE_DB);
		}else if(isAttachFile.equals(Constants.Result.NOFILE_PATH)){
			return ResultDto.getMessage(Constants.Result.NOFILE_PATH);
		}else {
			Map result = isVoColumnVal(nvrealtimeaccept, "SEQ", "RECEIVER_NM", "RECEIVER", "SENDER_NM", "SENDER", "SUBJECT", "JONMUN");
			boolean isColumnVal = Boolean.parseBoolean(result.get("result").toString());
			logger.info("accept column valid check = " + isColumnVal);
			if(!isColumnVal){	// �÷� ��ȿ�� üũ
				return ResultDto.getMessage(Constants.Result.NO_VALUE, result.get("columnNm").toString());
			}
			if(sendService.isDuplicateSeq(nvrealtimeaccept)) {		// SEQ �ߺ�üũ
				return ResultDto.getMessage(Constants.Result.DUPL_SEQ);
			}
			sendService.insertNvrealtimeAccept(nvrealtimeaccept);
			
			return ResultDto.getMessage(Constants.Result.SUCCESS);
		}
	}

	@RequestMapping("/smail")
	public ModelAndView smail_send(HttpServletRequest request) throws Exception {
		logger.info("send mail start()");
		NvRestUser userVo = (NvRestUser)request.getAttribute("userVo");
		JsonObject jsonObject = new JsonObject();
		
		String json = RequestParamUtil.getJsonString(request);   // request -> jsonString
		logger.info("Request => " + json);
		logger.info(request.getParameter("HTML"));
		String html = JsonUtil.defaultFieldValue(JsonUtil.stringToJsonElement(json), "HTML");	// HTML FIELD
		String cover = JsonUtil.defaultFieldValue(JsonUtil.stringToJsonElement(json), "COVER");	// HTML FIELD
		
		if(StringUtil.isEmpty(html)) {	// html is empty
			return ResultDto.getMessage(Constants.Result.NO_VALUE, "HTML");
		}else if(StringUtil.isEmpty(cover)){
			return ResultDto.getMessage(Constants.Result.NO_VALUE, "COVER");
		}
		
		jsonObject.addProperty("HTML", html);	// JONMUN add html
		jsonObject.addProperty("COVER", cover);	// JONMUN add cover
		
		NvRealtimeAccept nvrealtimeaccept = RequestParamUtil.jsonToNvrealtimeacceptVo(json);
		nvrealtimeaccept.setJONMUN(gson.toJson(jsonObject));
		nvrealtimeaccept.setCHANNEL("M");
		nvrealtimeaccept.setRECEIVER_ID(nvrealtimeaccept.getSEQ());
		nvrealtimeaccept.setREQ_USER_ID(userVo.getID()); // ���� ���̵�


		// ÷������ ���� ���� �ľ� �� ����
		String isAttachFile = nvFileAttach(userVo.getID(), nvrealtimeaccept, json);
		if(isAttachFile.equals(Constants.Result.NOFILE_DB)) {
			return ResultDto.getMessage(Constants.Result.NOFILE_DB);
		}else if(isAttachFile.equals(Constants.Result.NOFILE_PATH)){
			return ResultDto.getMessage(Constants.Result.NOFILE_PATH);
		}else {
		
			Map result = isVoColumnVal(nvrealtimeaccept, "SEQ", "RECEIVER_NM", "RECEIVER", "SENDER_NM", "SENDER", "SECU_KEY", "SUBJECT", "JONMUN");
			boolean isColumnVal = Boolean.parseBoolean(result.get("result").toString());
			logger.info("accept column valid check = " + isColumnVal);
			if(!isColumnVal){
				return ResultDto.getMessage(Constants.Result.NO_VALUE, result.get("columnNm").toString());
			}
			if(sendService.isDuplicateSeq(nvrealtimeaccept)) {		// SEQ �ߺ�üũ
				return ResultDto.getMessage(Constants.Result.DUPL_SEQ);
			}
			
			sendService.insertNvrealtimeAccept(nvrealtimeaccept);
			
			return ResultDto.getMessage(Constants.Result.SUCCESS);
		}
	}
	

	/**
	 * ���ε� �� ���� ���� ���� �ľ� �� NvRealtimeAccept�� ����
	 * @param id ���� ���̵�
	 * @param nvrealtimeaccept ���� ���� �� FILE_PATH�� ����
	 * @param json requset�� �Ѿ�� ���� ��ȿ�� üũ
	 * @return
	 */
	private String nvFileAttach(String id, NvRealtimeAccept nvrealtimeaccept, String json) {
		System.out.println("id ---> " + id);
		// ���� ���� ��� ���
		for(int i = 1; i <= 3; i++) {
			 NvRestFile nvRestFile = new NvRestFile();
			 nvRestFile.setFILE_ID(JsonUtil.defaultFieldValue(JsonUtil.stringToJsonElement(json), "FILE"+i));
			 if(!nvRestFile.getFILE_ID().equals("")) {	// ÷������ ��û�� ���� ��쿡��
				 nvRestFile.setID(id);
				 nvRestFile = restFileService.selectRestFile(nvRestFile);
				 if(nvRestFile == null) { // FILE_ID�� �������� ������
					 return Constants.Result.NOFILE_DB; 
				 }else { 
					 if(!FileUtil.isFileExist(nvRestFile.getFILE_PATH())) { // FILE_PATH�� �������� ������
						 return Constants.Result.NOFILE_PATH; 
					 }else {
						 nvrealtimeaccept.setFILE_PATH(i, nvRestFile.getFILE_PATH());
					 }
				 }
			 }
		}
		return Constants.Result.SUCCESS;
		
	}
	
	
}
