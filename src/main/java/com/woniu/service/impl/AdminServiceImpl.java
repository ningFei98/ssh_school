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
	List<Clazz> clazz01=new ArrayList<>();
	public void save(Clazz clazz) {
		System.out.println("/////////////////////////////////////////////");
		Stage stage=new Stage();
		//设置班级阶段
		stage.setStageId(1);
		clazz.setStage(stage);
		//设置班级期数，通过查询最后一个班级自动生成新的班级
		if(clazz.getClazzType().equals("java开发")) {  //如果是开发班级
			System.out.println("hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
			//查询出最后一级的开发班或者测试班
			String str="select * from clazz where clazz_type='java开发'  order by clazz_id desc limit 0,1";
			List<Clazz> clazz01=adminDao.findClazz(str);
            if(clazz01.size()==0) {
            	clazz.setClazzName("1期");
            }else {
            	Clazz clazzOne = clazz01.get(0);
    			//得到新班级名称
    			String name=clazzOne.getClazzName();    //获得最后一个班级名称
    			int index=name.indexOf("期");            //获取“期”这个字的下标
    			String name01=name.substring(0, index); //通过下标截取字符前面的int数字
    			int name02=Integer.parseInt(name01)+1;  
    			String newName=name02+"期";
    			System.out.println("新的班级名字为："+newName);
    			clazz.setClazzName(newName);
                 }
			
		} 
				
		if(clazz.getClazzType().equals("测试开发")) {  //如果是测试班级
			System.out.println("tttttttttttttttttttttttttttttttttttttttttttttt");
			//查询出最后一级的开发班或者测试班
			String str="select * from clazz where clazz_type='测试开发'  order by clazz_id desc limit 0,1";
			//List<Clazz> clazz01=new ArrayList<>();
			List<Clazz> clazz01=adminDao.findClazz(str);
			 if(clazz01.size()==0) {
	            	clazz.setClazzName("1期");
	            }else {
	            	Clazz clazzOne = clazz01.get(0);
	    			//得到新班级名称
	    			String name=clazzOne.getClazzName();    //获得最后一个班级名称
	    			int index=name.indexOf("期");            //获取“期”这个字的下标
	    			String name01=name.substring(0, index); //通过下标截取字符前面的int数字
	    			int name02=Integer.parseInt(name01)+1;  
	    			String newName=name02+"期";
	    			System.out.println("新的班级名字为："+newName);
	    			clazz.setClazzName(newName);
	                 }
		      }
		System.out.println(clazz);
		//保存班级信息
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
		//获得所有学生id
		li=adminDao.findAllStudent(a);
		//集合接收所有学生考勤
		List<Checkin> lc=new ArrayList<Checkin>();
		
		int a1=0;  //统计迟到次数
		int a2=0;  //统计旷课次数
		int a3=0;  //统计请假次数
        int a4=0;  //统计正常次数
        //集合接收考勤分类信息
        List<CheckOrderBy> checkorder=new ArrayList<>();
        CheckOrderBy or=new CheckOrderBy();
		for (Student stu : li) {
			List<Checkin> c=adminDao.findOne(stu.getStudentId());
			for (Checkin checkin : c) {
				if(checkin.getCheckintype().getCheckInTypeName().equals("迟到")) {
					a1++;
				}
				if(checkin.getCheckintype().getCheckInTypeName().equals("旷课")) {
					a2++;
				}
				if(checkin.getCheckintype().getCheckInTypeName().equals("请假")) {
					a3++;
				}
				if(checkin.getCheckintype().getCheckInTypeName().equals("正常")) {
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
		//获得所有学生id
		li=adminDao.findAllStudent(a);
		//集合接收所有学生的分数
		List<Score> scores = new ArrayList<Score>();		
			for (Student stu : li) {
				List<Score> c=adminDao.findByStu(stu.getStudentId());
				for (Score score : c) {
					scores.add(score);
					}
			}
		return scores;
	}

	//获得测试班级数量
	@Override
	public int countCei() {
		String str="select * from clazz where clazz_type='测试开发'";
		List<Clazz> a=adminDao.findClazz(str);
		int num=a.size();
		return num;
	}
	//开发班级数量
	@Override
	public int countJ() {
		String str="select * from clazz where clazz_type='java开发'";
		List<Clazz> a=adminDao.findClazz(str);
		int num=a.size();
		return num;
	}

}
