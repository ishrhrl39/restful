package com.mnwise.carrym.wiseu.rest.send.model;

import java.lang.reflect.Field;

public class NvRealtimeAccept {
	private String SEQ;
	private int ECARE_NO;
	private int RESULT_SEQ;
	private String LIST_SEQ;
	private String CHANNEL;
	private String SVC_ID;
	private String REQ_USER_ID;
	private String REQ_DEPT_ID;
	private String REQ_DT;
	private String REQ_TM;
	private String TMPL_TYPE;
	private String RECEIVER_ID;
	private String RECEIVER_NM;
	private String RECEIVER;
	private String SENDER_NM;
	private String SENDER;
	private String SUBJECT;
	private String SEND_FG;
	private String SLOT1;
	private String SLOT2;
	private String SECU_KEY;
	private String SECURITY_PATH;
	private String ERROR_MSG;
	private String RESERVED_DATE;
	private String PREVIEW_TYPE;
	private int DATA_CNT;
	private String FILE_PATH1;
	private String FILE_PATH2;
	private String FILE_PATH3;
	private String SRFIDD;
	private String JONMUN;
	
	
	public String getSEQ() {
		return SEQ;
	}
	public void setSEQ(String sEQ) {
		SEQ = sEQ;
	}
	public int getECARE_NO() {
		return ECARE_NO;
	}
	public void setECARE_NO(int eCARE_NO) {
		ECARE_NO = eCARE_NO;
	}
	public int getRESULT_SEQ() {
		return RESULT_SEQ;
	}
	public void setRESULT_SEQ(int rESULT_SEQ) {
		RESULT_SEQ = rESULT_SEQ;
	}
	public String getLIST_SEQ() {
		return LIST_SEQ;
	}
	public void setLIST_SEQ(String lIST_SEQ) {
		LIST_SEQ = lIST_SEQ;
	}
	public String getCHANNEL() {
		return CHANNEL;
	}
	public void setCHANNEL(String cHANNEL) {
		CHANNEL = cHANNEL;
	}
	public String getSVC_ID() {
		return SVC_ID;
	}
	public void setSVC_ID(String sVC_ID) {
		SVC_ID = sVC_ID;
	}
	public String getREQ_USER_ID() {
		return REQ_USER_ID;
	}
	public void setREQ_USER_ID(String rEQ_USER_ID) {
		REQ_USER_ID = rEQ_USER_ID;
	}
	public String getREQ_DEPT_ID() {
		return REQ_DEPT_ID;
	}
	public void setREQ_DEPT_ID(String rEQ_DEPT_ID) {
		REQ_DEPT_ID = rEQ_DEPT_ID;
	}
	public String getREQ_DT() {
		return REQ_DT;
	}
	public void setREQ_DT(String rEQ_DT) {
		REQ_DT = rEQ_DT;
	}
	public String getREQ_TM() {
		return REQ_TM;
	}
	public void setREQ_TM(String rEQ_TM) {
		REQ_TM = rEQ_TM;
	}
	public String getTMPL_TYPE() {
		return TMPL_TYPE;
	}
	public void setTMPL_TYPE(String tMPL_TYPE) {
		TMPL_TYPE = tMPL_TYPE;
	}
	public String getRECEIVER_ID() {
		return RECEIVER_ID;
	}
	public void setRECEIVER_ID(String rECEIVER_ID) {
		RECEIVER_ID = rECEIVER_ID;
	}
	public String getRECEIVER_NM() {
		return RECEIVER_NM;
	}
	public void setRECEIVER_NM(String rECEIVER_NM) {
		RECEIVER_NM = rECEIVER_NM;
	}
	public String getRECEIVER() {
		return RECEIVER;
	}
	public void setRECEIVER(String rECEIVER) {
		RECEIVER = rECEIVER;
	}
	public String getSENDER_NM() {
		return SENDER_NM;
	}
	public void setSENDER_NM(String sENDER_NM) {
		SENDER_NM = sENDER_NM;
	}
	public String getSENDER() {
		return SENDER;
	}
	public void setSENDER(String sENDER) {
		SENDER = sENDER;
	}
	public String getSUBJECT() {
		return SUBJECT;
	}
	public void setSUBJECT(String sUBJECT) {
		SUBJECT = sUBJECT;
	}
	public String getSEND_FG() {
		return SEND_FG;
	}
	public void setSEND_FG(String sEND_FG) {
		SEND_FG = sEND_FG;
	}
	public String getSLOT1() {
		return SLOT1;
	}
	public void setSLOT1(String sLOT1) {
		SLOT1 = sLOT1;
	}
	public String getSLOT2() {
		return SLOT2;
	}
	public void setSLOT2(String sLOT2) {
		SLOT2 = sLOT2;
	}
	public String getSECU_KEY() {
		return SECU_KEY;
	}
	public void setSECU_KEY(String sECU_KEY) {
		SECU_KEY = sECU_KEY;
	}
	public String getSECURITY_PATH() {
		return SECURITY_PATH;
	}
	public void setSECURITY_PATH(String sECURITY_PATH) {
		SECURITY_PATH = sECURITY_PATH;
	}
	public String getERROR_MSG() {
		return ERROR_MSG;
	}
	public void setERROR_MSG(String eRROR_MSG) {
		ERROR_MSG = eRROR_MSG;
	}
	public String getRESERVED_DATE() {
		return RESERVED_DATE;
	}
	public void setRESERVED_DATE(String rESERVED_DATE) {
		RESERVED_DATE = rESERVED_DATE;
	}
	public String getPREVIEW_TYPE() {
		return PREVIEW_TYPE;
	}
	public void setPREVIEW_TYPE(String pREVIEW_TYPE) {
		PREVIEW_TYPE = pREVIEW_TYPE;
	}
	public int getDATA_CNT() {
		return DATA_CNT;
	}
	public void setDATA_CNT(int dATA_CNT) {
		DATA_CNT = dATA_CNT;
	}
	public String getFILE_PATH1() {
		return FILE_PATH1;
	}
	public void setFILE_PATH1(String fILE_PATH1) {
		FILE_PATH1 = fILE_PATH1;
	}
	public String getFILE_PATH2() {
		return FILE_PATH2;
	}
	public void setFILE_PATH2(String fILE_PATH2) {
		FILE_PATH2 = fILE_PATH2;
	}
	public String getFILE_PATH3() {
		return FILE_PATH3;
	}
	public void setFILE_PATH3(String fILE_PATH3) {
		FILE_PATH3 = fILE_PATH3;
	}
	public String getSRFIDD() {
		return SRFIDD;
	}
	public void setSRFIDD(String sRFIDD) {
		SRFIDD = sRFIDD;
	}
	public String getJONMUN() {
		return JONMUN;
	}
	public void setJONMUN(String jONMUN) {
		JONMUN = jONMUN;
	}

	/**
	 * 파일 각 인덱스마다 파일경로를 삽입
	 * @param index
	 * @param file_PATH
	 */
	public void setFILE_PATH(int index, String filePath) {
		try {
			Field f = this.getClass().getDeclaredField("FILE_PATH" + index);
			f.setAccessible(true);
			f.set(this, filePath);
		} catch (NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		NvRealtimeAccept accept = new NvRealtimeAccept(); 
		for(int i=1; i<=3; i++) {
			accept.setFILE_PATH(i, "test" + i);
		}
		System.out.println(accept.getFILE_PATH1());
		System.out.println(accept.getFILE_PATH2());
		System.out.println(accept.getFILE_PATH3());
	}
	
}
