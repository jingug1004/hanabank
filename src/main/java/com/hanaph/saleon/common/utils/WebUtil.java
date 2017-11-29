/**
  * Hana Project
  * Copyright 2014 iRush Co.,
  *
  */
package com.hanaph.saleon.common.utils;

import com.hanaph.saleon.member.vo.LoginUserVO;
import com.hanaph.saleon.mgmt.dao.MgmtProgramDAO;
import com.hanaph.saleon.mgmt.vo.MgmtButtonVO;
import com.hanaph.saleon.mgmt.vo.MgmtProgramVO;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * <pre>
 * Class Name : WebUtil.java
 * 설명 : web에서 사용되는 공통 클래스 게시판 n이미지 및 버튼을 string으로 출력 하는 method로 구성 된다.
 * 
 * Modification Information
 *    수정일      수정자              수정 내용
 *  ------------ -------------- --------------------------------
 *  2014. 10. 17. slamwin		생성          
 * </pre>
 * 
 * @version : 
 * @author slawin(@irush.co.kr)
 * @since   : 2014. 10. 20.
 */
public class WebUtil {
	
	/**
	 * <pre>
	 * 1. 개요     : 게시판 목록에서 최신글 New 이미지 노출여부를 리턴한다.(등록일 3일 기준) 
	 * 2. 처리내용 : 
	 * @param newsDate 
	 * @return boolean
	 * </pre>
	 * @Method Name : getNewImage
	 * @param newsDate
	 * @return
	 */		
	public static boolean getNewImage(String newsDate){
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
		Calendar cal = new GregorianCalendar();
		cal.add(Calendar.DATE, -3);
		Date date = cal.getTime();
		
		String strCurrentDate = simpleDateFormat.format(date);
		String strNewsDate = StringUtil.nvl2(StringUtil.translate(newsDate, "-", ""), "0");
		int intCurrentDate = Integer.parseInt(strCurrentDate);
		int intNewsDate = Integer.parseInt(strNewsDate);
		
		if(intNewsDate > intCurrentDate){
			return true;
		}
		
		return false;
	}
	
	/**
	 * <pre>
	 * 1. 개요     : 현재 유저의 페이지별 버튼 권한과 버튼 정보를 조회해서 html로 버튼을 생성.
	 * 2. 처리내용 :	jsp에서 사용시.. <%=WebUtil.createButtonArea("00058", "cm_")%>
	 * </pre>
	 * @Method Name : createButtonArea
	 * @param pgm_no	해당 화면의 프로그램 pgm_no
	 * @param areaPrefix	버튼을 생성하는 위치 예약어
	 * @return	버튼들을 생성하는 html
	 */		
	public static String createButtonArea(String pgm_no, String areaPrefix){
		
		/*
		 * 파라메터가 없으면 공백 문자열 리턴
		 */
		if(pgm_no == null || areaPrefix == null || pgm_no.isEmpty() || areaPrefix.isEmpty()){
			return "";
		}
		
		/*
		 * 현재 요청중인 thread local의 HttpServletRequest 객체 가져오기
		 */
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		
		/*
		 *  세션에서 유저 정보 가져오기
		 */
		LoginUserVO loginUserVO = (LoginUserVO)WebUtils.getSessionAttribute(request, "onlineUser");
		
		/*
		 * Spring Context 가져오기
		 */
		WebApplicationContext wContext = WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
		 
		/*
		 * 스프링 빈 가져오기 & casting
		 */
		MgmtProgramDAO sBean = (MgmtProgramDAO)wContext.getBean("mgmtProgramDao");
		
		/*
		 *  사용자별 프로그램별 버튼리스트 조회
		 */
		MgmtProgramVO paramVO = new MgmtProgramVO();
		paramVO.setEmp_code(loginUserVO.getEmp_code());		//사원 코드
		paramVO.setPgm_no(pgm_no);							//페이지 번호
		paramVO.setUse_btn(areaPrefix);						//버튼 영역 prefix값
		List<MgmtButtonVO> btnList = sBean.getBtnAuthInPgmByUser(paramVO);
		
		/*
		 *  조회한 버튼리스트를 루프돌면서 버튼 사용 권한을 체크하면서 html을 생성한다.
		 */
		StringBuffer sb = new StringBuffer();			//html이 담겨질 StringBuffer
		StringBuffer sbClassName = new StringBuffer();	//class name이 담겨질 StringBuffer
		String[] arrBtnIds = null;
		boolean isMyBtn = false;
		for(MgmtButtonVO btnVO : btnList){
			if(btnVO.getUse_btn() != null && btnVO.getBtn_id() != null){
				if(btnVO.getBtn_id() != null && btnVO.getBtn_id().trim().indexOf(areaPrefix) == 0 ){
					
					/*
					 * 버튼에 사용되는 class name 생성
					 */
					sbClassName.setLength(0);
					arrBtnIds = btnVO.getBtn_id().split("\\_");
					if(arrBtnIds.length > 2){
						for(int i = 2; i < arrBtnIds.length; i++){
							if(sbClassName.length() > 0){
								sbClassName.append("_").append(arrBtnIds[i]);
							} else {
								sbClassName.append(arrBtnIds[i]);
							}
						}
					}
					
					/*
					 *  현재 버튼이 유저에게 권한이 있는지 확인.
					 */
					isMyBtn = false;
					for(String showBtnId : btnVO.getUse_btn().split(",")){
						if(showBtnId.trim().equalsIgnoreCase(btnVO.getBtn_id().trim())){
							isMyBtn = true;
							break;
						}
					}
					
					/*
					 *  html 생성. 권한이 없는 버튼은 disabled로 버튼 기능을 비활성화 시킴.
					 */
					if(isMyBtn){
						sb.append("<button type=\"button\" id='").append(btnVO.getBtn_id()).append("' class='").append(sbClassName.toString()).append("'>").append(StringUtils.defaultString(btnVO.getBtn_nm(), btnVO.getBtn_id())).append("</button> ");
					} else {
						sb.append("<button type=\"button\" disabled='disabled' id='").append(btnVO.getBtn_id()).append("' class='").append(sbClassName.toString()).append("'>").append(StringUtils.defaultString(btnVO.getBtn_nm(), btnVO.getBtn_id())).append("</button> ");
					}
				}
				
			}
		}
		
		return sb.toString();
	}
}
