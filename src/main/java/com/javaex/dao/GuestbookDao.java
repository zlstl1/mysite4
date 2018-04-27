package com.javaex.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GuestbookVO;

@Repository
public class GuestbookDao {
		
	@Autowired
	private SqlSession sqlSession;
	
	public List<GuestbookVO> getList() {
		return sqlSession.selectList("guestbook.getList");
	}
	
	public void add(GuestbookVO guestbookVo) {
		sqlSession.insert("guestbook.add",guestbookVo);
	}
	
	public void delete(GuestbookVO guestbookVo) {
		sqlSession.delete("guestbook.delete", guestbookVo);
	}
}
