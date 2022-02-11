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
		BlogVo blogVo = sqlSession.selectOne("blog.blogMain", id);
		
		return blogVo;
	}
	
	
	public void upload(BlogVo blogVo) {
		System.out.println("BlogDao.upload: " + blogVo);
		
		sqlSession.insert("blog.upload", blogVo);
		
	}
	
}
