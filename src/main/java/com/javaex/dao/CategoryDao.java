package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.CateVo;

@Repository
public class CategoryDao {

	@Autowired
	private SqlSession sqlSession;

	
	public List<CateVo> cateList() {
		
		List<CateVo> cateList = sqlSession.selectList("category.selectList", sqlSession);
		
		return cateList;
	}
	
	
	
	public void cateInsert(CateVo cateVo) {
		System.out.println("Dao: " + cateVo);
		
		sqlSession.insert("category.insert", cateVo);
	}
}
