package com.woniu.utils;

import java.text.SimpleDateFormat;

import org.apache.poi.ss.usermodel.Cell;

public class ExcelUtil {

	/**
	 * 7 * 传入Cell，根据Cell的类型转化，返回的都是String 8
	 */
	public static String formatCell(Cell cell) {
		String cellValue = "";
		if (cell == null) {
			return cellValue;
		} else {
			if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
				cellValue = String.valueOf(cell.getBooleanCellValue());
				return cellValue;
			} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
				if (org.apache.poi.ss.usermodel.DateUtil.isCellDateFormatted(cell)) {
					SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
					cellValue = fmt.format(cell.getDateCellValue());
				} else {
				    Long longVal = Math.round(cell.getNumericCellValue());
					Double doubleVal = cell.getNumericCellValue();
				    if (Double.parseDouble(longVal + ".0") == doubleVal){
				         cell.setCellType(Cell.CELL_TYPE_STRING);
				    }
				    cellValue = longVal.toString();
//					Long d = (long) cell.getNumericCellValue();
//					cellValue = d.toString();
//					// 解决1234.0 去掉后面的.0
//					if (null != cellValue&& !"".equals(cellValue.trim())) {
//						String[] item = cellValue.split("[.]");
//						if (1 < item.length && "0".equals(item[1])) {
//							cellValue = item[0];
//						}
//					}
				}
				return cellValue;
			} else {
				return String.valueOf(cell.getStringCellValue());
			}
		}
	}
}
