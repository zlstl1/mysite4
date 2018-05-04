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
	
	public List<GuestbookVO> getScrollList(int start,int end) {
		return guestbookDao.getScrollList(start,end);
	}
	
	public void add(GuestbookVO guestbookVo) {
		guestbookDao.add(guestbookVo);
	}
	
	public void delete(GuestbookVO guestbookVo) {
		guestbookDao.delete(guestbookVo);
	}
	
	public int ajax_delete(GuestbookVO guestbookVo) {
		return guestbookDao.ajax_delete(guestbookVo);
	}
	
	public GuestbookVO write(GuestbookVO guestbookVo) {
		int no = guestbookDao.insert2(guestbookVo);
		
		GuestbookVO vo = guestbookDao.select(no);
		return vo; 
	}
	
}
