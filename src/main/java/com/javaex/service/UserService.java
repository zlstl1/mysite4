package com.javaex.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	public int join(UserVo userVo) {
		return userDao.insert(userVo);
	}
	
	public UserVo login(String password,String email) {
		return userDao.getUser(password,email);
	}
	
	public UserVo getUserInfo(int no) {
		return userDao.getUserInfo(no);
	}
	
	public int userModify(UserVo userVo) {
		return userDao.userModify(userVo);
	}
	
}
