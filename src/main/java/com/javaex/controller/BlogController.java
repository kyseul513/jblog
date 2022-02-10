package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.BlogService;
import com.javaex.vo.BlogVo;

@RequestMapping("/{id}")
@Controller
public class BlogController {

	@Autowired
	private BlogService blogService;

	//블로그 정보(공통)
	@ModelAttribute("blogVo")
	public BlogVo blogInfo(@PathVariable("id") String id) {
		BlogVo blogVo = blogService.blogMain(id);
		
		return blogVo;
	}
	
	
	//블로그 메인
	@RequestMapping("")
	public String blogMain(@PathVariable("id") String id) {
		
		return "/blog/blog-main";
	}

	//내 블로그 관리
	@RequestMapping("/admin/basic")
	public String adminBasic(@PathVariable("id") String id) {

		return "/blog/admin/blog-admin-basic";
	}

	
	@RequestMapping("/upload")
	public String upload(@PathVariable("id") String id,
						 @RequestParam("file") MultipartFile file,
						 @ModelAttribute BlogVo blogVo){
		
		blogService.upload(id, file, blogVo);
		
		return "/blog/admin/blog-admin-basic";
	}
	
	
	//글작성 페이지
		@RequestMapping("/admin/write")
		public String write() {
			
			return "/blog/admin/blog-admin-write";
		}
}
