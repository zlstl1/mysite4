package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;

@Service
public class BoardService {
	
	@Autowired
	private BoardDao boardDao;
	
	public List<BoardVo> getList() {
		return boardDao.getList();
	}
	
	public void write(BoardVo boardVo) {
		boardDao.write(boardVo);
	}
	
	public BoardVo view(int no) {
		return boardDao.view(no);
	}
	
	public void hitup(int no,int hit) {
		boardDao.hitup(no,hit);
	}
	
	public void modify(BoardVo boardVo) {
		boardDao.modify(boardVo);
	}
	
	public void delete(int no) {
		boardDao.delete(no);
	}
	
	public List<BoardVo> search(String kwd) {
		kwd="%"+kwd+"%";
		return boardDao.search(kwd);
	}
}
