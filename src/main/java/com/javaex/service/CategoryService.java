package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.CategoryDao;
import com.javaex.vo.CateVo;

@Service
public class CategoryService {

	@Autowired
	private CategoryDao categoryDao;

	public List<CateVo> cateList(String id) {

		List<CateVo> optionList = categoryDao.cateList(id);

		return optionList;
	}

	public List<CateVo> cateOption(String id) {
		System.out.println("service" + id);

		List<CateVo> cateOption = categoryDao.cateOption(id);

		return cateOption;
	}

	// 카테고리 값 입력 + 입력된 값 가져오기
	public CateVo cateInsert(CateVo cateVo) {
		
		//카테고리 값 입력
		categoryDao.cateInsert(cateVo);
		
		//방금 저장된 값 가져오기(Dao에서 넘겨준 cateNo 활용)
		int cateNo = cateVo.getCateNo();
		return categoryDao.selectOne(cateNo);
	}

}
