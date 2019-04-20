package com.mnwise.carrym.wiseu.rest.listener;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * 최초에 application.properties 항목들을 한꺼번에 읽어온다
 * @author 한예나
 *
 */
public class PropertiesListener {
	
	// 프로퍼티 객체 생성
    static Properties props = null;
    
    FileInputStream fis = null;
	
	public PropertiesListener() {
		String classPathRoot = this.getClass().getResource("/").toString();		// ~/classes
		classPathRoot = classPathRoot.substring(0, classPathRoot.indexOf("/WEB-INF") + 8).replace("file:/", "");
		
		try{
            // 프로퍼티 파일 위치
            String propFile = classPathRoot + "/conf/application.properties";
            props = new Properties();
             
            // 프로퍼티 파일 스트림에 담기
            fis = new FileInputStream(propFile);
             
            // 프로퍼티 파일 로딩
            props.load(new java.io.BufferedInputStream(fis));
        }catch(Exception e){
            e.printStackTrace();
        }finally {
			try {
				if(fis != null) fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
	}
	
	
	public static String getPropertyValue(String key) {
		return props.getProperty(key);
	}
	
}
