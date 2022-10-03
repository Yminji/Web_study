package com.spring.ex03;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.spring.ex01.MemberVO;

@WebServlet("/mem3.do")
public class MemberServlet extends HttpServlet{
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doHandle(request, response);
	}
	protected void doPoset(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doHandle(request, response);
	}
	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("utf-8");
		MemberDAO dao = new MemberDAO();
		MemberVO memberVO = new MemberVO();
		String action = request.getParameter("action");
		String nextPage = "";
		if(action==null || action.equals("listMembers")) {
			List<MemberVO> membersList = dao.selectAllMemberList();
			request.setAttribute("membersList", membersList);
			nextPage = "test02/listMembers.jsp";
		}else if(action.equals("selectMemberById")) { //검색 조건이 selectMemberById이면
			String id = request.getParameter("value");  //전송된 값은 getParameter()로 가져온 후
			memberVO = dao.selectMemberById(id); //SQL문의 조건식에서 id의 조건 값으로 전달함
			request.setAttribute("member", memberVO);
			nextPage = "test02/memberInfo.jsp";
		}else if(action.equals("selectMemberByPwd")) { //검색 조건이 selectMemberByPwd이면
			int pwd = Integer.parseInt(request.getParameter("value")); //전송된 값은 getParameter()로 가져온 후
			List<MemberVO> membersList = dao.selectMemberByPwd(pwd); //SQL문의 조건식에서 pwd의 조건 값으로 전달함
			request.setAttribute("membersList", membersList);
			nextPage = "test02/listMembers.jsp";
		}
		RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
		dispatch.forward(request, response);
	}
}
