package com.bookshop01.order.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bookshop01.common.base.BaseController;
import com.bookshop01.goods.vo.GoodsVO;
import com.bookshop01.member.vo.MemberVO;
import com.bookshop01.order.service.OrderService;
import com.bookshop01.order.vo.OrderVO;

@Controller("orderController")
@RequestMapping("/order")
public class OrderControllerImpl extends BaseController implements OrderController{
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private OrderVO orderVO;
	
	//상품 상세 페이지에서 주문
	@RequestMapping(value="/orderEachGoods.do", method=RequestMethod.POST)
	public ModelAndView orderEachGoods(@ModelAttribute("orderVO") OrderVO _orderVO, 
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		
		Boolean isLogOn = (Boolean) session.getAttribute("isLogOn");
		String action = (String)session.getAttribute("action");
		
		//로그인여부 체그
		if(isLogOn == null || isLogOn == false) { //로그인을 하지 않았으면
			session.setAttribute("orderInfo", _orderVO);  //주문 정보 세션에 저장
			session.setAttribute("action", "/order/orderEachGoods.do"); //주문 페이지 요청 URL 세션에 저장
			return new ModelAndView("redirect:/member/loginForm.do"); //로그인창 반환
		}else { //로그인을 했다면
			if(action != null && action.equals("/order/orderEachGoods.do")) { //주문 페이지이면
				orderVO = (OrderVO)session.getAttribute("orderInfo"); //세션에서 주문 정보를 가져옴
				session.removeAttribute("action"); 
			}else { //미리 로그인을 했다면 바로 주문 처리
				orderVO = _orderVO; 
			}
			
			String viewName = (String)request.getAttribute("viewName");
			ModelAndView mav = new ModelAndView(viewName);
			
			List myOrderList = new ArrayList<OrderVO>(); 
			myOrderList.add(orderVO); //ArrayList에 주문 상품 정보를 저장
			
			MemberVO memberInfo = (MemberVO) session.getAttribute("memberInfo"); //세션에서 회원 정보를 가져옴
			
			session.setAttribute("myOrderList", myOrderList); //주문 목록을 세션에 저장
			session.setAttribute("memberInfo", memberInfo); //회원 정보를 세션에 저장
			return mav;
		}
	}
	
	//장바구니 상품 주문
	@RequestMapping(value="/orderAllCartGoods.do", method=RequestMethod.POST)
	//선택된 상품 수량을 배열로 받음
	public ModelAndView orderAllCartGoods(@RequestParam("cart_goods_qty") String[] cart_goods_qty,
			HttpServletRequest request, HttpServletResponse response ) throws Exception{
		String viewName = (String)request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);
		HttpSession session = request.getSession();
		
		Map cartMap = (Map)session.getAttribute("cartMap");  //세션에서 cartMap을 가져옴
		List myOrderList = new ArrayList<OrderVO>(); //arrayList에 주문 정보를 저장할 거임
		List<GoodsVO> myGoodsList = (List<GoodsVO>)cartMap.get("myGoodsList"); //carMap에서 장바구니 상품 목록을 가져와 리스트에 저장
		MemberVO memberVO = (MemberVO) session.getAttribute("memeberInfo"); //세션에서 회원 정보를 가져옴
		
		for(int i = 0; i<cart_goods_qty.length; i++) { //장바구니 상품 개수만큼 반복
			//문자열로 결합되어 전송된 상품 번호와 주문 수량을 split() 메서드를 이용해 분리
			String[] cart_goods = cart_goods_qty[i].split(":"); 
			for(int j = 0; j < myGoodsList.size(); j++) {
				GoodsVO goodsVO = myGoodsList.get(j); //장바구니 목록에서 차례대로 GoodsVO를 가져옴
				int goods_id = goodsVO.getGoods_id(); //GoodsVO의 상품 번호를 가져옴
				
				if(goods_id == Integer.parseInt(cart_goods[0])) { //전송된 상품 번호와 GoodsVO의 상품 번호가 같으면 주문하는 상품이므로
					OrderVO _orderVO = new OrderVO(); //OrderVO 객체를 생성
					String goods_title = goodsVO.getGoods_title();
					int goods_sales_price = goodsVO.getGoods_sales_price();
					String goods_fileName = goodsVO.getGoods_fileName();
					//상품 정보를 orderVO에 설정
					_orderVO.setGoods_id(goods_id);
					_orderVO.setGoods_title(goods_title);
					_orderVO.setGoods_sales_price(goods_sales_price);
					_orderVO.setGoods_fileName(goods_fileName);
					_orderVO.setOrder_goods_qty(Integer.parseInt(cart_goods[1]));
					myOrderList.add(_orderVO); //주문 목록에 저장
					break;
				}
			}
		}
		session.setAttribute("myOrderList", myOrderList); //주문 목록 세션에 저장
		session.setAttribute("orderer", memberVO); //주문자 정보 세션에 저장
		return mav;
	}
	
	@RequestMapping(value="/payToOrderGoods.do", method=RequestMethod.POST)
	//주문창에서 입력한 상품 수령자 정보와 배송지 정보를 Map에 바로 저장
	public ModelAndView payToOrderGoods(@RequestParam Map<String, String> receiverMap,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String viewName = (String)request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);
		
		HttpSession session = request.getSession();
		MemberVO memberVO = (MemberVO) session.getAttribute("orderer");
		String member_id = memberVO.getMember_id();
		String orderer_name = memberVO.getMember_name();
		String orderer_hp = memberVO.getHp1()+"-"+memberVO.getHp2()+"-"+memberVO.getHp3();
		List<OrderVO> myOrderList = (List<OrderVO>)session.getAttribute("myOrderList"); //세션에서 주문 리스트를 가져옴
		
		//주문창에서 입력한 수령자 정보와 배송지 정보를 주문 상품 정보 목록과 합침
		for(int i = 0; i < myOrderList.size(); i++) {
			OrderVO orderVO = (OrderVO) myOrderList.get(i);
			orderVO.setMember_id(member_id);
			orderVO.setOrderer_name(orderer_name);
			orderVO.setOrderer_hp(orderer_hp);
			
			//브라우저에서 받아온 정보 orderVO에 저장
			orderVO.setReceiver_hp1(receiverMap.get("receiver_hp1"));
			orderVO.setReceiver_hp2(receiverMap.get("receiver_hp2"));
			orderVO.setReceiver_hp3(receiverMap.get("receiver_hp3"));
			orderVO.setReceiver_tel1(receiverMap.get("receiver_tel1"));
			orderVO.setReceiver_tel2(receiverMap.get("receiver_tel2"));
			orderVO.setReceiver_tel3(receiverMap.get("receiver_tel3"));
			
			orderVO.setDelivery_address(receiverMap.get("delivery_address"));
			orderVO.setDelivery_message(receiverMap.get("delivery_message"));
			orderVO.setDelivery_method(receiverMap.get("delivery_method"));
			orderVO.setGift_wrapping(receiverMap.get("gift_wrapping"));
			orderVO.setPay_method(receiverMap.get("pay_method"));
			orderVO.setCard_com_name(receiverMap.get("cart_com_name"));
			orderVO.setCard_pay_month(receiverMap.get("cart_pay_month"));
			orderVO.setPay_orderer_hp_num(receiverMap.get("pay_orderer_hp_num"));
			
			myOrderList.set(i, orderVO); //각 orderVO에 주문자 쩡보를 세팅한 후 다시 myOrderList에 저장
		}
		
		orderService.addNewOrder(myOrderList); //주문 정보를 SQL문에 전달
		mav.addObject("myOrderInfo", receiverMap); //주문 완료 결돠창에 주문자 정보를 표시하도록 전달
		mav.addObject("myOrderList", myOrderList); //주문 완료 결과창에 주문 상품 목록을 표시하도록 전달
		return mav;
	}
}
