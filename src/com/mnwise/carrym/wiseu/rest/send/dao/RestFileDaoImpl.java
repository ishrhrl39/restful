package com.mnwise.carrym.wiseu.rest.send.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.mnwise.carrym.wiseu.rest.send.model.NvRestFile;
import com.mnwise.carrym.wiseu.rest.send.model.NvRestUser;

@Repository
public class RestFileDaoImpl extends SqlMapClientDaoSupport implements RestFileDao {

	Logger logger = Logger.getLogger(RestFileDaoImpl.class);
	
	private String namespace = "SqlMapRestFile.";
	
	@Autowired
	public RestFileDaoImpl(SqlMapClient sqlMapClient) {
		super.setSqlMapClient(sqlMapClient);
	}

	@Override
	public NvRestFile selectRestFile(NvRestFile nvRestFile) {
		return (NvRestFile) getSqlMapClientTemplate().queryForObject(namespace + "selectRestFile",nvRestFile);
	}
	
	@Override
	public List selectRestFileList(String id) {
		return getSqlMapClientTemplate().queryForList(namespace + "selectRestFile",id);
	}


	@Override
	public void insertRestFile(NvRestFile nvRestFile) {
		getSqlMapClientTemplate().insert(namespace + "insertRestFile",nvRestFile);
	}

	@Override
	public void deleteRestFile(NvRestFile nvRestFile) {
		getSqlMapClientTemplate().delete(namespace + "insertRestFile",nvRestFile);
	}

	
	
	

	
}
