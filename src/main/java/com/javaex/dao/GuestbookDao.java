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
	
	public List<GuestbookVO> getScrollList(int start,int end) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("start", start);
		map.put("end", end);
		return sqlSession.selectList("guestbook.getScrollList",map);
	}
	
	public void add(GuestbookVO guestbookVo) {
		sqlSession.insert("guestbook.add",guestbookVo);
	}
	
	public void delete(GuestbookVO guestbookVo) {
		sqlSession.delete("guestbook.delete", guestbookVo);
	}
	
	public int ajax_delete(GuestbookVO guestbookVo) {
		return sqlSession.delete("guestbook.delete", guestbookVo);
	}
	
	public int insert2(GuestbookVO guestbookVo) {
		sqlSession.insert("guestbook.insert2",guestbookVo);
		return guestbookVo.getNo();
	}
	
	public GuestbookVO select(int no) {
		return sqlSession.selectOne("guestbook.select",no);
	}
	
}
