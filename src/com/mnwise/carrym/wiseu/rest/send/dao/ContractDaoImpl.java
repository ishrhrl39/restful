package com.mnwise.carrym.wiseu.rest.send.dao;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.mnwise.carrym.wiseu.rest.send.model.NvContract;

@Repository
public class ContractDaoImpl extends SqlMapClientDaoSupport implements ContractDao {

	Logger logger = Logger.getLogger(ContractDaoImpl.class);
	
	private String namespace = "SqlMapContract.";
	
	@Autowired
	public ContractDaoImpl(SqlMapClient sqlMapClient) {
		super.setSqlMapClient(sqlMapClient);
	}

	@Override
	public NvContract selectContract(NvContract nvContract) {
		return (NvContract) getSqlMapClientTemplate().queryForObject(namespace + "selectContract",nvContract);
	}
}
