package com.spring.ex02;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller("LoginController") //컨트롤러 빈을 자동으로 생성
public class LoginController {
	// "/test/loginForm.do와 /test/loginForm2.do"로 요청 시 loginForm()을 호출
	@RequestMapping(value= {"/test/loginForm.do", "/test/loginForm2.do"}, method= {RequestMethod.GET})
	public ModelAndView loginForm(HttpServletRequest request, HttpServletResponse response) throws Exception{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("loginForm");
		return mav;
	}
	
	//GET과 POST 방식 요청 시 모두 처리
	@RequestMapping(value="/test/login.do", method= {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("utf-8");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("result");
		String userID = request.getParameter("userID");
		String userName = request.getParameter("userName");
		mav.addObject("userID", userID);
		mav.addObject("userName", userName);
		return mav;
	}
	
	//@RequestParam의 required 속성을 생략하면 기본값 true(메서드 호출 시 반드시 지정지된 매개변수 전달해야 함), false 설정하면(매개변수 전달되면 값 저장 없으면 null)
	@RequestMapping(value="/test/login2.do", method= {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView login2(@RequestParam("userID") String userID, @RequestParam(value="userName", required=true) String userName, 
			@RequestParam(value="email", required=false) String email,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("utf-8");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("result");
		System.out.println("userID:"+userID);
		System.out.println("userName:"+userName);
		mav.addObject("userID", userID);
		mav.addObject("userName", userName);
		return mav;
	}
	
	//@RequestParam을 이용해 Map에 전송된 매개변수 이름을 key, 값을 value로 저장
	@RequestMapping(value="/test/login3.do", method= {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView login3(@RequestParam Map<String, String> info,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		ModelAndView mav = new ModelAndView();
		//Map에 저장된 매개변수의 이름으로 전달된 값 가져옴
		String userID = info.get("userID");
		String userName = info.get("userName");
		System.out.println("userID:"+userID);
		System.out.println("userName:"+userName);
		mav.addObject("info", info); //@Requestparam에서 설정한 Map 이름으로 바인딩함
		mav.setViewName("result");
		return mav;
	}
	
	//@ModelAttribute를 이용해 전달되는 매개변수 값을 LoginVO 클래스와 이름이 같은 속성에 자동으로 설정, 
	//addObject()를 이용할 필요 없이 info를 이용해 바로 JSP에서 LoginVO 속성에 접근할 수 있음
	@RequestMapping(value="/test/login4.do", method= {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView login4(@ModelAttribute("info") LoginVO loginVO,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("utf-8");
		ModelAndView mav = new ModelAndView();
		System.out.println("userID:"+loginVO.getUserID());
		System.out.println("userName:"+loginVO.getUserName());
		mav.setViewName("result");
		return mav;
	}
	
	//Model 클래스 이용하면 메서드 호출 시 JSP로 값을 바로 바인딩할 수 있음
	@RequestMapping(value="/test/login5.do", method= {RequestMethod.GET, RequestMethod.POST})
	public String login5(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("utf-8");
		model.addAttribute("userID", "hong");
		model.addAttribute("userName", "홍길동");
		return "result";
	}
}
