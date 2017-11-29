package com.hanaph.saleon.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * <pre>
 * Class Name : CommonController.java
 * 설명 : 공통적으로 쓰이는 COMMON class 공통 프린트 method 구성
 * 
 * Modification Information
 *    수정일      수정자              수정 내용
 *  ------------ -------------- --------------------------------
 *  2014. 11. 7.      Beomjin          
 * </pre>
 * 
 * @version : 
 * @author  : Beomjin(@irush.co.kr)
 * @since   : 2014. 11. 7.
 */
@Controller
public class CommonController {
	
	/**
	 * <pre>
	 * 1. 개요     : 
	 * 2. 처리내용 :
	 * </pre>
	 * @Method Name : commonPrint
	 * @param request
	 * @return
	 */		
	@RequestMapping("/common/commonPrint.do")
	public ModelAndView commonPrint(HttpServletRequest request){
		
		ModelAndView mav = new ModelAndView("common/commonPrint");
		
		return mav;
	}
}
