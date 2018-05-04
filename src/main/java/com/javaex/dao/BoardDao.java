package com.javaex.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BoardVo;

@Repository
public class BoardDao {
		
	@Autowired
	private SqlSession sqlSession;
	
	public List<BoardVo> getList() {
		return sqlSession.selectList("board.getList");
	}
	
	public void write(BoardVo boardVo) {
		sqlSession.insert("board.write",boardVo);
	}
	
	public BoardVo view(int no) {
		return sqlSession.selectOne("board.view",no);
	}
	
	public void hitup(int no) {
		sqlSession.update("board.hitup",no);
	}
	
	public void modify(BoardVo boardVo) {
		sqlSession.update("board.modify",boardVo);
	}
	
	public void delete(int no) {
		sqlSession.delete("board.delete",no);
	}
	
	public List<BoardVo> search(String kwd) {
		return sqlSession.selectList("board.search",kwd);
	}
	
}
