/**
 * Hana Project
 * Copyright 2014 iRush Co.,
 *
 */
package com.hanaph.saleon.common.utils;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.Map.Entry;

/**
 * <pre>
 * Class Name : ReloadJDBCUtil.java
 * 설명 : dataSource 객체의 정보를 수정하고 바로 반영하는 Class
 * 
 * Modification Information
 *    수정일      수정자              수정 내용
 *  ------------ -------------- --------------------------------
 *  2014. 11. 3.  장일영			 최초생성
 * </pre>
 * 
 * @version :
 * @author : 장일영(goodhi@irush.co.kr)
 * @since : 2014. 11. 3.
 */
public class ReloadJDBCUtil {
	
	/**
	 *  로그 객체 생성
	 */
	private static final Logger logger = Logger.getLogger(ReloadJDBCUtil.class);
	
	/**
	 * 웹 애플리케이션이 실행중인 경로를 가져오기 위해 WebApplicationContext 를 스프링으로부터 가져와 저장한다.
	 */
	private WebApplicationContext webApplicationContext;

	/**
	 * 스프링 프레임웍에서 이 쓰기 전용 메서드에 WebApplicationContext을 셋팅한다.
	 * 
	 * @param w	WebApplicationContext 객체
	 */
	@Autowired
	public void setWebApplicationContext(WebApplicationContext w) {
		webApplicationContext = w;
	}

	/**
	 * <pre>
	 * 1. 개요     : dataSource 객체의 정보를 수정하고 바로 반영한다.
	 * 2. 처리내용 :	dataSource 객체 로드한 후 jdbc 정보 변경한다, 변경된 정보를 프로퍼티 파일에 반영하기 위해 map 변경된 정보를 담는다, 프로퍼티 파일 정보 변경한다
	 * </pre>
	 * @Method Name : reloadDataSourceUserInfo
	 * @param dataSourceName	dataSource 명
	 * @param username	DB 계정 유저명
	 * @param password	DB 계정 패스워드
	 * @param propFilePath	프로퍼티 파일 경로
	 */		
	public void reloadDataSourceUserInfo(String dataSourceName, String driverClassName, String url,
			String username, String password, String propFilePath) {
		logger.info("JDBC 정보변경 및 반영 ======================================================================>");
		
		/*dataSource 객체 로드한 후 jdbc 정보 변경한다*/
		BasicDataSource sdds = (BasicDataSource) webApplicationContext.getBean(dataSourceName);
		sdds.setDriverClassName(driverClassName);
		sdds.setUrl(url);
		sdds.setUsername(username);
		sdds.setPassword(password);
		
		logger.info("username : "+sdds.getUsername());
		logger.debug("password : "+sdds.getPassword());

		/*
		 *  변경된 정보를 프로퍼티 파일에 반영하기 위해 map 변경된 정보를 담는다
		 */
		/*Map<String, String> propsInfo = new HashMap<String, String>();
		propsInfo.put("jdbc.app.dataSourceName", dataSourceName);
		propsInfo.put("jdbc.app.username", username);
		propsInfo.put("jdbc.app.password", password);*/
		
		/*
		 *  프로퍼티 파일 정보 변경한다
		 */
		/*replaceProperties(propFilePath,propsInfo);
		
		logger.info(propFilePath + " 프로퍼티 파일 변경 완료");*/
		
		logger.info("JDBC 정보변경 및 반영 완료 ======================================================================>");
		
	}

	/**
	 * <pre>
	 * 1. 개요     : 프로퍼티 파일 정보 변경
	 * 2. 처리내용 : 프로퍼티 객체를 생성해서 프로퍼티 파일 정보를 읽어들인다, propsInfo에 담긴 내용을 프로퍼티 파일에 replace한다.
	 * </pre>
	 * @Method Name : replaceProperties
	 * @param propFilePath	프로퍼티 파일 위치
	 * @param propsInfo	변경할 프로퍼티 정보를 담은 Map 객체
	 */		
	public void replaceProperties(String propFilePath, Map<String, String> propsInfo) {
		/*
		 *  객체 생성
		 */
		Properties props = new Properties();	//프로퍼티 객체
		InputStream input = null;				//프로퍼티 파일을 읽어드릴 InputStream
		FileOutputStream output = null;			//프로퍼티 파일을 저장할 FileOutputStream

		/*
		 *  프로퍼티 객체를 생성해서 프로퍼티 파일 정보를 읽어들인다.
		 */
		try {
			input = ReloadJDBCUtil.class.getResourceAsStream(propFilePath);
			props.load(input);
		} catch (Exception e) {
			logger.error(propFilePath+" 프로퍼티 파일 로드시 에러 : ", e);
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					logger.error(propFilePath+" 프로퍼티 파일 로드시 에러 : ", e);
				}
			}
		}
		
		/*
		 *  propsInfo에 담긴 내용을 프로퍼티 파일에 replace한다.
		 */
		try {
			output = new FileOutputStream(ReloadJDBCUtil.class.getResource(propFilePath).getPath());

			Set<Entry<String, String>> items = propsInfo.entrySet();		
			Iterator<Entry<String, String>> iterator = items.iterator();
			Entry<String, String> entry = null;
			while (iterator.hasNext()) {
				entry = iterator.next();
				props.setProperty(entry.getKey(), entry.getValue());
			}
			props.store(output, null);
			
		} catch (Exception e) {
			logger.error(propFilePath+" 프로퍼티 파일 저장시 에러 : ", e);
			
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
					logger.error(propFilePath+" 프로퍼티 파일 저장시 에러 : ", e);
				}
			}

		}
	}
	
	/**
	 * <pre>
	 * 1. 개요     : 프로퍼티의 키와 값을 Map에 담아서 리턴한다.
	 * 2. 처리내용 : 프로퍼티 객체를 생성해서 프로퍼티 파일 정보를 읽어들인다. 프로퍼티의 내용을 Map객체에 담는다.
	 * </pre>
	 * @Method Name : getPropertiesForMap
	 * @param propFilePath	프로퍼티 파일 경로
	 * @return	프로퍼티의 키와 값을 담은 Map<String, String>
	 */		
	public Map<String, String> getPropertiesForMap(String propFilePath) {
		/*
		 *  객체 생성
		 */
		Properties props = new Properties();	//프로퍼티 객체
		InputStream input = null;				//프로퍼티 파일을 읽어드릴 InputStream
		Map<String, String> retMap = new HashMap<String, String>();

		try {
			
			/*
			 *  프로퍼티 객체를 생성해서 프로퍼티 파일 정보를 읽어들인다.
			 */
			input = ReloadJDBCUtil.class.getResourceAsStream(propFilePath);
			props.load(input);
			
			/*
			 *  프로퍼티의 내용을 Map객체에 담는다.
			 */
			Set<Entry<Object, Object>> items = props.entrySet();
			Iterator<Entry<Object, Object>> iterator = items.iterator();
			Entry<Object, Object> entry = null;
			while (iterator.hasNext()) {
				entry = iterator.next();
				retMap.put(((String)entry.getKey()).trim(), ((String)entry.getValue()).trim());
			}
			
		} catch (IOException e) {
			logger.error(propFilePath+" 프로퍼티 파일 로드시 에러 : ", e);
			
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					logger.error(propFilePath+" 프로퍼티 파일 로드시 에러 : ", e);
				}
			}
		}
		
		return retMap;
		
	}
}
