package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.UserService;
import com.javaex.util.JwtUtil;
import com.javaex.vo.UserVo;

import jakarta.servlet.http.HttpServletResponse;

@Controller
public class UserController {
	
	@Autowired
	private UserService us;

	@ResponseBody
	@PostMapping("/api/users/login")
	public UserVo login(@RequestBody UserVo uv, HttpServletResponse response) {
		System.out.println("login");
		
		System.out.println(uv);
		
		UserVo authUser = us.exeLogin(uv);
		
		System.out.println(authUser);
		
		if(authUser != null) {
			//토큰을 발급해서 응답문서의 헤더에 실어보낸다
			JwtUtil.createTokenAndSetHeader(response, ""+authUser.getNo());
		}
		
		return authUser;
	}
}
