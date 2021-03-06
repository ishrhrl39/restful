CREATE TABLE `NVREALTIMEACCEPT` (
  `SEQ` varchar(100) NOT NULL,
  `ECARE_NO` decimal(15,0) NOT NULL,
  `RESULT_SEQ` decimal(16,0) DEFAULT NULL,
  `LIST_SEQ` varchar(10) DEFAULT NULL,
  `CHANNEL` varchar(2) NOT NULL,
  `SVC_ID` varchar(20) DEFAULT NULL,
  `REQ_USER_ID` varchar(50) DEFAULT NULL,
  `REQ_DEPT_ID` varchar(50) DEFAULT NULL,
  `REQ_DT` varchar(8) NOT NULL,
  `REQ_TM` varchar(6) NOT NULL,
  `TMPL_TYPE` char(1) NOT NULL,
  `RECEIVER_ID` varchar(50) NOT NULL,
  `RECEIVER_NM` varchar(50) NOT NULL,
  `RECEIVER` varchar(100) NOT NULL,
  `SENDER_NM` varchar(50) NOT NULL,
  `SENDER` varchar(100) NOT NULL,
  `SUBJECT` varchar(250) DEFAULT NULL,
  `SEND_FG` char(1) DEFAULT 'R',
  `SLOT1` varchar(100) DEFAULT NULL,
  `SLOT2` varchar(100) DEFAULT NULL,
  `SECU_KEY` varchar(13) DEFAULT NULL,
  `SECURITY_PATH` varchar(1000) DEFAULT NULL,
  `ERROR_MSG` varchar(250) DEFAULT NULL,
  `RESERVED_DATE` varchar(14) DEFAULT NULL,
  `PREVIEW_TYPE` char(1) NOT NULL DEFAULT 'N',
  `DATA_CNT` decimal(3,0) DEFAULT NULL,
  `FILE_PATH1` varchar(250) DEFAULT NULL,
  `FILE_PATH2` varchar(250) DEFAULT NULL,
  `FILE_PATH3` varchar(250) DEFAULT NULL,
  `SRFIDD` varchar(50) DEFAULT NULL,
  `JONMUN` longtext,
  PRIMARY KEY (`REQ_USER_ID`,`SEQ`),
  KEY `IDX_NVREALTIMEACCEPT_SEND_FG` (`SEND_FG`),
  KEY `IDX_NVREALTIMEACCEPT_REQ_DT` (`REQ_USER_ID`,`REQ_DT`),
  KEY `IDX_NVREALTIMEACCEPT_ENO_RSEQ` (`ECARE_NO`,`RESULT_SEQ`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



CREATE TABLE NVRESTUSER(
	ID VARCHAR(50) PRIMARY KEY COMMENT '아이디',
	NAME VARCHAR(50) NOT NULL COMMENT '이름',
	PASSWORD VARCHAR(100) NOT NULL COMMENT '비밀번호',
	REG_DTM VARCHAR(14) NOT NULL COMMENT '등록일자',
	USE_YN VARCHAR(1) NOT NULL DEFAULT 'Y' COMMENT '사용여부'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '고객사정보';
insert into NVRESTUSER values ('test','test','7103EF6BC28112F768609DBADFBFC7C1', '20191231000000','Y');

CREATE TABLE NVCONTRACT(
	ID VARCHAR(50) COMMENT '아이디',
	CHANNEL VARCHAR(2) NOT NULL DEFAULT 'M' COMMENT '채널',
	USE_YN VARCHAR(1) NOT NULL DEFAULT 'Y' COMMENT '계약여부',
	PRIMARY KEY(ID, CHANNEL)	
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '계약정보';

CREATE TABLE NVRESTECAREMAP(
	CHANNEL_TYPE VARCHAR(3) PRIMARY KEY COMMENT '채널정보',
	ECARE_NO decimal(15,0) NOT NULL 
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '채널별 사용 이케어번호 관리';


INSERT INTO NVRESTECAREMAP VALUES ('M', 1);		-- 이메일
INSERT INTO NVRESTECAREMAP VALUES ('SM', 2);		-- 보안이메일
INSERT INTO NVRESTECAREMAP VALUES ('S', 3);		-- SMS
INSERT INTO NVRESTECAREMAP VALUES ('L', 4);		-- LMS
INSERT INTO NVRESTECAREMAP VALUES ('T', 5);		-- MMS
INSERT INTO NVRESTECAREMAP VALUES ('A', 6);		-- 알림톡
INSERT INTO NVRESTECAREMAP VALUES ('C', 7);		-- 친구톡

CREATE TABLE NVRESTFILE(
	ID VARCHAR(50) COMMENT '고객아이디',
	FILE_ID VARCHAR(50) COMMENT '파일아이디',
	FILE_NM VARCHAR(100) NOT NULL DEFAULT '' COMMENT '파일명',
	FILE_PATH VARCHAR(250) NOT NULL DEFAULT '' COMMENT '파일경로',
	REG_DTM VARCHAR(14) NOT NULL DEFAULT '' COMMENT '업로드날짜',
	PRIMARY KEY(ID, FILE_ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '첨부파일 관리';
INSERT INTO NVRESTECAREMAP VALUES ('C', 7);		-- 친구톡


INSERT INTO NVCONTRACT (ID, CHANNEL, USE_YN) VALUES ('test', 'M', 'Y');
INSERT INTO NVCONTRACT (ID, CHANNEL, USE_YN) VALUES ('test', 'SM', 'Y');
INSERT INTO NVCONTRACT (ID, CHANNEL, USE_YN) VALUES ('test', 'S', 'Y');
INSERT INTO NVCONTRACT (ID, CHANNEL, USE_YN) VALUES ('test', 'L', 'Y');
INSERT INTO NVCONTRACT (ID, CHANNEL, USE_YN) VALUES ('test', 'T', 'Y');
INSERT INTO NVCONTRACT (ID, CHANNEL, USE_YN) VALUES ('test', 'A', 'Y');
INSERT INTO NVCONTRACT (ID, CHANNEL, USE_YN) VALUES ('test', 'C', 'Y');
