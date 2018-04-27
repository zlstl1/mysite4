package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.GuestbookDao;
import com.javaex.vo.GuestbookVO;

@Service
public class GuestbookService {
	
	@Autowired
	private GuestbookDao guestbookDao;
	
	public List<GuestbookVO> getList() {
		return guestbookDao.getList();
	}
	
	public void add(GuestbookVO guestbookVo) {
		guestbookDao.add(guestbookVo);
	}
	
	public void delete(GuestbookVO guestbookVo) {
		guestbookDao.delete(guestbookVo);
	}
	
}
