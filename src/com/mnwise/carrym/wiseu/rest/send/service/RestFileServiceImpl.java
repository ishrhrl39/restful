package com.mnwise.carrym.wiseu.rest.send.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mnwise.carrym.wiseu.rest.send.dao.RestFileDao;
import com.mnwise.carrym.wiseu.rest.send.model.NvRestFile;

@Service
public class RestFileServiceImpl implements RestFileService {

	Logger logger = Logger.getLogger(RestFileServiceImpl.class);
	
	@Autowired
	private RestFileDao restFileDao;

	@Override
	public NvRestFile selectRestFile(NvRestFile nvRestFile) {
		return restFileDao.selectRestFile(nvRestFile);
	}

	@Override
	public List selectRestFileList(String id) {
		return restFileDao.selectRestFileList(id);
	}

	@Override
	public void insertRestFile(NvRestFile nvRestFile) {
		restFileDao.insertRestFile(nvRestFile);
	}

	@Override
	public void deleteRestFile(NvRestFile nvRestFile) {
		restFileDao.deleteRestFile(nvRestFile);
	}

}
