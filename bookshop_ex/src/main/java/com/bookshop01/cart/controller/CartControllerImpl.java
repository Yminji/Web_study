package com.bookshop01.cart.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bookshop01.cart.service.CartService;
import com.bookshop01.cart.vo.CartVO;
import com.bookshop01.common.base.BaseController;
import com.bookshop01.member.vo.MemberVO;

@Controller("cartController")
@RequestMapping("/cart")
public class CartControllerImpl extends BaseController implements CartController{
	@Autowired
	private CartService cartService;
	
	@Autowired
	private CartVO cartVO;
	
	@Autowired
	private MemberVO memberVO;
	
	@Override
	@RequestMapping(value="/myCartList.do", method=RequestMethod.GET)
	public ModelAndView myCartMain(HttpServletRequest request, HttpServletResponse resopsne) throws Exception{
		String viewName = (String)request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);
		HttpSession session = request.getSession();
		MemberVO memberVO = (MemberVO)session.getAttribute("memberInfo");
		String member_id = memberVO.getMember_id();
		cartVO.setMember_id(member_id);
		Map<String, List> cartMap = cartService.myCartList(cartVO); //회원 번호가 같은 장바구니 목록 조회에 cartMap에 저장
		session.setAttribute("cartMap", cartMap);//장바구니 목록 화면에서 상품 주문 시 사용하기 위해 장바구니 목록을 세션에 저장
		return mav;
	}
	
	@Override
	@RequestMapping(value="/addGoodsInCart.do", method=RequestMethod.POST, produces="application/text; charset=utf-8")
	public @ResponseBody String addGoodsInCart(@RequestParam("goods_id") int goods_id, HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession session = request.getSession();
		memberVO = (MemberVO)session.getAttribute("memberInfo");
		String member_id = memberVO.getMember_id();
		cartVO.setMember_id(member_id);
		cartVO.setGoods_id(goods_id);
		//상품 번호가 장바구니 테이블에 있는지 조회
		boolean isAreadyExisted = cartService.findCartGoods(cartVO);
		if(isAreadyExisted == true)
			return "already_existed";
		else {
			cartService.addGoodsInCart(cartVO);
			return "add_success";
		}
	}
	
	@Override
	@RequestMapping(value="/modifyCartQty.do", method=RequestMethod.POST)
	public @ResponseBody String modifyCartQty(@RequestParam("goods_id") int goods_id, @RequestParam("cart_goods_qty") int cart_goods_qty,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession session = request.getSession();
		memberVO = (MemberVO) session.getAttribute("memberInfo");
		String member_id = memberVO.getMember_id();
		cartVO.setMember_id(member_id);
		cartVO.setGoods_id(goods_id);
		cartVO.setCart_goods_qty(cart_goods_qty);
		boolean result = cartService.modifyCartQty(cartVO); //상품 변경 유무 확인
		if(result == true)
			return "modify_success";
		else
			return "modify_failes";
		
	}
	
	@RequestMapping(value="/removeCartGoods.do", method=RequestMethod.POST)
	public ModelAndView removeCartGoods(@RequestParam("cart_id") int cart_id,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		ModelAndView mav = new ModelAndView();
		cartService.removeCartGoods(cart_id);
		mav.setViewName("redirect:/cart/myCartList.do");
		return mav;
	}
}
