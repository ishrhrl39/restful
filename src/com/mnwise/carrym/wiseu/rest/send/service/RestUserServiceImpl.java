package com.mnwise.carrym.wiseu.rest.send.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mnwise.carrym.wiseu.rest.send.controller.MailController;
import com.mnwise.carrym.wiseu.rest.send.dao.RestUserDao;
import com.mnwise.carrym.wiseu.rest.send.dao.SendDao;
import com.mnwise.carrym.wiseu.rest.send.model.NvRealtimeAccept;
import com.mnwise.carrym.wiseu.rest.send.model.NvRestUser;

@Service
public class RestUserServiceImpl implements RestUserService {

	Logger logger = Logger.getLogger(RestUserServiceImpl.class);
	
	private RestUserDao restUserDao;

	public void setRestUserDao(RestUserDao restUserDao) {
		this.restUserDao = restUserDao;
	}

	@Override
	public NvRestUser selectRestUser(NvRestUser nvRestUser) {
		return restUserDao.selectRestUser(nvRestUser);
	}

}
