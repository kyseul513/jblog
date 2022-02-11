package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	public List<CateVo> cateList() {

		List<CateVo> cateList = categoryService.cateList();

		return cateList;
	}

	// 카테고리 값 입력 + 입력된 값 가져오기
	@ResponseBody
	@RequestMapping("/cateInsert")
	public void cateInsert(@ModelAttribute CateVo cateVo) {

		categoryService.cateInsert(cateVo);
	}
	

}
