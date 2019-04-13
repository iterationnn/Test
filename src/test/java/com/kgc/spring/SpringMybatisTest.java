package com.kgc.spring;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.kgc.entity.User;
import com.kgc.spring.service.UserService2Real;

public class SpringMybatisTest {

	private ApplicationContext ctx;
	
	@Before
	public void getApplication() {
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
	}
	
	@Test
	public void testSqlSessionTemplate() {
		UserService2Real service = (UserService2Real)ctx.getBean("userService");
		service.count();
	}
	
	@Test
	public void testMapperFactory() {
		UserService2Real service = (UserService2Real)ctx.getBean("userService");
		service.getUserList();
	}
	
	@Test
	public void testMapperScannerConfigurer() {
		UserService2Real service = (UserService2Real)ctx.getBean("userService");
		service.getAll();
	}
	
	@Test
	public void testTransaction() {
		UserService2Real service = (UserService2Real)ctx.getBean("userService");
		User user = new User();
		user.setUserName("廖海兵");
		service.addUser(user);
	}
	
}
