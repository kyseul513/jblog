package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository
public class UserDao {

	@Autowired
	private SqlSession sqlSession;

	public void join(UserVo userVo) {
		System.out.println("UserDao.join");

		sqlSession.insert("user.join", userVo);
	}

	public String idCheck(String id) {
		System.out.println("Dao: " + id);

		String cId = sqlSession.selectOne("user.idCheck", id);
		// System.out.println(cId);

		return cId;
	}

	public UserVo login(UserVo userVo) {
		System.out.println("UserDao.login");

		UserVo authUser = sqlSession.selectOne("user.login", userVo);
		System.out.println("userDao.login: " + authUser);
		
		return authUser;
	}
}
