package com.kgc.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.dao.RoleDao;
import com.kgc.dao.UserDao;
import com.kgc.entity.Role;
import com.kgc.entity.User;
import com.kgc.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;
	@Autowired
	RoleDao roleDao;
	
	@Override
	public List<User> getUserList() {
		return userDao.getUserList();
	}
	
	@Override
	public List<Role> getRoleList() {
		return roleDao.getRoleList();
	}

	public User getUserById(Long id) {
		return userDao.findUserWithRoleNameById(id);
	}

	@Override
	public List<User> getUserByParam(User param) {
		return userDao.findUserByParam(param);
	}

	@Override
	public PageInfo<User> getUserPageByParam(User param,Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<User> list = userDao.findUserByParam(param);
		PageInfo<User> pageList = new PageInfo<User>(list);
		return pageList;
	}

	@Override
	@Transactional
	public int addUser(User user) {
		user.setCreationDate(new Date());
		return userDao.insert(user);
	}

	@Override
	public int updateUser(User user) {
		user.setModifyDate(new Date());
		return userDao.updateByPrimaryKeySelective(user);
	}

	@Override
	public int deleteUser(Long id) {
		return userDao.deleteByPrimaryKey(id);
	}

}
