package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;

	public void join(UserVo userVo) {
		System.out.println("UserService.join");

		userDao.join(userVo);
	}

	public String idCheck(String id) {
		System.out.println("service: " + id);

		String cId = userDao.idCheck(id);
		System.out.println("cId: " + cId);
		
		if (id.equals(cId)) {
			return "n";
		}else {
			return "y";
		}	
	}

}
