package com.woniu.action;

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
	
	
	public String change() {
		User u=(User) ServletActionContext.getRequest().getSession().getAttribute("loginUser");
		String mi=u.getUserPwd();	
		System.out.println("旧密码"+mi);
		
		String newmi=ServletActionContext.getRequest().getParameter("oldPwd");
		System.out.println("新密码"+newmi);
		if(mi.equals("newmi")) {
			return "true";
		}
		return "false";
	}

}
