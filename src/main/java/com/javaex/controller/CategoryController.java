package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.CategoryService;
import com.javaex.vo.CateVo;

@RequestMapping("/{id}/admin")
@Controller
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	// 카테고리 리스트
	@ResponseBody
	@RequestMapping("/cateList")
	public List<CateVo> cateList(@PathVariable("id") String id) {

		List<CateVo> cateList = categoryService.cateList(id);
		
		return cateList;
	}

	// 카테고리 값 입력 + 입력된 값 가져오기
	@ResponseBody
	@RequestMapping("/cateInsert")
	public CateVo cateInsert(@ModelAttribute CateVo cateVo) {
		//System.out.println(cateVo);
		CateVo cateOne = categoryService.cateInsert(cateVo);

		return cateOne;
	}
	
	//카테고리 삭제
	@ResponseBody
	@RequestMapping("/cateDelete") 
	public String cateDel(@ModelAttribute CateVo cateVo) {
		//System.out.println(cateVo);		
		String result = categoryService.cateDel(cateVo);
		
		return result;
	}
	

}
