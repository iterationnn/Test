package com.kgc.spring;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.kgc.pojo.Address;
import com.kgc.pojo.User;

public class IocTest {

	private ApplicationContext ctx;
	
	@Before
	public void getApplication() {
		ctx = new ClassPathXmlApplicationContext("application-bean.xml");
	}
	
	@Test
	public void testSetInject() {
		//set注入各种类型
		User zhangSan  = (User)ctx.getBean("zhangSan");
		System.out.println(zhangSan);
		System.out.println(zhangSan.getRole());
		for(Address add : zhangSan.getAddressList()) {
			System.out.println(add);
		}
	}
	
	@Test
	public void testConstructorInject() {
		//构造方法注入
		User liSi  = (User)ctx.getBean("liSi");
		System.out.println(liSi);
		
		User wangWu  = (User)ctx.getBean("wangWu");
		System.out.println(wangWu);
		System.out.println(wangWu.getRole());
	}
	
	@Test
	public void testPSetInjection() {
		User zhuLiu  = (User)ctx.getBean("zhuLiu");
		System.out.println(zhuLiu);
		System.out.println(zhuLiu.getRole());
	}
}
