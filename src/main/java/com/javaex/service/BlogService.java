package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.BlogDao;
import com.javaex.vo.BlogVo;

@Service
public class BlogService {

	@Autowired
	private BlogDao blogDao;

	public BlogVo blogMain(String id) {

		BlogVo blogVo = blogDao.blogMain(id);

		return blogVo;
	}

	
	public void upload(String id, MultipartFile file, BlogVo blogVo) {

		String savDir = "C:\\javaStudy\\upload";

		// 원본파일이름 저장
		String orgName = file.getOriginalFilename();

		// 확장자 추출 - 온점의 위치를 찾아서 그 이후의 값 반환.
		String exName = orgName.substring(file.getOriginalFilename().lastIndexOf("."));

		// 저장파일이름
		String saveName = System.currentTimeMillis() + UUID.randomUUID().toString() + exName;

		// 파일패스 생성
		String logoFile = savDir + "\\" + saveName;

		
		////////////////파일 저장(사용자입장에서는 업로드)
		try {
			byte[] fileData = file.getBytes();
			OutputStream out = new FileOutputStream(logoFile);
			BufferedOutputStream bout = new BufferedOutputStream(out);

			bout.write(fileData);
			bout.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		//////////////// DB에 관련정보 저장 - Gallery
		blogVo.setLogoFile(saveName);
		blogVo.setId(id);
		
		System.out.println(blogVo);
		
		blogDao.upload(blogVo);
	}
	
}
