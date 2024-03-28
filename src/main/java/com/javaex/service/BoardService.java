package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BoardDao;
import com.javaex.util.JsonResult;
import com.javaex.vo.BoardVo;

@Service
public class BoardService {
	
	@Autowired
	private BoardDao bd;
	
	public JsonResult exeList() {
		
		List<BoardVo> bList = bd.list();
		
		if(bList != null) {
			return JsonResult.success(bList);
		}else {
			return JsonResult.fail("리스트를 불러오지 못했습니다");
		}
	}
	
	//읽기(조회수 올리기도 포함)
	public JsonResult exeRead(int no) {
		
		BoardVo vo = bd.hit(no);
		
		int a = vo.getHit()+1;
		
		vo.setHit(a);
		vo.setNo(no);
		
		bd.upHit(vo);
		
		//System.out.println(vo);
		
		BoardVo bVo = bd.read(no);
		
		System.out.println(bVo);
		
		if(bVo != null) {
			return JsonResult.success(bVo);
		}else {
			return JsonResult.fail("오류");
		}
	}
	
	//쓰기
	public JsonResult exeWrite(BoardVo bv) {
		
		int a = bd.write(bv);
		
		String ok = "ok";
		
		if(a==1) {
			return JsonResult.success(ok);
		}else {
			return JsonResult.fail("fail");
		}
	}
	
	//수정폼 글 읽기
	public BoardVo exeModifyForm(int no) {
		
		BoardVo bv = bd.read(no);
		
		System.out.println(bv);
		
		return bv;
	}
	
	//수정
	public void exeModify(BoardVo bv) {
		
		bd.modify(bv);
	}
}
