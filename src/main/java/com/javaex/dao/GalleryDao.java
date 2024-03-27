package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GalleryVo;

@Repository
public class GalleryDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public List<GalleryVo> list() {
		
		List<GalleryVo> gvList = sqlSession.selectList("gallery.list");
		
		System.out.println(gvList);
		return gvList;
	}
	
	public int upload(GalleryVo gv) {
		
		return sqlSession.insert("gallery.insert", gv);
	}
}
