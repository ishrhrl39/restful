package com.mnwise.carrym.wiseu.rest.send.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mnwise.carrym.wiseu.rest.send.controller.MailController;
import com.mnwise.carrym.wiseu.rest.send.dao.SendDao;
import com.mnwise.carrym.wiseu.rest.send.model.NvRealtimeAccept;

@Service
public class SendServiceImpl implements SendService {

	
	Logger logger = Logger.getLogger(SendServiceImpl.class);
	
	@Autowired
	private SendDao sendDao ;

	@Override
	public void insertNvrealtimeAccept(NvRealtimeAccept nvrealtimeaccept) {
		sendDao.insertNvrealtimeAccept(nvrealtimeaccept);
	}

	@Override
	public boolean isDuplicateSeq(NvRealtimeAccept nvrealtimeaccept) {
		if(sendDao.selectNvrealtimeacceptCount(nvrealtimeaccept) > 0) {
			return true;
		}
		return false;
	}
	
	
	
	
}
