package com.spring.member.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.dao.DataAccessException;

import com.spring.member.vo.MemberVO;

public class MemberDAOImpl implements MemberDAO{
	private SqlSession sqlSession;
	
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}//속성 sqlSession에 sqlSession 빈을 주입하기 위해 setter 구현
	
	@Override
	public List selectAllMemberList() throws DataAccessException{
		List<MemberVO> membersList = null;
		//주입된 sqlSession 빈으로 selectList() 메서드를 호출하면서 SQL문의 id를 전달
		membersList = sqlSession.selectList("mapper.member.selectAllMemberList");
		return membersList;
	}
	@Override
	public int insertMember(MemberVO memberVO) throws DataAccessException{
		//주입된 sqlSession 빈으로 insert() 메서드를 호출하면서 SQL문의 id와 memberVO를 전달
		int result = sqlSession.insert("mapper.member.insertMember", memberVO);
		return result;
	}
	@Override
	public int deleteMember(String id) throws DataAccessException{
		//주입된 sqlSession 빈으로 delete() 메서드를 호출하면서 Sql문의 id와 회원 ID를 전달
		int result = sqlSession.delete("mapper.member.deleteMember", id);
		return result;
	}
}
