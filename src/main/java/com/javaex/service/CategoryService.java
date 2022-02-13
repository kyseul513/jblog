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
	
	public void cateInsert(CateVo cateVo) {
		
		categoryDao.cateInsert(cateVo);
	}
	
}
