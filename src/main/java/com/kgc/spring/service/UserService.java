package com.kgc.spring.service;

import com.kgc.pojo.User;

/**
 * 用户业务接口，定义了所需的业务方法
 */
public interface UserService {
	
	public int addNewUser(User user);
	
	public void updateUser(Integer id)throws Exception ;
}
