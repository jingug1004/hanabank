/**
  * Hana Project
  * Copyright 2014 iRush Co.,
  *
  */
package com.hanaph.saleon.common.utils;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 * Class Name : ExcelDownManager.java
 * 설명 : 엑셀 다운로드 하는 공통 common class
 * 
 * Modification Information
 *    수정일      수정자              수정 내용
 *  ------------ -------------- --------------------------------
 *  2015. 1. 9.      slamwin          
 * </pre>
 * 
 * @version : 
 * @author  : slamwin(@irush.co.kr)
 * @since   : 2015. 1. 9.
 */
@SuppressWarnings("deprecation")
public class ExcelDownManager {
	/**
	  * 엑셀다운로드 공통모듈
	  * @param title 파일이름
	  * @param header 제목들
	  * @param content 내용들
	  * @param map 쿼리 리턴값
	  * @param response
	  * @throws Exception
	  */
	public static void ExcelDown(String title, String[] header, String[] content,@SuppressWarnings("rawtypes") List<Map> map, HttpServletResponse response) throws Exception {
		try {
			
			HSSFRow row = null;
			HSSFCell cell = null;
			HSSFWorkbook wb = new HSSFWorkbook();
			
			HSSFSheet sheet = wb.createSheet();
			
			title = StringUtil.nvl(title,"제목없음");
			
			wb.setSheetName(0, title);

			int cellCount = header.length;

			// 제목 라인 구성
			HSSFCellStyle titleCellStyle = wb.createCellStyle();
			titleCellStyle.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
			titleCellStyle.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);
			titleCellStyle.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
			titleCellStyle.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
			titleCellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			titleCellStyle.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
			titleCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
  
			// 제목들
			HSSFRow headerRow = sheet.createRow((short) 0);
			for (int i = 0; i < cellCount; i++) {
				cell = headerRow.createCell(i);
				cell.setCellValue(header[i]);
				cell.setCellStyle(titleCellStyle);
			}

			// 내용 라인 구성
			HSSFCellStyle contentCellStyle = wb.createCellStyle();
			contentCellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			contentCellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			contentCellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
			contentCellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
			contentCellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			contentCellStyle.setFillForegroundColor(HSSFColor.WHITE.index);
			contentCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);

			if (map.size() > 0) {
				for (int i = 0; i < map.size(); i++) {
					row = sheet.createRow((short) (i + 1));
					for (int j = 0; j < content.length; j++) {
						cell = row.createCell(j);
						String cont = "";
						cont = String.valueOf(map.get(i).get(content[j]));
						if ("null".equals(cont)) {
							cell.setCellValue("");
						} else {
							cell.setCellValue(cont);
						}
						cell.setCellStyle(contentCellStyle);
					}
				}
			} else {
				row = sheet.createRow((short) 1);
				for (int i = 0; i < cellCount; i++) {
					cell = row.createCell(i);
					if (i == 0) {
						cell.setCellValue("등록된 데이터가 없습니다.");
					}
					cell.setCellStyle(contentCellStyle);
				}
				sheet.addMergedRegion(new CellRangeAddress(1, 1, 0,cellCount - 1));
			}

			// autoSizeColumn after setColumnWidth setting!!
			for (int i = 0; i < cellCount; i++) {
				sheet.autoSizeColumn(i);
				sheet.setColumnWidth(i, (sheet.getColumnWidth(i)) + 512);
			}

			response.setContentType("application/vnd.ms-excel;charset=utf-8");
			response.setHeader("Content-Disposition", "attachment;filename="
					+ new String((title).getBytes("KSC5601"), "8859_1")
					+ ".xls");
			wb.write(response.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
