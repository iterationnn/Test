package com.kgc.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.dao.user.UserMapper;
import com.kgc.pojo.User;
import com.kgc.util.MyBatisUtil;

public class PageTest {

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
	public void testPage() {
		//开始分页,pageHelper会拦截该语句后的第一条查询语句,并拼接分页sql
		PageHelper.startPage(2, 5);
		UserMapper mapper = sqlSession.getMapper(UserMapper.class);
		List<User> list = mapper.findUserByName("庄");
		for (User user : list) {
			System.out.println(user);
		}
		//创建一个分页信息对象,通过构造方法传入结果集合,自动生成页码等信息
		PageInfo<User> pageInfo = new PageInfo<User>(list);
		System.out.println("总页数:" + pageInfo.getPages());
		System.out.println("总条数:" + pageInfo.getTotal());
	}
}
