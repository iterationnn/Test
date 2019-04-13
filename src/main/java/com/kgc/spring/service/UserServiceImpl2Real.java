package com.kgc.spring.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kgc.dao.AddressDao;
import com.kgc.dao.BillDao;
import com.kgc.dao.RoleDao;
import com.kgc.dao.UserDao;
import com.kgc.dao.user.UserMapper;
import com.kgc.pojo.User;

@Service("userService")
public class UserServiceImpl2Real implements UserService2Real {

	@Autowired
	//@Qualifier("userDaoByTemplate")
	//@Qualifier("userDaoByMapperFactory")
	private UserMapper userMapper;
	@Autowired
	private UserDao userDao;
	@Autowired
	private RoleDao roleDao;
	@Autowired
	private BillDao billDao;
	@Autowired
	private AddressDao addressDao;
	
	@Override
	public void count() {
		// TODO Auto-generated method stub
		System.out.println(userMapper.count());
	}

	@Override
	public void getUserList() {
		List<User> users = userMapper.getUserList();
		System.out.println(users.size());
	}

	@Override
	public void getAll() {
		System.out.println(userDao.selectByPrimaryKey(1L));
		System.out.println(roleDao.selectByPrimaryKey(1L));
		System.out.println(billDao.selectByPrimaryKey(1L));
		System.out.println(addressDao.selectByPrimaryKey(1L));
	}

	@Transactional
	public int addUser(com.kgc.entity.User user) {
		userDao.insert(user);
		throw new RuntimeException("模拟异常");
	}
	
}
