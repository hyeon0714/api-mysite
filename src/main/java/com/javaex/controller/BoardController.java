package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.javaex.service.BoardService;
import com.javaex.util.JsonResult;
import com.javaex.util.JwtUtil;
import com.javaex.vo.BoardVo;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
public class BoardController {

	@Autowired
	private BoardService bs;

	@GetMapping("/api/boardlist")
	public JsonResult list() {

		return bs.exeList();
	}

	@PostMapping("/api/boardlist")
	public JsonResult read(@RequestBody int bv) {

		return bs.exeRead(bv);
	}

	@PostMapping("/api/boardmodify")
	public JsonResult modify(@RequestBody BoardVo bv, HttpServletRequest request) {
		int no = JwtUtil.getNoFromHeader(request);

		System.out.println(no);
		
		System.out.println(bv.getNo());
		if(no > 0) {
			BoardVo bvo = bs.exeModifyForm(bv.getNo());
			return JsonResult.success(bvo);
		}else {
			return JsonResult.fail("fail");
		}
	}
	
	@PutMapping("/api/boardmodify")
	public void modify2(@RequestBody BoardVo bv, HttpServletRequest request) {
		int no = JwtUtil.getNoFromHeader(request);

		System.out.println(no);
		
		System.out.println(bv);
		if(no > 0) {
			bs.exeModify(bv);
			
		}else {
			
		}
	}
	

	@PostMapping("/api/boardwrite")
	public JsonResult write(@RequestBody BoardVo bv, HttpServletRequest request) {
		System.out.println(bv);
		
		int no = JwtUtil.getNoFromHeader(request);
		System.out.println(no);
		bv.setUserNo(no);
		System.out.println(bv);

		return bs.exeWrite(bv);

	}
}
