package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository
public class UserDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	//로그인
	public UserVo login(UserVo uv) {
		
		return sqlSession.selectOne("user.selectByIdPw", uv);
	}
	
	//조회no(회원정보수정 폼)
	public UserVo userSelectOneByNo(int no) {
		System.out.println("UserDao.userSelectOneByNo()");
		
		UserVo userVo = sqlSession.selectOne("user.selectOneByNo", no);
		return userVo;
	}
	
	//회원정보수정
	public int userUpdate(UserVo uv) {
		
		return sqlSession.update("user.update", uv);
	}
	
	//수정후 수정된 이름 가져오기
	public UserVo getName(int no) {
		
		UserVo userVo = sqlSession.selectOne("user.getName", no);
		
		return userVo;
	}
	
	//회원가입
	public int join(UserVo uv) {
		
		int count = sqlSession.insert("user.join", uv);
		
		return count;
	}
	
	//아이디 중복체크
	public int idCheck(String id) {
		
		int a = sqlSession.selectOne("user.idCheck", id);
		
		System.out.println(a);
		
		return a;
		
	}
}
