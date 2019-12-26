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
		CELL_HEADS.add("序号");
		CELL_HEADS.add("学生姓名");
		CELL_HEADS.add("学号");
		CELL_HEADS.add("所在班级");
		CELL_HEADS.add("性别");
		CELL_HEADS.add("电话");
		CELL_HEADS.add("地址");
		CELL_HEADS.add("学历");
		CELL_HEADS.add("毕业学校");
		CELL_HEADS.add("专业");
	}
	/**
	 * 生成excel并写入数据
	 * @param dataList
	 * @return
	 */
	public static Workbook exportData(List<Student> dataList){

		Workbook workbook = new SXSSFWorkbook();
		//生成sheet，写入列头
		Sheet sheet = buildDataSheet(workbook);
		//构建每行数据内容
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
	 * 生成sheet表，并写入第一行列头
	 * @param workbook
	 * @return
	 */
	private static Sheet buildDataSheet(Workbook workbook) {
		Sheet sheet = workbook.createSheet();
		//设置列头宽度
		for (int i = 0; i < CELL_HEADS.size(); i++) {
			sheet.setColumnWidth(i, 4000);
		}
		//设置默认行高
		sheet.setDefaultRowHeight((short)400);
		//构建单元格样式
		CellStyle cellStyle = buildHeadCellStyle(sheet.getWorkbook());
		//写入第一行数据
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
	        //对齐方式设置
	        style.setAlignment(HorizontalAlignment.CENTER);
	        //边框颜色和宽度设置
	        style.setBorderBottom(BorderStyle.THIN);
	        style.setBottomBorderColor(IndexedColors.BLACK.getIndex()); // 下边框
	        style.setBorderLeft(BorderStyle.THIN);
	        style.setLeftBorderColor(IndexedColors.BLACK.getIndex()); // 左边框
	        style.setBorderRight(BorderStyle.THIN);
	        style.setRightBorderColor(IndexedColors.BLACK.getIndex()); // 右边框
	        style.setBorderTop(BorderStyle.THIN);
	        style.setTopBorderColor(IndexedColors.BLACK.getIndex()); // 上边框
	        //设置背景颜色
	        style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
	        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	        //粗体字设置
	        Font font = workbook.createFont();
	        font.setBold(true);
	        style.setFont(font);
	        return style;
	    }

	private static void convertDataToRow(Student data, Row row) {
		// TODO Auto-generated method stub
		int cellNum = 0;
		Cell cell;
		//序号
		cell = row.createCell(cellNum++);
		cell.setCellValue(row.getRowNum());
		//姓名
		cell = row.createCell(cellNum++);
		String name = data.getStudentName();
		cell.setCellValue(null==name?"":name);
		//学号
		cell = row.createCell(cellNum++);
		String studentCode = data.getSutdentCode();
		cell.setCellValue(null==studentCode?"":studentCode);
		//所在班级
		cell = row.createCell(cellNum++);
		String studentClazz = data.getClazz().getClazzName() + data.getClazz().getClazzType(); 
		cell.setCellValue(null==studentClazz?"":studentClazz);
		//性别
		cell = row.createCell(cellNum++);
		String studentSex = data.getStudentSex();
		cell.setCellValue(null==studentSex?"":studentSex);
		//电话
		cell = row.createCell(cellNum++);
		String studentPhone = data.getStudentPhone();
		cell.setCellValue(null==studentPhone?"":studentPhone);
		//地址
		cell = row.createCell(cellNum++);
		String studentAddress = data.getStudentAddress();
		cell.setCellValue(null==studentAddress?"":studentAddress);
		//学历
		cell = row.createCell(cellNum++);
		String studentEdu = data.getStudentEdu();
		cell.setCellValue(null==studentEdu?"":studentEdu);
		//毕业学校
		cell = row.createCell(cellNum++);
		String studentCollege = data.getStudentCollege();
		cell.setCellValue(null==studentCollege?"":studentCollege);
		//专业
		cell = row.createCell(cellNum++);
		String studentProfessional = data.getStudentProfessional();
		cell.setCellValue(null==studentProfessional?"":studentProfessional);
//		//日期
//		cell = row.createCell(cellNum++);
//		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
//		String date = sf.format(data.getScoreDate());;
//		cell.setCellValue(null==date?"":date);
	}
	
	
}
