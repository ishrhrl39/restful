package com.mnwise.carrym.wiseu.rest.send.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mnwise.carrym.wiseu.rest.send.model.NvRealtimeAccept;
import com.mnwise.carrym.wiseu.rest.send.model.NvRestUser;
import com.mnwise.carrym.wiseu.rest.send.service.RestUserService;
import com.mnwise.carrym.wiseu.rest.send.service.SendService;
import com.mnwise.carrym.wiseu.rest.util.Aria;
import com.mnwise.carrym.wiseu.rest.util.CarrymController;
import com.mnwise.carrym.wiseu.rest.util.Constants;
import com.mnwise.carrym.wiseu.rest.util.RequestParamUtil;
import com.mnwise.carrym.wiseu.rest.util.ResultDto;

@Controller
public class MailController extends CarrymController{
	
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
		
		NvRealtimeAccept nvrealtimeaccept = RequestParamUtil.requestToVo(request);
		logger.info(nvrealtimeaccept.getCHANNEL());
//		sendService.insertNvrealtimeAccept(nvrealtimeaccept);
		
		return ResultDto.getMessage(Constants.Result.SUCCESS);
	}
}
