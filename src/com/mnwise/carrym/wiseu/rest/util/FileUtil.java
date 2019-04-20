package com.mnwise.carrym.wiseu.rest.util;

import java.io.File;

public class FileUtil {
	
	/**
	 * 디렉터리 생성
	 * @param path
	 * @return bool (true: 생성완료, false: 생성 미진행)
	 */
	public static boolean mkdir(String path) {
		File directory = new File(path);
		if(!directory.isDirectory()) {
			return directory.mkdir();
		}
		return false;
	}
	

	/**
	 * 파일 존재 여부 체크
	 * @param path
	 * @return boolean (true: 생성완료, false: 생성 미진행)
	 */
	public static boolean isFileExist(String path) {
		return new File(path).exists();
	}
	
	/**
	 * 이미지 파일확장자 체크
	 * @param fileName
	 * @return boolean (true: 이미지 확장자 일치, false : 이미지 확장자가 아님)
	 */
	public static boolean isImageFileExt(String fileName) {
		if(fileName.endsWith(".jpg")|| fileName.endsWith(".jpeg") || fileName.endsWith(".png") || fileName.endsWith(".gif") || fileName.endsWith(".bmp")) {
			return true;
		}
		return false;
	}
	
}
