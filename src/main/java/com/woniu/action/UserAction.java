package com.woniu.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.woniu.pojo.Relationship1;
import com.woniu.pojo.User;
import com.woniu.service.IUserService;

@SuppressWarnings("serial")
@Controller
public class UserAction extends ActionSupport{
	@Autowired
	private IUserService us;
	private List<User> users;
	private User user;
	
	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	public String login(){
		
		user = us.login(user);
		if(user!=null){
			System.out.println("登录后" + user);
			//将用户信息放入session
			ActionContext ac = ActionContext.getContext();
			Map<String, Object> session = ac.getSession();
			session.put("loginUser", user);
			Set<Relationship1> set = user.getRelationship1s();
			
			for (Relationship1 r1 : set) {
				switch (r1.getRole().getRoleId()) {
				case 1:
					return "admin";
				case 2:
					return "teacher";
				case 3:
					return "overman";
				case 4:
					return "student";
				}
			}
			
		}
		return "no";
	}
	

	
	public String exit(){
		ActionContext ac = ActionContext.getContext();
		Map<String, Object> session = ac.getSession();
		session.remove("loginUser");
		return "exit";
	}
	
	
	public void change(){
		User u=(User) ServletActionContext.getRequest().getSession().getAttribute("loginUser");
		String oldpwd=u.getUserPwd();	
		System.out.println("旧密码"+oldpwd);
		
		String affpwd=ServletActionContext.getRequest().getParameter("oldPwd");
		System.out.println("新密码"+affpwd);
		PrintWriter out = null;
		try {
			out = ServletActionContext.getResponse().getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		if(oldpwd.equals(affpwd)) {
			out.print(true);
					
		}else {
			out.print(false);
		}
		out.flush();
		out.close();
	}
	
	public String upd() {
		User u=(User) ServletActionContext.getRequest().getSession().getAttribute("loginUser");
//		System.out.println(u);
		String newpwd=ServletActionContext.getRequest().getParameter("newPwd");
		u.setUserPwd(newpwd);
		us.upd(u);
		return "ok";
		
	} 

}
