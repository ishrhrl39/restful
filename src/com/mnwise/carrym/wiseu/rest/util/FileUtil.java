package com.mnwise.carrym.wiseu.rest.util;

import java.io.File;

public class FileUtil {
	
	/**
	 * ���͸� ����
	 * @param path
	 * @return bool (true: �����Ϸ�, false: ���� ������)
	 */
	public static boolean mkdir(String path) {
		File directory = new File(path);
		if(!directory.isDirectory()) {
			return directory.mkdir();
		}
		return false;
	}
	

	/**
	 * ���� ���� ���� üũ
	 * @param path
	 * @return bool (true: �����Ϸ�, false: ���� ������)
	 */
	public static boolean isFileExist(String path) {
		return new File(path).exists();
	}
	
	
	
}
