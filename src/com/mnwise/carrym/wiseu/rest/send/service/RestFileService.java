package com.mnwise.carrym.wiseu.rest.send.service;

import java.util.List;

import com.mnwise.carrym.wiseu.rest.send.model.NvRestFile;

public interface RestFileService {
	public NvRestFile selectRestFile(NvRestFile nvRestFile);
	
	public List selectRestFileList(String id);
	
	public void insertRestFile(NvRestFile nvRestFile);
	
	public void deleteRestFile(NvRestFile nvRestFile);
}
