package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javaex.service.BlogService;
import com.javaex.vo.BlogVo;

@RequestMapping("/{id}")
@Controller
public class BlogController {

	@Autowired
	private BlogService blogService;

	//블로그 정보(공통)
	@ModelAttribute("blogVo")
	public BlogVo blogInfo(@PathVariable("id") String id,
						   Model model) {
		BlogVo blogVo = blogService.blogMain(id);
		
		return blogVo;
	}
	
	
	
	//블로그 메인
	@RequestMapping("")
	public String blogMain(@PathVariable("id") String id) {
		
		return "/blog/blog-main";
	}

	//admin 페이지
	@RequestMapping("/admin/basic")
	public String adminBasic(@PathVariable("id") String id) {

		return "/blog/admin/blog-admin-basic";
	}

	@RequestMapping("/admin/category")
	public String category() {
		
		return "/blog/admin/blog-admin-cate";
	}
}
