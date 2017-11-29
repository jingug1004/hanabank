/**
  * Hana Project
  * Copyright 2014 iRush Co.,
  *
  */
package com.hanaph.saleon.common.utils;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;

import java.net.URL;

/**
 * <pre>
 * Class Name : Environment.java
 * 설명 : 환경변수 가져오는 Class
 * 
 * Modification Information
 *    수정일      수정자              수정 내용
 *  ------------ -------------- --------------------------------
 *  2014. 10. 17. slamwin		생성
 * </pre>
 * 
 * @version 1.0
 * @author slawin(@irush.co.kr)
 * @since 2014. 10. 17.
 */
public class Environment {

	private static PropertiesConfiguration config = null;
	
	private static PropertiesConfiguration database = null;

	/**
	 * Environment.java 생성자
	 */
	public Environment() {
		
		try {
			// server 시작시 vm 옵션중에 server.type값을 기준으로 서버타입을 구분해서 프로퍼티 파일을 읽어드림
			String propPath = "";
			String dbPropPath = "";
			if(System.getProperty("server.type") != null && !System.getProperty("server.type").isEmpty()){
				propPath = "/com/hanaph/saleon/properties/config_"+System.getProperty("server.type")+".properties";
				dbPropPath = "/com/hanaph/saleon/properties/database_"+System.getProperty("server.type")+".properties";
			} else {
				propPath = "/com/hanaph/saleon/properties/config_local.properties";
				dbPropPath = "/com/hanaph/saleon/properties/database_local.properties";
			}
			 
			
			// db url 정보 가져오기 start
			ClassLoader dbCl = this.getClass().getClassLoader();
			URL dbUrl = dbCl.getResource(dbPropPath);

			database = new PropertiesConfiguration(dbUrl);
			String jdbc_url = database.getString("jdbc.url", "");
			// db url 정보 가져오기 end
			
			ClassLoader cl = this.getClass().getClassLoader();
			URL url = cl.getResource(propPath);

			config = new PropertiesConfiguration(url);
			config.setProperty("jdbc.url", jdbc_url); // db url 정보 set
			
			FileChangedReloadingStrategy strategy = new FileChangedReloadingStrategy();
			strategy.setRefreshDelay(500);
			config.setReloadingStrategy(strategy);
			
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * properties 파일에서 key 값에 대한 value 값을 리턴한다.
	 * @param strKey
	 * @return String
	 */
	public String getValue(String strKey) {
		if(null == config) {
			new Environment();
		}
		return config.getString(strKey, "");
	}

}
