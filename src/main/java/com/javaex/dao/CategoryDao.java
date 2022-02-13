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

	
	public List<CateVo> cateList(String id) {		
		List<CateVo> cateList = sqlSession.selectList("category.selectList", id);
		
		System.out.println("Dao: " + cateList);
		return cateList;
	}
	
	//글작성페이지의 카테고리 선택란
	public List<CateVo> cateOption(String id) {
		List<CateVo> cateOption = sqlSession.selectList("category.cateOption", id);
		
		return cateOption;
	}
	
	
	public void cateInsert(CateVo cateVo) {
		//System.out.println("Dao: " + cateVo);
		
		sqlSession.insert("category.insert", cateVo);
	}
}
