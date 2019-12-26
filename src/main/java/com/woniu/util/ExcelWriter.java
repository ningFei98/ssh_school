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
		CELL_HEADS.add("序号");
		CELL_HEADS.add("学生姓名");
		CELL_HEADS.add("成绩");
		CELL_HEADS.add("日期");
	}
	/**
	 * 生成excel并写入数据
	 * @param dataList
	 * @return
	 */
	public static Workbook exportData(List<Score> dataList){

		Workbook workbook = new SXSSFWorkbook();
		//生成sheet，写入列头
		Sheet sheet = buildDataSheet(workbook);
		//构建每行数据内容
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

	private static void convertDataToRow(Score data, Row row) {
		// TODO Auto-generated method stub
		int cellNum = 0;
		Cell cell;
		//序号
		cell = row.createCell(cellNum++);
		cell.setCellValue(row.getRowNum());
		//姓名
		cell = row.createCell(cellNum++);
		String name = data.getStudent().getStudentName();
		cell.setCellValue(null==name?"":name);
		//分数
		cell = row.createCell(cellNum++);
		String num = String.valueOf(data.getScoreNum());
		cell.setCellValue(null==num?"":num);
		//日期
		cell = row.createCell(cellNum++);
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sf.format(data.getScoreDate());;
		cell.setCellValue(null==date?"":date);
	}
	
	
}
