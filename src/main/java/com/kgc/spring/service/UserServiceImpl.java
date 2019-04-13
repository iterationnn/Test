package com.kgc.spring.service;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.kgc.pojo.User;
import com.kgc.spring.dao.UserDao;

/**
 * 用户业务类，实现对User功能的业务管理
 */
//@Scope("singleton")	//Bean 在Spring容器中默认是单例模式
//@Scope("prototype")  	//原型模式,每次getBean都是一个新的实例对象 
@Service("UserService")	//定义业务层的Bean
public class UserServiceImpl implements UserService {

	//@Autowired				//依据类型,自动注入
	//@Qualifier("UserDao")		//依据Bean的ID注入
	@Resource(name="UserDao")	//JDK的注解 依据bean的名字进行注入
	private UserDao dao; 		// 声明接口类型的引用，和具体实现类解耦合
	
	// dao 属性的setter访问器，会被Spring调用，实现设值注入
	public void setDao(UserDao dao) {
		this.dao = dao;
	}

	public int addNewUser(User user) {
		// 调用用户DAO的方法保存用户信息
		dao.save(user);
		return 1;
	}

	@Override
	public void updateUser(Integer id)throws Exception {
		if(id==null) {
			// 测试异常通知
			throw new RuntimeException("ID IS NULL ERROR");
		}else {
			//测试环绕通知
			Thread.sleep(500);
		}
	}
}