/**
 * Hana Project
 * Copyright 2014 iRush Co.,
 *
 */
package com.hanaph.saleon.vo;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.hanaph.saleon.member.vo.LoginUserVO;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.hanaph.saleon.common.utils.Environment;
import com.hanaph.saleon.mgmt.vo.MgmtProgramVO;

/**
 * <pre>
 * Class Name : SessionUserInfoVO.java
 * 설명 : 세션 스코프드 빈 객체로 사용자의 로그인 정보 및 메뉴 권한들을 저장하는 VO로 세션별로 생성된다.
 * 
 * Modification Information
 *    수정일      수정자              수정 내용
 *  ------------ -------------- --------------------------------
 *  2014. 12. 5.      장일영            최초생성
 * </pre>
 * 
 * @version : 1.0
 * @author  : 장일영(goodhi@irush.co.kr)
 * @since   : 2014. 12. 5.
 */
@Scope(value="session")
@Component("sessionUserInfoVO")
public class SessionUserInfoVO {
	
	/**
	 * 환경변수
	 */
	private Environment env = new Environment();	
	
	/**
	 * 사용자별 메뉴 권한 리스트
	 */
	List<MgmtProgramVO> menuList;
	
	/**
	 * 사용자 유저 정보
	 */
	LoginUserVO loginUserVO;

	/**
	 * @return the menuList
	 */
	public List<MgmtProgramVO> getMenuList() {
		return menuList;
	}

	/**
	 * @param menuList the menuList to set
	 */
	public void setMenuList(List<MgmtProgramVO> menuList) {
		this.menuList = menuList;
	}
	
	/**
	 * @return the loginUserVO
	 */
	public LoginUserVO getLoginUserVO() {
		return loginUserVO;
	}

	/**
	 * @param loginUserVO the loginUserVO to set
	 */
	public void setLoginUserVO(LoginUserVO loginUserVO) {
		this.loginUserVO = loginUserVO;
	}

	/**
	 * <pre>
	 * 1. 개요     : 상위 메뉴 코드를 받아서 하위 메뉴를 gnb에 사용되는 html로 만들어서 리턴한다.
	 * 2. 처리내용 :	상위 메뉴 코드를 받아서 하위 메뉴를 gnb에 사용되는 html로 만들어서 리턴한다.
	 * </pre>
	 * @Method Name : getMenuHtml
	 * @param parentPgmCode	상위 메뉴 코드
	 * @return	html
	 */		
	public String getGnbMenuHtml(String parentPgmCode){
		
		StringBuffer sb = new StringBuffer();	//메뉴 html을 저장할 StringBuffer
		
		/*
		 *  gnb html 생성
		 */
		if(this.menuList != null){
			for(MgmtProgramVO vo : this.menuList){
				if(parentPgmCode.equals(vo.getParent_pgm()) && "Y".equals(vo.getMenu_use_yn())){
					sb.append("<li><a href=\"javascript:Commons.addTab('"+vo.getPgm_name()+"','"+env.getValue("root_dir.url")+vo.getPgm_id()+"');\">"+vo.getPgm_name()+"</a></li>");
				}
			}
		}
		
		return sb.toString();
		
	}
	
	/**
	 * <pre>
	 * 1. 개요     : 현재 요청한 Request의 URI이 현재 유저가 접근 가능한 URL인지 확인해서 결과를 리턴한다.
	 * 2. 처리내용 :	현재 요청한 Request의 URI이 현재 유저가 접근 가능한 URL인지 확인해서 결과를 리턴한다.
	 * </pre>
	 * @Method Name : isAccessibleUrl
	 * @param uri	요청한 URI
	 * @return	true : 권한있음, false : 권한없음
	 */		
	public boolean isAccessibleUrl(String uri){
		boolean isPass = false;		//URI에 대한 권한 여부
		
		/*
		 *  권한 체크 안 해도 되는 uri
		 *  com/hanaph/saleon/properties/config_dev.properties 프로퍼티 파일에 저장해놓음.
		 */
		String[] noCheckUri = env.getValue("not_auth_check.uri").split("\\:");
		for(String str : noCheckUri){
			Pattern p = Pattern.compile("(?i)^"+str.replaceAll("\\*", ".*")+"$");
			Matcher m = p.matcher(uri.replaceFirst(env.getValue("root_dir.context_path"), ""));
			if(m.matches()){
				return true;
			}
		}
		
		/*
		 *  권한 체크.
		 *  현재 유저가 전근 가능한 메뉴(프로그램)의 id와 uri와 비교해서 같은 것이 있다면 isPass를 true로 변경
		 */
		if(this.menuList != null){
			for(MgmtProgramVO vo : this.menuList){
				if(vo.getPgm_id() != null && vo.getPgm_id().equalsIgnoreCase(uri.replaceFirst(env.getValue("root_dir.context_path"), ""))){
					isPass = true;
					break;
				}
			}
		}
		return isPass;
	}
	
	/**
	 * <pre>
	 * 1. 개요     : uri를 기준으로 프로그램의 pgmNo을 조회
	 * 2. 처리내용 :	uri를 기준으로 프로그램의 pgmNo을 조회해서 리턴한다.
	 * </pre>
	 * @Method Name : getPgmNo
	 * @param uri	요청한 URI
	 * @return	uri에 대응되는 프로그램의 pgmNo
	 */		
	public String getPgmNo(String uri){
		String pgmNo = "";	//프로그램 코드
		
		/*
		 *  현재 유저가 전근 가능한 메뉴(프로그램)의 id와 uri와 비교해서 같은 것이 있다면 pgmNo에 프로그램 코드를 저장함
		 */
		if(this.menuList != null){
			for(MgmtProgramVO vo : this.menuList){
				if(vo.getPgm_id() != null && vo.getPgm_id().equalsIgnoreCase(uri.replaceFirst(env.getValue("root_dir.context_path"), ""))){
					pgmNo = vo.getPgm_no();
					break;
				}
			}
		}
		return pgmNo;
	}

}
