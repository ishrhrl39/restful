package com.mnwise.carrym.wiseu.rest.util;

import java.io.File;

public class FileUtil {
	
	/**
	 * 叼泛磐府 积己
	 * @param path
	 * @return bool (true: 积己肯丰, false: 积己 固柳青)
	 */
	public static boolean mkdir(String path) {
		File directory = new File(path);
		if(!directory.isDirectory()) {
			return directory.mkdir();
		}
		return false;
	}
	

	/**
	 * 颇老 粮犁 咯何 眉农
	 * @param path
	 * @return bool (true: 积己肯丰, false: 积己 固柳青)
	 */
	public static boolean isFileExist(String path) {
		return new File(path).exists();
	}
	
	
	
}
