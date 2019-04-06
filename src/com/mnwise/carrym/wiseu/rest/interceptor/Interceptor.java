package com.mnwise.carrym.wiseu.rest.interceptor;

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
import com.mnwise.carrym.wiseu.rest.send.model.NvRestUser;
import com.mnwise.carrym.wiseu.rest.send.service.RestUserService;
import com.mnwise.carrym.wiseu.rest.util.Aria;
import com.mnwise.carrym.wiseu.rest.util.Constants;
import com.mnwise.carrym.wiseu.rest.util.ResultDto;

public class Interceptor extends HandlerInterceptorAdapter{
	
	Logger logger = Logger.getLogger(Interceptor.class);
	
	private RestUserService restUserService;
	private MessageSourceAccessor messageSourceAccessor;
	
	
	public void setRestUserService(RestUserService restUserService) {
		this.restUserService = restUserService;
	}
	
	public void setMessageSourceAccessor(MessageSourceAccessor messageSourceAccessor) {
		this.messageSourceAccessor = messageSourceAccessor;
	}

	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		// request Header로 들어온 Authorization 값을 Base64 복호화하여 셋팅
		String authorization = new String(Base64.decodeBase64(request.getHeader("Authorization")));
		String [] account = authorization.split("\\+");
		
		NvRestUser nvRestUser = new NvRestUser();
		nvRestUser.setID(account[0]);
		nvRestUser.setPASSWORD(Aria.encrypt(account[1]));
//		logger.info("messageSourceAccessor => " + messageSourceAccessor);
//		logger.info("restUserService => " + restUserService);
		
		logger.info("id => " + nvRestUser.getID());
		logger.info("passwd => " + nvRestUser.getPASSWORD());
		
		nvRestUser = restUserService.selectRestUser(nvRestUser);
		
		
		if(nvRestUser == null) {
			Gson gson = new Gson();
			JsonObject object = new JsonObject();
			String result = Constants.Result.NO_USER;	// 결과코드 (사용자 없음)
			object.addProperty("result", result);
			object.addProperty("message", messageSourceAccessor.getMessage("result.message." + result));
			PrintWriter out = response.getWriter();
			out.println(gson.toJson(object));
			return false;
		}else {
			request.setAttribute("userVo", nvRestUser);
		}
		
        return super.preHandle(request, response, handler);
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
