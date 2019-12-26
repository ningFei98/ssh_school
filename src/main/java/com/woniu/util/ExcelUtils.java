package com.woniu.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.woniu.pojo.Score;
import com.woniu.pojo.Student;

public class ExcelUtils {
	
	//private static Logger logger = Logger.getLogger(ExcelReader.class.getName());
	private static final String XLS = "xls";
	private static final String XLSX = "xlsx";
	
	/**
	 * �����ļ���׺��ȡ��Ӧ����������
	 * @param inputStream
	 * @param fileType
	 * @return
	 * @throws IOException
	 */
	private static Workbook getWorkbook(InputStream inputStream, String fileType) throws IOException {
		Workbook workbook = null;
		if(fileType.equalsIgnoreCase(XLS)) {
			workbook = new HSSFWorkbook(inputStream);
		}else if (fileType.equalsIgnoreCase(XLSX)) {
			workbook = new XSSFWorkbook(inputStream);
		}
		return workbook;
	}
	
	private static String getFileType(String filename) {
		return filename.substring(filename.lastIndexOf(".")+1);
	}
	
	/**
	 * ��ȡexcel
	 * @param fileName
	 * @return
	 */
	public static List<Score> readExcel(File excelFile,String fileName) {
		String fileType = getFileType(fileName);
		if(!excelFile.exists()) {
			System.out.println("�ļ�������");
			return null;
		}
		//��ȡ������
		FileInputStream inputStream = null;
		Workbook workbook = null;
		try {
			inputStream = new FileInputStream(excelFile);
			workbook = getWorkbook(inputStream, fileType);
			//��ȡ����
			List<Score> resultDataList = parseExcel(workbook);
			return resultDataList;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			//�ر�workbook����
			try {
                if (null != workbook) {
                    workbook.close();
                }
                if (null != inputStream) {
                    inputStream.close();
                }
            } catch (Exception e) {
                return null;
            }
		}
		return null;
		
	}
	/**
	 * �����������
	 * @param workbook
	 * @return
	 */
	private static List<Score> parseExcel(Workbook workbook) {
		List<Score> resultDataList = new ArrayList<Score>();
		//����sheet
		for (int sheetNum = 0; sheetNum < workbook.getNumberOfSheets(); sheetNum++) {
			Sheet sheet =  workbook.getSheetAt(sheetNum);
			//У��sheet�Ƿ�Ϸ� 
			if (sheet==null) {
				continue;
			}
			//��ȡ���ͷ
			int firstRowNum = sheet.getFirstRowNum();
			Row firstRow = sheet.getRow(firstRowNum);
			if(firstRow==null) {
				System.out.println("����������");
			}
			
			//����ÿ�����ݣ��������ݶ���
			int rowStart = firstRowNum + 1;
			int rowEnd = sheet.getPhysicalNumberOfRows();
			for (int rowNum = rowStart; rowNum < rowEnd; rowNum++) {
				Row row = sheet.getRow(rowNum);
				if (row == null) {
					continue;
				}
				Score resultData = convertRowToData(row);
				if (null == resultData) {
					System.out.println("��"+row.getRowNum() + "�����ݲ��Ϸ����Ѻ��ԣ�");
					continue;
				}
				resultDataList.add(resultData);
			}
		}
		return resultDataList;
	}
	
	private static Score convertRowToData(Row row) {
		Score resultData = new Score();
        Cell cell;
        int cellNum = 0;
        // ��ȡѧ��id
        cell = row.getCell(cellNum++);
        String stuId = convertCellValueToString(cell);
        Student stu = new Student();
        stu.setStudentId(Integer.parseInt(stuId));
        resultData.setStudent(stu);
        // ��ȡ�ɼ�
        cell = row.getCell(cellNum++);
        String scoreNum = convertCellValueToString(cell);
        resultData.setScoreNum(Double.valueOf(scoreNum));
        // ��ȡ����
        cell = row.getCell(cellNum++);
        String scoreDate = convertCellValueToString(cell);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        try {
			resultData.setScoreDate(sf.parse(scoreDate));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        return resultData;
	}

	private static String convertCellValueToString(Cell cell) {
		 if(cell==null){
	            return null;
	        }
	        String returnValue = null;
	        switch (cell.getCellTypeEnum()) {
	            case NUMERIC:   //����
	            	if(HSSFDateUtil.isCellDateFormatted(cell)){
	            		//����ת��Ϊ���ڸ�ʽ
	            		Date d = cell.getDateCellValue();
	            		DateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
	            		returnValue = formater.format(d);
	            	}else {
	            		Double doubleValue = cell.getNumericCellValue();
		                // ��ʽ����ѧ��������ȡһλ����
		                DecimalFormat df = new DecimalFormat("0");
		                returnValue = df.format(doubleValue);
					}
	            	break;
	            case STRING:    //�ַ���
	                returnValue = cell.getStringCellValue();
	                break;
	            case BOOLEAN:   //����
	                Boolean booleanValue = cell.getBooleanCellValue();
	                returnValue = booleanValue.toString();
	                break;
	            case BLANK:     // ��ֵ
	                break;
	            case FORMULA:   // ��ʽ
	                returnValue = cell.getCellFormula();
	                break;
	            case ERROR:     // ����
	                break;
	            default:
	                break;
	        }
	        return returnValue;
	}
}
