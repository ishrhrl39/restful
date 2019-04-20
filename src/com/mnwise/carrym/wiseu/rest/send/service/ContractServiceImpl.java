package com.mnwise.carrym.wiseu.rest.send.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mnwise.carrym.wiseu.rest.send.dao.ContractDao;
import com.mnwise.carrym.wiseu.rest.send.dao.RestFileDao;
import com.mnwise.carrym.wiseu.rest.send.model.NvContract;
import com.mnwise.carrym.wiseu.rest.send.model.NvRestFile;

@Service
public class ContractServiceImpl implements ContractService {

	Logger logger = Logger.getLogger(ContractServiceImpl.class);
	
	private ContractDao contractDao;

	public void setContractDao(ContractDao contractDao) {
		this.contractDao = contractDao;
	}

	@Override
	public NvContract selectContract(NvContract nvContract) {
		return contractDao.selectContract(nvContract);
	}


}
