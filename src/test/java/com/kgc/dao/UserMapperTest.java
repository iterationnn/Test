package com.kgc.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.kgc.dao.user.UserMapper;
import com.kgc.pojo.Address;
import com.kgc.pojo.User;
import com.kgc.util.MyBatisUtil;

public class UserMapperTest {

	private SqlSession sqlSession = null;

	@Before
	public void setUp() {
		sqlSession = MyBatisUtil.createSqlSession();
	}

	@After
	public void setDown() {
		MyBatisUtil.closeSqlSession(sqlSession);
	}
	
	@Test
	public void testFindUserByName() {
		UserMapper mapper = sqlSession.getMapper(UserMapper.class);
		List<User> list = mapper.findUserByName("李");
		for (User userRes : list) {
			System.out.println(userRes);
		}
	}

	@Test
	public void testFindUserByObj() {
		UserMapper mapper = sqlSession.getMapper(UserMapper.class);
		User user = new User();
		user.setUserName("李");
		user.setUserRole(2);
		List<User> list = mapper.findUserByObj(user);
		for (User userRes : list) {
			System.out.println(userRes);
		}
	}

	@Test
	public void testFindUserByMap() {
		UserMapper mapper = sqlSession.getMapper(UserMapper.class);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("uName", "李");
		map.put("uRole", 2);
		List<User> list = mapper.findUserByMap(map);
		for (User user : list) {
			System.out.println(user);
		}
	}

	@Test
	public void testFindUserByParam() {
		UserMapper mapper = sqlSession.getMapper(UserMapper.class);
		List<User> list = mapper.findUserByParam("李", 2);
		for (User user : list) {
			System.out.println(user);
		}
	}

	@Test
	public void TestFindUserByParamChoose() {
		UserMapper mapper = sqlSession.getMapper(UserMapper.class);
		List<User> list = mapper.findUserByParamChoose(null, null, null);
		for (User user : list) {
			System.out.println(user);
		}
	}
	
	@Test
	public void testGetUserWithRole() {
		UserMapper mapper = sqlSession.getMapper(UserMapper.class);
		List<User> list = mapper.getUserWithRole();
		for (User user : list) {
			System.out.println(user);
		}
	}

	@Test
	public void testAddUser() {
		UserMapper mapper = sqlSession.getMapper(UserMapper.class);
		User user = new User();
		user.setUserName("庄杰聪");
		user.setUserCode("666");
		int result = mapper.addUser(user);
		// 提交
		sqlSession.commit();
		System.out.println(result);
	}

	@Test
	public void testModifyUser() {
		UserMapper mapper = sqlSession.getMapper(UserMapper.class);
		User user = new User();
		user.setId(16);
		user.setUserName("庄杰聪_NEW");
		user.setUserCode("ZhuangJieCong");
		int result = mapper.modifyUserById(user);
		sqlSession.commit();
		System.out.println(result);
	}

	@Test
	public void testDeleteUser() {
		UserMapper mapper = sqlSession.getMapper(UserMapper.class);
		int result = mapper.deleteUserById(16);
		sqlSession.commit();
		System.out.println(result);
	}

	@Test
	public void testFindUserWithRoleObjById() {
		UserMapper mapper = sqlSession.getMapper(UserMapper.class);
		User user = mapper.findUserWithRoleObjById(1);
		System.out.println(user);
		System.out.println(user.getRole());
	}

	@Test
	public void testFindUserWithAddressById() {
		UserMapper mapper = sqlSession.getMapper(UserMapper.class);
		User user = mapper.findUserWithAddressById(1);
		System.out.println(user);
		for (Address add : user.getAddressList()) {
			System.out.println(add);
		}
	}

	@Test
	public void testFindUserByRoles() {
		UserMapper mapper = sqlSession.getMapper(UserMapper.class);
		List<Integer> rolesList = new ArrayList<Integer>();
		rolesList.add(2);
		rolesList.add(3);
		rolesList.add(4);
		//int[] rolesArray = {1,2,3};
		List<User> list = mapper.findUserByRoles(rolesList);
		for (User user : list) {
			System.out.println(user);
		}
	}
	
	@Test
	public void testAddUsersBatch() {
		UserMapper mapper = sqlSession.getMapper(UserMapper.class);
		List<User> users = new ArrayList<User>();
		User user1 = new User();
		user1.setUserName("张三丰");
		users.add(user1);
		User user2 = new User();
		user2.setUserName("张无忌");
		users.add(user2);
		int result = mapper.addUsersBatch(users);
		System.out.println(result);
		sqlSession.commit();
	}
	
	@Test
	public void testGetUserWithRoleSecond() {
		UserMapper mapper = sqlSession.getMapper(UserMapper.class);
		List<User> list = mapper.getUserWithRoleSecond();
		System.out.println(list.get(0));
		System.out.println(list.get(0).getRole());
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(list.get(1));
		System.out.println(list.get(1).getRole());
	}
	
	@Test
	public void testFindUserByPage() {
		UserMapper mapper = sqlSession.getMapper(UserMapper.class);
		List<User> list = mapper.findUserByPage("庄", 5, 5);
		for (User user : list) {
			System.out.println(user);
		}
	}
	
}
