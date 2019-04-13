package com.kgc.spring;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.kgc.pojo.User;
import com.kgc.spring.service.UserService;

public class AopTest {

	private ApplicationContext ctx;
	
	@Before
	public void getApplication() {
		ctx = new ClassPathXmlApplicationContext("application-aop.xml");
	}
		
    @Test
	public void testAddUser() {
    	UserService service = (UserService) ctx.getBean("service");
		User user = new User();
		user.setUserName("张三丰");
		service.addNewUser(user);
	}

    @Test
	public void testUpdateUser(){
    	UserService service = (UserService) ctx.getBean("service");
		try {
			service.updateUser(null);
		} catch (Exception e) {
			System.out.println("AopTest类处理了这个异常");
		}
	}
    
}
