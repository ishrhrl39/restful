package com.mnwise.carrym.wiseu.rest.listener;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * ���ʿ� application.properties �׸���� �Ѳ����� �о�´�
 * @author �ѿ���
 *
 */
public class PropertiesListener {
	
	// ������Ƽ ��ü ����
    static Properties props = null;
    
    FileInputStream fis = null;
	
	public PropertiesListener() {
		String classPathRoot = this.getClass().getResource("/").toString();		// ~/classes
		classPathRoot = classPathRoot.substring(0, classPathRoot.indexOf("/WEB-INF") + 8).replace("file:/", "");
		
		try{
            // ������Ƽ ���� ��ġ
            String propFile = classPathRoot + "/conf/application.properties";
            props = new Properties();
             
            // ������Ƽ ���� ��Ʈ���� ���
            fis = new FileInputStream(propFile);
             
            // ������Ƽ ���� �ε�
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
