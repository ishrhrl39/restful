package com.mnwise.carrym.wiseu.rest.interceptor;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mnwise.carrym.wiseu.rest.send.controller.MailController;
import com.mnwise.carrym.wiseu.rest.send.model.NvContract;
import com.mnwise.carrym.wiseu.rest.send.model.NvRestUser;
import com.mnwise.carrym.wiseu.rest.send.service.ContractService;
import com.mnwise.carrym.wiseu.rest.send.service.RestUserService;
import com.mnwise.carrym.wiseu.rest.util.Aria;
import com.mnwise.carrym.wiseu.rest.util.Constants;
import com.mnwise.carrym.wiseu.rest.util.ResultDto;

public class Interceptor extends HandlerInterceptorAdapter{
	
	Logger logger = Logger.getLogger(Interceptor.class);
	
	private RestUserService restUserService;
	private MessageSourceAccessor messageSourceAccessor;
	private ContractService contractService;
	
	
	public void setRestUserService(RestUserService restUserService) {
		this.restUserService = restUserService;
	}
	
	public void setMessageSourceAccessor(MessageSourceAccessor messageSourceAccessor) {
		this.messageSourceAccessor = messageSourceAccessor;
	}
	
	public void setContractService(ContractService contractService) {
		this.contractService = contractService;
	}

	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		// request Header로 들어온 Authorization 값을 Base64 복호화하여 셋팅
		String authHeaderStr = request.getHeader("Authorization");
		NvContract nvContract = new NvContract();	// 계약정보
		if(authHeaderStr ==  null) {
			printJsonResult(response, Constants.Result.NO_AUTHORIZATION);	// Authorization 헤더정보가 없음.
		}
		
		String authorization = new String(Base64.decodeBase64(authHeaderStr));
		String [] account = authorization.split("\\+");
		
		NvRestUser nvRestUser = new NvRestUser();
		nvRestUser.setID(account[0]);
		nvRestUser.setPASSWORD(Aria.encrypt(account[1]));

		logger.debug("login-id => " + nvRestUser.getID());
		
		nvRestUser = restUserService.selectRestUser(nvRestUser);
		
		if(nvRestUser == null) {
			printJsonResult(response, Constants.Result.NO_USER);	// 결과코드 (사용자 없음)
			return false;
		}
		
		String href = request.getRequestURI();
		logger.info("request uri => " + href);
			
		String channel = "M";
		switch(href) {
		case "/send/mail":		// 메일
			break;
		case "/send/smail":		// 보안메일
			channel = "SM";
			break;
		case "/send/sms":		// SMS
			channel = "S";
			break;
		case "/send/lms":		// LMS
			channel = "L";
			break;
		case "/send/mms":		// MMS
			channel = "T";
			break;
		case "/send/at":		// 알림톡
			channel = "A";
			break;
		case "/send/ft":		// 친구톡
			channel = "C";
			break;
		}
		
		nvContract.setID(nvRestUser.getID());
		nvContract.setCHANNEL(channel);
		nvContract = contractService.selectContract(nvContract);
		if(nvContract == null || nvContract.getUSE_YN().equalsIgnoreCase("N")) {
			printJsonResult(response, Constants.Result.NO_CONTRACT);	// 결과코드 (사용자 없음)
			return false;
		}
		
		request.setAttribute("userVo", nvRestUser);
		
        return super.preHandle(request, response, handler);
    }
 
	/**
	 * JSON 에러 데이터를 반환
	 * @param response
	 * @throws IOException
	 */
    private void printJsonResult(HttpServletResponse response, String result) throws IOException {
    	Gson gson = new Gson();
		JsonObject object = new JsonObject();
		object.addProperty("result", result);
		object.addProperty("message", messageSourceAccessor.getMessage("result.message." + result));
		logger.info(messageSourceAccessor.getMessage("result.message." + result));
		PrintWriter out = response.getWriter();
		out.println(gson.toJson(object));
		
	}

	@Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }
 
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }


    public static void main(String[] args) throws Exception {
    	System.out.println(Aria.decrypt("7103EF6BC28112F768609DBADFBFC7C1"));
	}
}
