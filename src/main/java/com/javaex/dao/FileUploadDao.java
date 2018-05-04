package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.FileVo;

@Repository
public class FileUploadDao {

	@Autowired
	private SqlSession sqlSession;
	
	public List<FileVo> getFiles() {
		return sqlSession.selectList("gallery.getList");
	}
	
	public void insert(FileVo fileVo) {
		sqlSession.insert("gallery.insert",fileVo);
	}
	
	public void delete(int no) {
		sqlSession.delete("gallery.delete",no);
	}
}
