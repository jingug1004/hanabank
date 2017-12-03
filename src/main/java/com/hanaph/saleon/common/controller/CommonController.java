package com.hanaph.saleon.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

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
 * @author : Beomjin(@irush.co.kr)
 * @version :
 * @since : 2014. 11. 7.
 */
@Controller
public class CommonController {

    /**
     * <pre>
     * 1. 개요     :
     * 2. 처리내용 :
     * </pre>
     *
     * @param request
     * @return
     * @Method Name : commonPrint
     */
    @RequestMapping("/common/commonPrint.do")
    public ModelAndView commonPrint(HttpServletRequest request) {

        ModelAndView mav = new ModelAndView("common/commonPrint");

        return mav;
    }
}
