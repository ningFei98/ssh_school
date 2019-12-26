package com.woniu.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import com.woniu.pojo.Score;

public class ExcelWriter {
	private static List<String> CELL_HEADS;
	static {
		CELL_HEADS = new ArrayList<String>();
		CELL_HEADS.add("���");
		CELL_HEADS.add("ѧ������");
		CELL_HEADS.add("�ɼ�");
		CELL_HEADS.add("����");
	}
	/**
	 * ����excel��д������
	 * @param dataList
	 * @return
	 */
	public static Workbook exportData(List<Score> dataList){

		Workbook workbook = new SXSSFWorkbook();
		//����sheet��д����ͷ
		Sheet sheet = buildDataSheet(workbook);
		//����ÿ����������
		int rowNum = 0;
		for (Score data:dataList) {
			rowNum++;
			if(data==null) {
				continue;
			}
			Row row = sheet.createRow(rowNum);
			convertDataToRow(data,row);
		}
		return workbook;
	}
	
	/**
	 * ����sheet����д���һ����ͷ
	 * @param workbook
	 * @return
	 */
	private static Sheet buildDataSheet(Workbook workbook) {
		Sheet sheet = workbook.createSheet();
		//������ͷ���
		for (int i = 0; i < CELL_HEADS.size(); i++) {
			sheet.setColumnWidth(i, 4000);
		}
		//����Ĭ���и�
		sheet.setDefaultRowHeight((short)400);
		//������Ԫ����ʽ
		CellStyle cellStyle = buildHeadCellStyle(sheet.getWorkbook());
		//д���һ������
		Row head = sheet.createRow(0);
		for (int i = 0; i < CELL_HEADS.size(); i++) {
			Cell cell = head.createCell(i);
			cell.setCellValue(CELL_HEADS.get(i));
			cell.setCellStyle(cellStyle);
		}
		return sheet;
	}
	 private static CellStyle buildHeadCellStyle(Workbook workbook) {
	        CellStyle style = workbook.createCellStyle();
	        //���뷽ʽ����
	        style.setAlignment(HorizontalAlignment.CENTER);
	        //�߿���ɫ�Ϳ������
	        style.setBorderBottom(BorderStyle.THIN);
	        style.setBottomBorderColor(IndexedColors.BLACK.getIndex()); // �±߿�
	        style.setBorderLeft(BorderStyle.THIN);
	        style.setLeftBorderColor(IndexedColors.BLACK.getIndex()); // ��߿�
	        style.setBorderRight(BorderStyle.THIN);
	        style.setRightBorderColor(IndexedColors.BLACK.getIndex()); // �ұ߿�
	        style.setBorderTop(BorderStyle.THIN);
	        style.setTopBorderColor(IndexedColors.BLACK.getIndex()); // �ϱ߿�
	        //���ñ�����ɫ
	        style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
	        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	        //����������
	        Font font = workbook.createFont();
	        font.setBold(true);
	        style.setFont(font);
	        return style;
	    }

	private static void convertDataToRow(Score data, Row row) {
		// TODO Auto-generated method stub
		int cellNum = 0;
		Cell cell;
		//���
		cell = row.createCell(cellNum++);
		cell.setCellValue(row.getRowNum());
		//����
		cell = row.createCell(cellNum++);
		String name = data.getStudent().getStudentName();
		cell.setCellValue(null==name?"":name);
		//����
		cell = row.createCell(cellNum++);
		String num = String.valueOf(data.getScoreNum());
		cell.setCellValue(null==num?"":num);
		//����
		cell = row.createCell(cellNum++);
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sf.format(data.getScoreDate());;
		cell.setCellValue(null==date?"":date);
	}
	
	
}
