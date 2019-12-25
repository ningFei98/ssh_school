package com.woniu.action;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.woniu.pojo.Checkin;
import com.woniu.pojo.Checkintype;
import com.woniu.pojo.Clazz;
import com.woniu.pojo.Student;
import com.woniu.povo.StudentCheckintype;
import com.woniu.service.IStudentService;
import com.woniu.utils.ExcelUtil;

@SuppressWarnings("serial")
@Controller
public class StudentAction extends ActionSupport{
	@Autowired
	private IStudentService ss;
	
	private Student student;
	private List<Checkin> checks;
	private Checkin check;
	private StudentCheckintype sct;
	private Checkintype checkintype;
	private Clazz clazz;
	private File userUploadFile;    //���������ϴ����ļ�

	
	public File getUserUploadFile() {
		return userUploadFile;
	}


	public void setUserUploadFile(File userUploadFile) {
		this.userUploadFile = userUploadFile;
	}


	public Clazz getClazz() {
		return clazz;
	}


	public void setClazz(Clazz clazz) {
		this.clazz = clazz;
	}


	public Checkintype getCheckintype() {
		return checkintype;
	}


	public void setCheckintype(Checkintype checkintype) {
		this.checkintype = checkintype;
	}


	public StudentCheckintype getSct() {
		return sct;
	}


	public void setSct(StudentCheckintype sct) {
		this.sct = sct;
	}


	public Checkin getCheck() {
		return check;
	}


	public void setCheck(Checkin check) {
		this.check = check;
	}


	public List<Checkin> getChecks() {
		return checks;
	}


	public void setChecks(List<Checkin> checks) {
		this.checks = checks;
	}


	public Student getStudent() {
		return student;
	}


	public void setStudent(Student student) {
		this.student = student;
	}

	/**
	 * ��ѯָ��ѧ����Ϣ
	 * @return
	 */
	public String getStudentByStudentId(){
		System.out.println(student.getStudentId());
		student = ss.getStudentByStudentId(student.getStudentId());
		//��ָ��ѧ�����������У�ʹ��chainת����findAllClazz.action
		ActionContext ac = ActionContext.getContext();
		ac.put("student", student);
		return SUCCESS;
	}

	/**
	 * չʾָ��ѧ��������Ϣ
	 * ͨ��һ��ѧ����ID�����ݿ⿼�ڱ��ѯָ��ѧ�����п��ڼ�¼�����Ұ�����ʱ�䵹���װ
	 * Ҳ����ͨ��һ��ѧ��id�����ݿ�鵽һ��ѧ����Ϣ��ѧ���������п��ڼ�¼set����
	 * ������Ϊset�������򣬵���ÿ�����ڼ�¼ʱ����ң�û��ʹ�ö����е�set����
	 * @return
	 */
	public String findChecksByStudentId(){
		checks = ss.findChecksByStudentId(student.getStudentId());
		return SUCCESS;
	}
	/**
	 * ��ӿ��ڼ�¼
	 * 1.���ݿ�������п������ͣ�����list���ϣ����ҳ��չʾ�������������˵���
	 * 2.���ݿ�õ�ָ��ѧ����¼�����ҳ��Ҫ�õ�ѧ��������
	 * 3.��ѧ���Ϳ�������list��װΪStudentCheckintype����povo��
	 * 4.ת�������ҳ��
	 * @return
	 */
	public String findAllCheckintype(){
		student = ss.getStudentByStudentId(student.getStudentId());
		sct = new StudentCheckintype();
		sct.setStudent(student);
		sct.setCheckintypes(ss.findAllCheckintype());
		return SUCCESS;
	}
	/**
	 * ������ӵĿ��ڼ�¼
	 * ��ת����ǰѧ����չʾ���ڼ�¼
	 * ��תʹ���ض���action��ֵ/����ת��action
	 * @return
	 */
	public String overSaveCheckin(){
		check = new Checkin();
		check.setStudent(student);
		check.setCheckintype(checkintype);
		check.setCheckInDate(new Date());
		ss.overSaveCheckin(check);
		//��ѧ��������������У�ʹ��chainת����findChecksByStudentId.action
		ActionContext ac = ActionContext.getContext();
		ac.put("student", student);
		return SUCCESS;
	}
	/**
	 * ���ѧԱ������ѧԱ
	 * @return
	 */
	public String overSaveStudent(){
		student.setClazz(clazz);
		ss.overSaveStudent(student);
		//���༶id���������У�ʹ��chainת����findChecksByStudentId.action
		ActionContext ac = ActionContext.getContext();
		ac.put("clazz", clazz);
		return SUCCESS;
	}
	
	/**
	 * ����ѧԱת����Ϣ
	 * @return
	 */
	public String overUpdStudentClazz(){
		//�������ת��id�Ž�ѧ������
		student.setClazz(clazz);
		ss.overUpdStudentClazz(student);
		return SUCCESS;
	}
	
	/**
	 * �޸�ѧԱ��Ϣ
	 * ��ѧԱ��Ϣת�����޸�ҳ��
	 * @return
	 */
	public String overUpdStudentView(){
		student = ss.getStudentByStudentId(student.getStudentId());
		return SUCCESS;
	}
	
	/**
	 * �޸�ѧԱ��Ϣ
	 * ��ҳ��ѧԱ��Ϣ����
	 * @return
	 */
	public String overUpdStudent(){
		ss.overUpdStudent(student);
		ActionContext ac = ActionContext.getContext();
		ac.put("clazz", clazz);
		return SUCCESS;
	}
	
	public String overBatchAddStudentSave(){
		//�õ�������
		Workbook wb = null;
		try {
			wb = WorkbookFactory.create(new FileInputStream(userUploadFile));
		} catch (EncryptedDocumentException | InvalidFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//��ȡ��һ��sheetҳ
        Sheet sheet =  wb.getSheetAt(0);
        if(sheet != null){
            for(int rowNum=1; rowNum<=sheet.getLastRowNum(); rowNum++){
                Row row = sheet.getRow(rowNum);
                if(row == null){
                    continue;
                }
                student = new Student();
                student.setClazz(clazz);
                student.setStudentName(ExcelUtil.formatCell(row.getCell(1)));
                student.setStudentSex(ExcelUtil.formatCell(row.getCell(2)));
                student.setStudentPhone(ExcelUtil.formatCell(row.getCell(3)));
                student.setStudentAddress(ExcelUtil.formatCell(row.getCell(4)));
                student.setStudentEdu(ExcelUtil.formatCell(row.getCell(5)));
                student.setStudentCollege(ExcelUtil.formatCell(row.getCell(6)));
                student.setStudentProfessional(ExcelUtil.formatCell(row.getCell(7)));
                ss.overSaveStudent(student);
            }
        }
		//���༶id���������У�ʹ��chainת����findChecksByStudentId.action
		ActionContext ac = ActionContext.getContext();
		ac.put("clazz", clazz);
		return SUCCESS;
	}
	
}
