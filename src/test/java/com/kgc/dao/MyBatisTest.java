package com.kgc.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.kgc.dao.user.UserMapper;
import com.kgc.pojo.User;
import com.kgc.util.MyBatisUtil;

public class MyBatisTest {
	
	private Logger logger = Logger.getLogger(MyBatisTest.class);
	
	@Before
	public void setUp() throws Exception {
		logger.debug("===>Test Begin");
	}
	
	@After
	public void setDown() throws Exception {
		logger.debug("===>Test End");
	}

	@Test
	public void testMybatis() {
		String resource = "mybatis-config.xml";
		int count = 0;
		SqlSession sqlSession = null;
		try {
			//1 获取mybatis-config.xml的输入流
			InputStream is = Resources.getResourceAsStream(resource);
			//2 创建SqlSessionFactory对象，完成对配置文件的读取
			SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
			//3 创建sqlSession
			sqlSession = factory.openSession();
			//4 调用mapper文件来对数据进行操作，必须先把mapper文件引入到mybatis-config.xml中
			count = sqlSession.selectOne("com.kgc.dao.user.UserMapper.count");
			
			logger.debug( "count---> " + count);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			sqlSession.close();
		}
	}
	
	@Test
	public void testUserByMapper() {
		
		SqlSession sqlSession = MyBatisUtil.createSqlSession();
		//第一种方式:调用Mapper的selectList方法执行查询操作
		List<User> userList = sqlSession.selectList("com.kgc.dao.user.UserMapper.getUserList");
		
		MyBatisUtil.closeSqlSession(sqlSession);
		for (User user : userList) {
			System.out.println(user);
		}
	}
	
	
	@Test
	public void testUserByInterface() {
		SqlSession sqlSession = MyBatisUtil.createSqlSession();
		//第二种方式:调用getMapper(Mapper.class)执行dao接口方法来实现对数据库的查询操作
		UserMapper mapper = sqlSession.getMapper(UserMapper.class);
		List<User> userList = mapper.getUserList();
		
		MyBatisUtil.closeSqlSession(sqlSession);
		for (User user : userList) {
			System.out.println(user);
		}
		
		//断言,断定一个结果,如果是这个结果则测试成功,否则测试失败
		Assert.assertNotNull(userList);
	}

}
