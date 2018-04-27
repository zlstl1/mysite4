package com.javaex.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository
public class UserDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public int insert(UserVo userVo) {
		return sqlSession.insert("user.insert",userVo);
	}
	
	public UserVo getUser(String password, String email) {		
		Map<String,String> map = new HashMap<String,String>();
		map.put("password", password);
		map.put("email", email);
		
		return sqlSession.selectOne("user.getUser",map);
	} 
	
	public UserVo getUserInfo(int no) {
		return sqlSession.selectOne("user.getUserInfo",no);
	}
	
	public int userModify(UserVo userVo) {
		return sqlSession.update("user.userModify",userVo);
	}
	
}
