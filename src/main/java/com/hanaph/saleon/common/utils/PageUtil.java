/**
  * Hana Project
  * Copyright 2014 iRush Co.,
  *
  */
package com.hanaph.saleon.common.utils;

/**
 * <pre>
 * Class Name : PageUtil.java
 * 설명 : jsp paging를 사용하기 위하여 쓰이는 공통 java class
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
public class PageUtil
{
  public static PageUtil instance = new PageUtil();
  
  public PageUtil()
  {
  }
  
  /**
 * <pre>
 * 1. 개요     : 페이징 처리를 하기 위한 pagingUtilClass
 * 2. 처리내용 : List controller에서 받은 4개의 값으로 page html 구성
 * </pre>
 * @Method Name : autoPaging
 * @param plTotalCnt  게시글 전체 카운트 수
 * @param plRowRange  list에 보여질 row갯수
 * @param plPageRange list에 보여질 page 수 
 * @param plCurrPage  현재 페이지 번호
 * @return tsRetVal.toString()
 */	
  public String autoPaging(int plTotalCnt, int plRowRange, int plPageRange, int plCurrPage)
  {
	  
	long lplTotalCnt  = (long)plTotalCnt;
	long lplRowRange  = (long)plRowRange;
	long lplPageRange  = (long)plPageRange;
	long lplCurrPage  = (long)plCurrPage;
	
    StringBuffer tsRetVal = new StringBuffer();
    if (lplTotalCnt == 0L)
    {
      return "";
    }


    long plPageCnt = lplTotalCnt % lplRowRange;
    if (plPageCnt == 0L)
    {
      plPageCnt = lplTotalCnt / lplRowRange;
    }
    else
    {
      plPageCnt = lplTotalCnt / lplRowRange + 1L;
    }


    tsRetVal.append("<table cellpadding=0 cellspacing=0 border=0>\n");
    tsRetVal.append("<tr>");
    tsRetVal.append("<td>");


    long plRangeCnt = lplCurrPage / lplPageRange;
    if (lplCurrPage % lplPageRange == 0L)
    {
      plRangeCnt = lplCurrPage / lplPageRange - 1L;
    }


    long tlFirstPage = lplCurrPage - lplPageRange;
    if (tlFirstPage > 0)
    {
      tsRetVal.append("<a href=\"javascript:goPage('1');\">");
      tsRetVal.append("<img src=\"/images/btn_pageFirst.gif\" border=0 align=\"absmiddle\" />");
      tsRetVal.append("</a>\n");
    }
    else
    {
      tsRetVal.append("<img src=\"/images/btn_pageFirstR.gif\" border=0 align=\"absmiddle\" />\n");
    }


    if (lplCurrPage > lplPageRange)
    {
      String s2;
      if (lplCurrPage % lplPageRange == 0L)
      {
        s2 = Long.toString(lplCurrPage - lplPageRange);
      }
      else
      {
        s2 = Long.toString(lplCurrPage - lplCurrPage % lplPageRange);
      }
      tsRetVal.append("<a href=\"javascript:goPage('").append(s2).append("');\">");
      tsRetVal.append("<img src=\"/images/btn_pagePrev.gif\" border=0 align=\"absmiddle\" />");
      tsRetVal.append("</a>\n");
    }
    else
    {
      tsRetVal.append("<img src=\"/images/btn_pagePrevR.gif\" border=0 align=\"absmiddle\" />\n");
    }


    for (long index = plRangeCnt * lplPageRange + 1L; index < (plRangeCnt + 1L) * lplPageRange + 1L; index++)
    {
      String tsFontBegin = "<font size=2>";
      String tsFonfEnd = "</font>\n";
      if (index == lplCurrPage)
      {
        tsFontBegin = "<font size=2><b>";
        tsFonfEnd = "</b></font>\n";
      }
      tsRetVal.append(tsFontBegin);
      tsRetVal.append("<a href=\"javascript:goPage('").append(Long.toString(index)).append("');\">").append(Long.toString(index)).append("</a>");
      tsRetVal.append(tsFonfEnd);
      if (index == plPageCnt)
      {
        break;
      }
    }


    if (plPageCnt > (plRangeCnt + 1L) * lplPageRange)
    {
      tsRetVal.append("<a href=\"javascript:goPage('").append(Long.toString((plRangeCnt + 1L) * lplPageRange + 1L)).append("');\" ").append(">");
      tsRetVal.append("<img src=\"/images/btn_pageNext.gif\" border=0 align=\"absmiddle\" />");
      tsRetVal.append("</a>\n");
    }
    else
    {
      tsRetVal.append("<img src=\"/images/btn_pageNextR.gif\" border=0 align=\"absmiddle\" />\n");
    }


    long tlEndPage = lplCurrPage + lplPageRange;
    if (tlEndPage < plPageCnt)
    {
      tsRetVal.append("<a href=\"javascript:goPage('").append(Long.toString(plPageCnt)).append("');\" ").append(">");
      tsRetVal.append("<img src=\"/images/btn_pageEnd.gif\" border=0 align=\"absmiddle\" />");
      tsRetVal.append("</a>\n");
    }
    else
    {
      tsRetVal.append("<img src=\"/images/btn_pageEndR.gif\" border=0 align=\"absmiddle\" />\n");
    }
    tsRetVal.append("</td>");
    tsRetVal.append("</tr>");
    tsRetVal.append("</table>\n");
    
    return tsRetVal.toString();
    
  }
} 


