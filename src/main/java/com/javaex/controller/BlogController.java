package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javaex.service.BlogService;
import com.javaex.vo.BlogVo;

@RequestMapping("/{id}")
@Controller
public class BlogController {

	@Autowired
	private BlogService blogService;

	@RequestMapping("")
	public String blogMain(@PathVariable("id") String id,
						   Model model) {

		BlogVo blogVo = blogService.blogMain(id);
		System.out.println(blogVo);
		model.addAttribute("blogVo",blogVo);
		
		return "/blog/blog-main";
	}

	@RequestMapping("/admin/basic")
	public String adminBasic() {

		return "/blog/admin/blog-admin-basic";
	}

	@RequestMapping("/admin/category")
	public String category() {
		
		return "/blog/admin/blog-admin-cate";
	}
}
