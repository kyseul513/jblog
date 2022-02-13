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

		//System.out.println("Dao: " + cateList);
		return cateList;
	}

	// 글작성페이지의 카테고리 선택란
	public List<CateVo> cateOption(String id) {
		List<CateVo> cateOption = sqlSession.selectList("category.cateOption", id);

		return cateOption;
	}

	//1-1. 카테고리 값 입력 + 입력된 값(cateNo) 가져오기
	public int cateInsert(CateVo cateVo) {
		// System.out.println("Dao: " + cateVo);
		
		//변수 만들지 않고 바로 리턴(cateNo값을 CategoryService로 리턴시키기.)
		return sqlSession.insert("category.insert", cateVo);
	}
	
	//1-2. 방금 입력한 카테고리 전체값 가져오기
	public CateVo selectOne(int cateNo) {
		
		CateVo cateVo = sqlSession.selectOne("category.selectOne", cateNo);
		
		return cateVo;
	}
	
	//카테고리 삭제
	public void cateDel(CateVo cateVo) {
		//System.out.println("cateDao: " + cateVo);
		int no = sqlSession.delete("category.cateDel", cateVo);
		System.out.println(no + "건 삭제");
	}
}
