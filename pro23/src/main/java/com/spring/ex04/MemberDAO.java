package com.spring.ex04;

import java.io.Reader;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.spring.ex01.MemberVO;

public class MemberDAO {
	private static SqlSessionFactory sqlMapper = null;
	private static SqlSessionFactory getInstance() {
		if(sqlMapper == null) {
			try {
				String resource = "mybatis/SqlMapConfig.xml";
				Reader reader = Resources.getResourceAsReader(resource);
				sqlMapper = new SqlSessionFactoryBuilder().build(reader);
				reader.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return sqlMapper;
	}
	
	public MemberVO selectMemberById(String id) {
		sqlMapper =getInstance();
		SqlSession session = sqlMapper.openSession();
		MemberVO memberVO = session.selectOne("mapper.member.selectMemberById", id);
		return memberVO;
	}
	
	public List<MemberVO> selectMemberByPwd(int pwd){
		sqlMapper = getInstance();
		SqlSession session = sqlMapper.openSession();
		List<MemberVO> membersList = session.selectList("mapper.member.selectMemberByPwd", pwd);
		return membersList;
	}
	
	public List<MemberVO> selectAllMemberList(){
		sqlMapper = getInstance();
		SqlSession session = sqlMapper.openSession();
		List<MemberVO> memlist =  null;
		memlist = session.selectList("mapper.member.selectAllMemberList");
		return memlist;
	}
	
	public int insertMember(MemberVO memberVO) {
		sqlMapper = getInstance();
		SqlSession session = sqlMapper.openSession();
		int result = 0;
		//지정한 id의 SQL문에 memberVO의 값을 전달하여 회원 정보를 테이블에 추가함
		result = session.insert("mapper.member.insertMember", memberVO);
		session.commit(); //수동 커밋이므로 반드시 commit() 메서드를 호출하여 영구 반영함
		return result;
	}
	
	public int insertMember2(Map<String, String> memberMap) {
		sqlMapper = getInstance();
		SqlSession session = sqlMapper.openSession();
		int result = session.insert("mapper.member.insertMember2",memberMap);
		session.commit();
		return result;
	}
	
	public int updateMember(MemberVO memberVO) {
		sqlMapper = getInstance();
		SqlSession session = sqlMapper.openSession();
		int result = session.update("mapper.member.updateMember", memberVO);
		session.commit();
		return result;
	}
	
	public int deleteMember(String id) {
		sqlMapper = getInstance();
		SqlSession session = sqlMapper.openSession();
		int result= 0;
		result = session.delete("mapper.member.deleteMember", id);
		session.commit();
		return result;
	}
	
	public List<MemberVO> searchMember(MemberVO memberVO){
		sqlMapper = getInstance();
		SqlSession session = sqlMapper.openSession();
		List<MemberVO> list = session.selectList("mapper.member.searchMember", memberVO);
		return list;
	}
	
	public List<MemberVO> foreachSelect(List<String> nameList){
		sqlMapper = getInstance();
		SqlSession session = sqlMapper.openSession();
		List<MemberVO> list = session.selectList("mapper.member.foreachSelect", nameList);
		return list;
	}
	
	public int foreachInsert(List<MemberVO> memList) {
		sqlMapper = getInstance();
		SqlSession session = sqlMapper.openSession();
		int result = session.insert("mapper.member.foreachInsert", memList);
		session.commit(); //반드시 commit 호출
		return result;
	}
}
