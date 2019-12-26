package com.woniu.service.impl;

import java.util.ArrayList;
import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.woniu.dao.AdminDao;
import com.woniu.pojo.CheckOrderBy;
import com.woniu.pojo.Checkin;
import com.woniu.pojo.Clazz;
import com.woniu.pojo.Overman;
import com.woniu.pojo.Score;
import com.woniu.pojo.Stage;
import com.woniu.pojo.Student;
import com.woniu.pojo.Teacher;
import com.woniu.service.AdminService;

@Controller
@Transactional
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminDao adminDao;
	@Override
	public List<Clazz> findAll() {
		System.out.println("============adminServiceImpl");
        return adminDao.findAll();
	}

//	@Autowired
//	Stage stage;
//	@Override
	public void save(Clazz clazz) {
		Stage stage=new Stage();
		stage.setStageId(1);
//		stage.setStageId(1);
		clazz.setStage(stage);
        adminDao.save(clazz);
	}

	@Override
	public List<Teacher> findTeacher() {
		System.out.println("---------------adminServiceImpl---findTeacher");
		return adminDao.findAllTeacher();
	}

	@Override
	public List<Overman> findOverman() {
		// TODO Auto-generated method stub
		System.out.println("---------------adminServiceImpl---findOverman");
		return adminDao.findAllOverman();
	}
	
	
    List<Student> li=new ArrayList<Student>();
	@SuppressWarnings("unlikely-arg-type")
	@Override
	public List<CheckOrderBy> findCheck(int a) {
		//�������ѧ��id
		li=adminDao.findAllStudent(a);
		//���Ͻ�������ѧ������
		List<Checkin> lc=new ArrayList<Checkin>();
		
		int a1=0;  //ͳ�Ƴٵ�����
		int a2=0;  //ͳ�ƿ��δ���
		int a3=0;  //ͳ����ٴ���
        int a4=0;  //ͳ����������
        //���Ͻ��տ��ڷ�����Ϣ
        List<CheckOrderBy> checkorder=new ArrayList<>();
        CheckOrderBy or=new CheckOrderBy();
		for (Student stu : li) {
			List<Checkin> c=adminDao.findOne(stu.getStudentId());
			for (Checkin checkin : c) {
				if(checkin.getCheckintype().getCheckInTypeName().equals("�ٵ�")) {
					a1++;
				}
				if(checkin.getCheckintype().getCheckInTypeName().equals("����")) {
					a2++;
				}
				if(checkin.getCheckintype().getCheckInTypeName().equals("���")) {
					a3++;
				}
				if(checkin.getCheckintype().getCheckInTypeName().equals("����")) {
					a4++;
				}
                
			}
			or.setChidao(a1);
			or.setKuangke(a2);
			or.setQingjia(a3);
			or.setZhengchang(a4);
            String name= stu.getStudentName();
            or.setStudentName(name);
            checkorder.add(or);
		  }
		return checkorder;
	}

	@Override
	public List<Score> findScoreByClazz(int a) {
		//�������ѧ��id
		li=adminDao.findAllStudent(a);
		//���Ͻ�������ѧ���ķ���
		List<Score> scores = new ArrayList<Score>();		
			for (Student stu : li) {
				List<Score> c=adminDao.findByStu(stu.getStudentId());
				for (Score score : c) {
					scores.add(score);
					}
			}
		return scores;
	}

}
