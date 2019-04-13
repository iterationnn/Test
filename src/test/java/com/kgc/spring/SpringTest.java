package com.kgc.spring;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.kgc.spring.bean.HelloSpring;

public class SpringTest {

	@Test
	public void testSpring() {
		 // 通过ClassPathXmlApplicationContext实例化Spring的上下文
		ApplicationContext context = new ClassPathXmlApplicationContext("application-bean.xml");
       
        // 通过ApplicationContext的getBean()方法，根据id来获取bean的实例
        HelloSpring bean = (HelloSpring)context.getBean("helloSpring");
        
        // 执行print()方法
        bean.print();
	}
	
}
