package com.mnwise.carrym.wiseu.rest.send.dao;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.mnwise.carrym.wiseu.rest.send.model.NvRealtimeAccept;
import com.mnwise.carrym.wiseu.rest.send.model.NvRestUser;
import com.mnwise.carrym.wiseu.rest.send.service.SendServiceImpl;

@Repository
public class RestUserDaoImpl extends SqlMapClientDaoSupport implements RestUserDao {

	Logger logger = Logger.getLogger(RestUserDaoImpl.class);
	
	private String namespace = "SqlMapRestUser.";
	
	@Autowired
	public RestUserDaoImpl(SqlMapClient sqlMapClient) {
		super.setSqlMapClient(sqlMapClient);
	}

	@Override
	public NvRestUser selectRestUser(NvRestUser nvRestUser) {
		return (NvRestUser) getSqlMapClientTemplate().queryForObject(namespace + "selectRestUser", nvRestUser);
	}
	
	

	
}
