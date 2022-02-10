package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BlogVo;

@Repository
public class BlogDao {

	@Autowired
	private SqlSession sqlSession;
	
	public BlogVo blogMain(String id) {
		System.out.println("BlogDao.blogMain: " + id);
		
		BlogVo blogVo = sqlSession.selectOne("blog.blogMain", id);
		
		return blogVo;
		
	}
}
