package com.mnwise.carrym.wiseu.rest.util;

public class Constants {
	
	/**
	 * 처리 결과코드
	 * @author 이현섭
	 *
	 */
	public static class Result{
		public static final String SUCCESS = "000";	// 성공
		public static final String NO_USER = "600";	// 유저없음
		public static final String NO_VALUE = "500"; // 컬럼값 없음
		public static final String DUPL_SEQ = "501"; // 이미 존재하는 SEQ
		public static final String NOFILE_DB = "502"; // 파일이 DB내 존재하지않음
		public static final String NOFILE_PATH = "503"; // 파일이 경로내 존재하지않음
	}
}
