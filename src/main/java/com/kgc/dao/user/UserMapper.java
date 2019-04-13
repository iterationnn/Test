package com.kgc.dao.user;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.kgc.pojo.User;

/*
 * 接口的完全限定名和mapper的命名空间保持一致
 * 方法名称和sql的id保持一致	
 */
public interface UserMapper {

	// 统计用户人数
	int count();

	// 查询所有的用户
	List<User> getUserList();

	// 查询所有的用户并关联查询用户的角色名
	List<User> getUserWithRole();
	
	// 方法接收一个参数
	List<User> findUserByName(String userName);

	// 多个参数的查询
	List<User> findUserByObj(User user);
	List<User> findUserByMap(Map<String, Object> parameter);
	List<User> findUserByParam(@Param("userName") String userName, @Param("roleId") Integer roleId);
	
	// 多条件按优先级匹配其中一个条件
	List<User> findUserByParamChoose(
			@Param("userCode") String userCode, 
			@Param("userName") String userName, 
			@Param("roleId") Integer roleId);
	
	// 依据多个角色ID查询用户
	// 传入一个集合参数,引用时用list; 传入一个数组参数,引用时用array; 传入多个参数用@Param指定命名
	List<User> findUserByRoles(List<Integer> roleIds);

	// 增加用户的方法
	int addUser(User user); // 返回影响行数
	// 批量插入用户
	int addUsersBatch(List<User> users);

	// 修改用户(依据传入的参数,动态修改部分字段)
	int modifyUserById(User user);

	// 删除用户
	int deleteUserById(int id);

	// 一对一关联查询
	User findUserWithRoleObjById(int id);

	// 一对多关联查询
	User findUserWithAddressById(int id);
	
	//二段查询
	List<User> getUserWithRoleSecond();
	
	//分页查询
	List<User> findUserByPage(
			@Param("userName") String userName,
			@Param("index") int index,
			@Param("pageSize") int pageSize);
	
}
