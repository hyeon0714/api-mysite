package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BoardVo;

@Repository
public class BoardDao {

	@Autowired
	private SqlSession sqlSession;

	// 리스트 가져오기
	public List<BoardVo> list() {

		List<BoardVo> bList = sqlSession.selectList("board.list");

		System.out.println(bList);

		return bList;

	}

	// 조회수 가져오기
	public BoardVo hit(int no) {
		
		return sqlSession.selectOne("board.hit", no);
	}

	// 조회수 올리기
	public void upHit(BoardVo bv) {
		
		sqlSession.update("board.upHit", bv);
	}

	// 읽기
	public BoardVo read(int no) {

		return sqlSession.selectOne("board.read", no);
	}
	
	//쓰기
	public int write(BoardVo bv) {
		
		int count = -1;
		
		count = sqlSession.insert("board.write", bv);
		return count;
	}
	
	//수정
	public void modify(BoardVo bv) {
		
		sqlSession.update("board.modify", bv);
	}

}
