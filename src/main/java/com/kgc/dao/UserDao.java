package com.kgc.dao;

import java.util.List;

import com.kgc.entity.User;

public interface UserDao {
	
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    List<User> getUserList();
    
    User findUserWithRoleNameById(Long id);
    
    List<User> findUserByParam(User param);
}