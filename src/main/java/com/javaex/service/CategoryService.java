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
	
	
	public List<CateVo> cateList() {
	
		List<CateVo> cateList = categoryDao.cateList();
		
		return cateList;
	}
	
	public void cateInsert(CateVo cateVo) {
		
		categoryDao.cateInsert(cateVo);
	}
	
}
