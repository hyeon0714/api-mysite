package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.javaex.service.UserService;
import com.javaex.util.JsonResult;
import com.javaex.util.JwtUtil;
import com.javaex.vo.UserVo;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController//컨트롤러에 리스폰 바디를 합친것
public class UserController {
	
	@Autowired
	private UserService us;
	
	//로그인
	@PostMapping("/api/users/login")
	public JsonResult login(@RequestBody UserVo uv, HttpServletResponse response) {
		System.out.println("login");
		
		System.out.println(uv);
		
		UserVo authUser = us.exeLogin(uv);
		
		System.out.println(authUser);
		
		if(authUser != null) {
			//토큰을 발급해서 응답문서의 헤더에 실어보낸다
			JwtUtil.createTokenAndSetHeader(response, ""+authUser.getNo());
			return JsonResult.success(authUser);
		}else {
			return JsonResult.fail("로그인 실패");
		}
		
		
	}
	
	//수정폼(1명 데이터 가져오기) --> 토큰을 받아서 이상없으면 데이터 출력
	@GetMapping("/api/users/modifyform")
	public JsonResult modifyForm(HttpServletRequest request) {
		System.out.println("modify");
		/*
		//토큰꺼내기
		String token = JwtUtil.getTokenByHeader(request);
		System.out.println(token);
		
		//토큰 검증
		if(JwtUtil.checkToken(token)==true) {
			
			//검증후 정상이면 no값 출력
			String no = JwtUtil.getSubjectFromToken(token);
			System.out.println(no);
		}
		*/
		//위의 것을 no를 기준으로 한번에 묶은것
		int no = JwtUtil.getNoFromHeader(request);
		
		if(no != -1) {//정상
			//int no = 3;
			
			UserVo userVo = us.exeModifyForm(no);
			return JsonResult.success(userVo);
			
		}else {//토큰이 없거나 변조된 경우
			return JsonResult.fail("fail");
		}
	}
	
	//수정(데이터 수정)
	@PutMapping("/api/users/modifyform")
	public JsonResult modify(@RequestBody UserVo uv, HttpServletRequest request) {
		System.out.println("modify");
		System.out.println(uv);
		
		//토큰값 검증
		int no = JwtUtil.getNoFromHeader(request);
		
		if(no != -1) {//정상
			//int no = 3;
			String name = us.exeModify(uv);
			return JsonResult.success(name);
			
		}else {//토큰이 없거나 변조된 경우
			return JsonResult.fail("로그인하지 않음");
		}
	}
	
	//회원가입
	@PostMapping("/api/users/joinform")
	public JsonResult joinform(@RequestBody UserVo uv) {
		System.out.println("join");
		System.out.println(uv);
		
		int count = us.exeJoin(uv);
		
		if(count == 1) {
			System.out.println(count);
			return JsonResult.success(count);
		}else {
			return JsonResult.fail("회원가입이 실패했습니다");
		}
	}
	
	//아이디 중복체크
	@PutMapping("/api/users/joinform")
	public void idCheck(@RequestBody UserVo uv) {
	
		System.out.println(uv);
		String id = uv.getId();
		
		us.exeIdCheck(id);
	}
}
