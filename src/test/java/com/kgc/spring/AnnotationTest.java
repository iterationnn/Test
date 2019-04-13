package com.kgc.spring;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.kgc.pojo.User;
import com.kgc.spring.bean.Person;
import com.kgc.spring.service.UserService;


public class AnnotationTest {

	private ApplicationContext ctx;
	
	@Before
	public void getApplication() {
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
	}
	
	@Test
	public void testComponent() {
		Person bean = (Person)ctx.getBean("person");
		System.out.println(bean.getName());
	}
	
	@Test
	public void testService() {
		UserService userService = (UserService)ctx.getBean("UserService");
		userService.addNewUser(new User());
	}
}
