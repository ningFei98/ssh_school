package com.woniu.utils;

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
import org.apache.xmlbeans.impl.common.SystemCache;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STString;

import com.woniu.pojo.Student;

public class StudentExcelWriter {
	private static List<String> CELL_HEADS;
	static {
		CELL_HEADS = new ArrayList<String>();
		CELL_HEADS.add("���");
		CELL_HEADS.add("ѧ������");
		CELL_HEADS.add("ѧ��");
		CELL_HEADS.add("���ڰ༶");
		CELL_HEADS.add("�Ա�");
		CELL_HEADS.add("�绰");
		CELL_HEADS.add("��ַ");
		CELL_HEADS.add("ѧ��");
		CELL_HEADS.add("��ҵѧУ");
		CELL_HEADS.add("רҵ");
	}
	/**
	 * ����excel��д������
	 * @param dataList
	 * @return
	 */
	public static Workbook exportData(List<Student> dataList){

		Workbook workbook = new SXSSFWorkbook();
		//����sheet��д����ͷ
		Sheet sheet = buildDataSheet(workbook);
		//����ÿ����������
		int rowNum = 0;
		for (Student data:dataList) {
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

	private static void convertDataToRow(Student data, Row row) {
		// TODO Auto-generated method stub
		int cellNum = 0;
		Cell cell;
		//���
		cell = row.createCell(cellNum++);
		cell.setCellValue(row.getRowNum());
		//����
		cell = row.createCell(cellNum++);
		String name = data.getStudentName();
		cell.setCellValue(null==name?"":name);
		//ѧ��
		cell = row.createCell(cellNum++);
		String studentCode = data.getSutdentCode();
		cell.setCellValue(null==studentCode?"":studentCode);
		//���ڰ༶
		cell = row.createCell(cellNum++);
		String studentClazz = data.getClazz().getClazzName() + data.getClazz().getClazzType(); 
		cell.setCellValue(null==studentClazz?"":studentClazz);
		//�Ա�
		cell = row.createCell(cellNum++);
		String studentSex = data.getStudentSex();
		cell.setCellValue(null==studentSex?"":studentSex);
		//�绰
		cell = row.createCell(cellNum++);
		String studentPhone = data.getStudentPhone();
		cell.setCellValue(null==studentPhone?"":studentPhone);
		//��ַ
		cell = row.createCell(cellNum++);
		String studentAddress = data.getStudentAddress();
		cell.setCellValue(null==studentAddress?"":studentAddress);
		//ѧ��
		cell = row.createCell(cellNum++);
		String studentEdu = data.getStudentEdu();
		cell.setCellValue(null==studentEdu?"":studentEdu);
		//��ҵѧУ
		cell = row.createCell(cellNum++);
		String studentCollege = data.getStudentCollege();
		cell.setCellValue(null==studentCollege?"":studentCollege);
		//רҵ
		cell = row.createCell(cellNum++);
		String studentProfessional = data.getStudentProfessional();
		cell.setCellValue(null==studentProfessional?"":studentProfessional);
//		//����
//		cell = row.createCell(cellNum++);
//		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
//		String date = sf.format(data.getScoreDate());;
//		cell.setCellValue(null==date?"":date);
	}
	
	
}
