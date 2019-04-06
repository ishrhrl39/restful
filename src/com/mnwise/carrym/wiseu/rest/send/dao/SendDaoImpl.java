package com.mnwise.carrym.wiseu.rest.send.dao;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.mnwise.carrym.wiseu.rest.send.model.NvRealtimeAccept;
import com.mnwise.carrym.wiseu.rest.send.service.SendServiceImpl;

@Repository
public class SendDaoImpl extends SqlMapClientDaoSupport implements SendDao {

	Logger logger = Logger.getLogger(SendDaoImpl.class);
	
	private String namespace = "SqlMapSend.";
	
	@Autowired
	public SendDaoImpl(SqlMapClient sqlMapClient) {
		super.setSqlMapClient(sqlMapClient);
	}

	@Override
	public void insertNvrealtimeAccept(NvRealtimeAccept nvrealtimeaccept) {
		getSqlMapClientTemplate().insert(namespace + "insertNvrealtimeAccept", nvrealtimeaccept);
	}

	@Override
	public int selectNvrealtimeacceptCount(NvRealtimeAccept nvrealtimeaccept) {
		return (int) getSqlMapClientTemplate().queryForObject(namespace + "selectNvrealtimeacceptCount", nvrealtimeaccept);
	}
	
	

	
}
