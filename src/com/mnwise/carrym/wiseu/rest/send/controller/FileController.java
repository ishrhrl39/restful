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
public class FileController extends CarrymController{
	
	Gson gson = new Gson();
	
	Logger logger = Logger.getLogger(FileController.class);
	@Autowired
	private SendService sendService;
	
	@Autowired
	private RestUserService restUserService;
	
	@Autowired
	private MessageSourceAccessor messageSourceAccessor;
	
	@Autowired
	private RestFileService restFileService;
	
	
	@RequestMapping("/file")
	public ModelAndView file_send(HttpServletRequest request) throws Exception {
		
		SimpleDateFormat sdf =  new SimpleDateFormat("yyyyMMddHHmmss");
		String nowDate = sdf.format(new Date());
		NvRestUser userVo = (NvRestUser)request.getAttribute("userVo");
		
		AjaxFileUploadMultipartResolver resolver = new AjaxFileUploadMultipartResolver();
        resolver.setMaxUploadSize(1024 * 1024 * 5);
        MultipartHttpServletRequest multiPartRequest = null;
        multiPartRequest = resolver.resolveMultipart(request);

        MultipartFile file = multiPartRequest.getFile("file");
        
        // 파일 DB에저장
        logger.info("login id => "  + userVo.getID());
        NvRestFile nvRestFile = new NvRestFile();
        nvRestFile.setID(userVo.getID());
        nvRestFile.setFILE_ID(nowDate);
        if(restFileService.selectRestFile(nvRestFile) != null) {
        	String fileId = nvRestFile.getFILE_ID();
        	String nextId = "";
        	if(fileId.indexOf("_") > -1) {
        		nextId = nowDate +"_" + (Integer.parseInt(fileId.substring(fileId.indexOf("_") + 1)) + 1);
        	}else {
        		nextId = nowDate +"_1";
        	}
        	nvRestFile.setFILE_ID(nextId);
        }
        
        String reqDt = nowDate.substring(0,8);
        String uploadPath = PropertiesListener.getPropertyValue("upload.Root.Path");
        String fileNm = file.getOriginalFilename();
        String relativePath = nvRestFile.getID() + "/" + reqDt + "/" + nvRestFile.getFILE_ID() + fileNm.substring(fileNm.lastIndexOf("."));
        String fileFullPath = uploadPath + "/" + relativePath;
        
        // 업로드 + 회원ID 폴더
        FileUtil.mkdir(uploadPath + "/" + nvRestFile.getID());
        
        // 업로드 + 회원ID 폴더 + 날짜
        FileUtil.mkdir(uploadPath + "/" + nvRestFile.getID() + "/" + reqDt);
        
        nvRestFile.setFILE_NM(fileNm);
        nvRestFile.setFILE_PATH(fileFullPath);
        nvRestFile.setREG_DTM(nowDate);
        
        file.transferTo(new File(fileFullPath));
        restFileService.insertRestFile(nvRestFile);
		return ResultDto.getMessage(Constants.Result.SUCCESS, "FILE_ID", nvRestFile.getFILE_ID());
	}
}
