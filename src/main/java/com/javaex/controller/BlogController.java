package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.BlogService;
import com.javaex.service.CategoryService;
import com.javaex.vo.BlogVo;
import com.javaex.vo.CateVo;

@RequestMapping("/{id}")
@Controller
public class BlogController {

	@Autowired
	private BlogService blogService;
	@Autowired
	private CategoryService categoryService;

	// 블로그 정보(공통)
	@ModelAttribute("blogVo")
	public BlogVo blogInfo(@PathVariable("id") String id) {
		BlogVo blogVo = blogService.blogMain(id);
		// System.out.println(blogVo);

		return blogVo;
	}

	// 블로그 메인 페이지
	@RequestMapping("")
	public String blogMain() {

		return "/blog/blog-main";
	}

	// 내 블로그 관리
	@RequestMapping("/admin/basic")
	public String adminBasic() {

		return "/blog/admin/blog-admin-basic";
	}

	// 블로그 프로필 자료 업로드 기능
	@RequestMapping("/upload")
	public String upload(@PathVariable("id") String id, @RequestParam("file") MultipartFile file,
			@ModelAttribute BlogVo blogVo) {

		blogService.upload(id, file, blogVo);

		return "/blog/admin/blog-admin-basic";
	}

	// 카테고리 페이지
	@RequestMapping("/admin/category")
	public String category() {

		return "/blog/admin/blog-admin-cate";
	}

	// 글작성 페이지
	@RequestMapping("/admin/writeForm")
	public String writeForm(@PathVariable("id") String id, Model model) {
		List<CateVo> cateOption = categoryService.cateOption(id);
		model.addAttribute("cateOption", cateOption);
		System.out.println(cateOption);
		
		return "blog/admin/blog-admin-write";
	}
}
