package com.woniu.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opensymphony.xwork2.ActionContext;
import com.woniu.pojo.Chart;
import com.woniu.pojo.CheckOrderBy;
import com.woniu.pojo.Checkin;
import com.woniu.pojo.Clazz;
import com.woniu.pojo.Overman;
import com.woniu.pojo.Score;
import com.woniu.pojo.Stage;
import com.woniu.pojo.Teacher;
import com.woniu.service.AdminService;

    @Controller
    public class AdminAction {
	    private Clazz clazz;
		public Clazz getClazz() {
			return clazz;
		}
		public void setClazz(Clazz clazz) {
			this.clazz = clazz;
		}
		
		//-------------------------------�������а༶��Ϣ
		private List<Clazz> li =new ArrayList<Clazz>();
		public List<Clazz> getLi() {
			return li;
		}
		public void setLi(List<Clazz> li) {
			this.li = li;
		}
		
		//---------------------------------����������ʦ��Ϣ
		private List<Teacher> te=new ArrayList<Teacher>();
		public List<Teacher> getTe() {
			return te;
		}
		public void setTe(List<Teacher> te) {
			this.te = te;
		}
		
		//---------------------------------�������а�������Ϣ
		private List<Overman> lo=new ArrayList<Overman>();
		public List<Overman> getLo() {
			return lo;
		}
		public void setLo(List<Overman> lo) {
			this.lo = lo;
		}
		
		//---------------------------------�������п�����Ϣ
		private List<CheckOrderBy> lc;
		public List<CheckOrderBy> getLc() {
			return lc;
		}
		public void setLc(List<CheckOrderBy> lc) {
			this.lc = lc;
		}
		
		//---------------------------------�������гɼ���Ϣ
		private List<Score> scores = new ArrayList<Score>();
		public List<Score> getScores() {
			return scores;
		}
		public void setScores(List<Score> scores) {
			this.scores = scores;
		}

		//---------------------------------��װ�׶���Ϣ
		private Stage stage;	
		public Stage getStage() {
			return stage;
		}
		public void setStage(Stage stage) {
			this.stage = stage;
		}

		//��ѯ���еİ༶��Ϣ--------clazz
		@Autowired
		AdminService  adminService;
		public String findAll() {
			
			li=adminService.findAll();
			return "success";
		}
		
		
        //��ѯ�����༶ʱ��������Ϣ
		public String find() {
			System.out.println("------------Action---find");
			//��ѯ��������ʦ��Ϣ
			lo=adminService.findOverman();
			System.out.println("==============================");
			System.out.println(lo);
			//��ѯ��ʦ��Ϣ
			te=adminService.findTeacher();
			System.out.println("==============================");
			System.out.println(te);
			return "success";
		}
		
		
		

		
		//���������༶
		public String save() {
			System.out.println(clazz);
			adminService.save(clazz);
			return "success";
			
		}
		
		//��ѯ�༶����
		public String findScore() {
			System.out.println(clazz);
			int a=clazz.getClazzId();
			//��ȡ�༶����
			scores=adminService.findScoreByClazz(a);
			
			return "success";
			
		}
		
		//��ѯ�༶����
		public String findCheck() {
			System.out.println("++++++++++++++++++++++++++++findCHECK");
			System.out.println(clazz);
			int a=clazz.getClazzId();
			lc=adminService.findCheck(a);
			System.out.println(lc);
			return "success";
		}
		
		//��ȫ�˳�
		public String userDelete() {
			System.out.println("��ȫ�˳�");
			//ɾ��session�е�loginName����
			ActionContext ac=ActionContext.getContext();
			 Map<String, Object> session = ac.getSession();
			 session.remove("loginUser");
			return "success";	
		}
		
		
		//��״ͼ����
		public void chart() {
			System.out.println("��״ͼ����");
			//��ò��԰༶������
			int countCei =adminService.countCei();
			//��ÿ����༶������
			int countJ=adminService.countJ();
			//���ݷ�װ
			Chart ch=new Chart();
			ch.setCountC(countCei);
			ch.setCountJ(countJ);
		    //���ݷ���
			try {
				PrintWriter out=ServletActionContext.getResponse().getWriter();
				//������ת����json����
				ObjectMapper obj = new ObjectMapper();
				String str01=obj.writeValueAsString(ch);
				out.print(str01);
				out.flush();
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
}
