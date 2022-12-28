package com.spring.member.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.member.service.MemberService;
import com.spring.member.vo.MemberVO;

@Controller("memberController")
public class MemberControllerImpl implements MemberController{
	@Autowired //setter를 사용하지 않고 생성된 빈을 속성에 주입
	private MemberService memberService; //@Autowired를 이용해 id가 memberService인 빈을 자동 주입
	@Autowired
	private MemberVO memberVO; //@Autowried를 이용해 id가 memberVO인 빈을 자동 주입
	
	@Override
	@RequestMapping(value="/member/listMembers.do", method=RequestMethod.GET)
	public ModelAndView listMembers(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String viewName = getViewName(request);
		List membersList = memberService.listMembers();
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("membersList", membersList);
		return mav;
	}
	
	@Override
	@RequestMapping(value="/member/addMember.do", method=RequestMethod.POST)
	//@ModelAttribute("member") 회원가입창에서 전송된 회원 정보를 바로 MemberVO 객체에 설정
	public ModelAndView addMember(@ModelAttribute("member") MemberVO member, 
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		int result = 0;
		ModelAndView mav = new ModelAndView("redirect:/member/listMembers.do");
		return mav;
	}
	
	@Override
	@RequestMapping(value="/member/removeMember.do", method=RequestMethod.GET)
	public ModelAndView removeMember(@RequestParam("id") String id, 
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("utf-8");
		memberService.removeMember(id);
		ModelAndView mav = new ModelAndView("redirect:/member/listMembers.do");
		return mav;
	}
	
	//정규식을 이용해 요청명이 Form.do로 끝나면 form() 메서드 호출
	//여러 요청에 대해 한 개의 메서드를 호출할 경우 정규식을 이용해 매핑하는 역할 -> Form.do로 끝나는 모든 요청에 대해 동일한 메서드 호출
	@RequestMapping(value="/member/*Form.do", method=RequestMethod.GET)
	public ModelAndView form(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String viewName = getViewName(request);
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		return mav;
	}
	
	private String getViewName(HttpServletRequest request) throws Exception{
		String contextPath = request.getContextPath();
		String uri = (String)request.getAttribute("javax.servlet.include.request_uri");
		if(uri == null || uri.trim().equals("")) {
			uri = request.getRequestURI();
		}
		int begin = 0;
		if(!((contextPath == null)||("".equals(contextPath)))) {
			begin  = contextPath.length();
		}
		
		int end;
		if(uri.indexOf(";")!=-1) {
			end = uri.indexOf(";");
		}else if(uri.indexOf("?")!=-1) {
			end = uri.indexOf("?");
		}else {
			end = uri.length();
		}
		
		String viewName = uri.substring(begin, end);
		if(viewName.indexOf(".")!=-1) {
			viewName = viewName.substring(0, viewName.lastIndexOf("."));
		}
		if(viewName.indexOf("/")!=-1) {
			viewName = viewName.substring(viewName.lastIndexOf("/"), viewName.length());
		}
		return viewName;
	}
}
