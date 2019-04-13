package com.kgc.dao.user;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;

import com.kgc.pojo.User;

public class UserMapperImpl implements UserMapper {

	private SqlSessionTemplate sqlSession;
	
	public SqlSessionTemplate getSqlSession() {
		return sqlSession;
	}

	public void setSqlSession(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public int count() {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne("com.kgc.dao.user.UserMapper.count");
	}

	@Override
	public List<User> getUserList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getUserWithRole() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findUserByName(String userName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findUserByObj(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findUserByMap(Map<String, Object> parameter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findUserByParam(String userName, Integer roleId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findUserByParamChoose(String userCode, String userName, Integer roleId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findUserByRoles(List<Integer> roleIds) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int addUser(User user) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int addUsersBatch(List<User> users) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int modifyUserById(User user) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteUserById(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public User findUserWithRoleObjById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findUserWithAddressById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getUserWithRoleSecond() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findUserByPage(String userName, int index, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

}
