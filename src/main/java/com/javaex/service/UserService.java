package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Service
public class UserService {

	@Autowired
	private UserDao ud;
	
	//로그인
	public UserVo exeLogin(UserVo uv) {
		
		UserVo authUser = ud.login(uv);
		
		return authUser; 
	}
	
	//수정폼(1명 데이터 가져오기)
	public UserVo exeModifyForm(int no) {
		
		System.out.println("UserService.exeModifyForm()");

		UserVo userVo = ud.userSelectOneByNo(no);
		
		System.out.println(userVo.toString());
		
		return userVo;
	}
	
	// 회원정보 수정
	public String exeModify(UserVo userVo) {
		System.out.println("UserService.exeModify()");

		ud.userUpdate(userVo);
		
		
			
		UserVo uv = ud.getName(userVo.getNo());
		System.out.println(uv.getName());
		return uv.getName();
		
	}
	
	//회원가입
	public int exeJoin(UserVo uv) {
		
		return ud.join(uv);
	}
	
	//아이디 중복체크
	public int exeIdCheck(String id) {
		
		return ud.idCheck(id);
	}
}
