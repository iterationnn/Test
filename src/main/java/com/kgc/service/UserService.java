package com.kgc.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.kgc.entity.Role;
import com.kgc.entity.User;

public interface UserService {
	
	public List<User> getUserList();
	
	public List<Role> getRoleList();
	
	public User getUserById(Long id);
	
	public List<User> getUserByParam(User param);
	
	public PageInfo<User> getUserPageByParam(User param,Integer pageNum, Integer pageSize);
	
	public int addUser(User user);
	
	public int updateUser(User user);
	
	public int deleteUser(Long id);

}
