package com.bookshop01.cart.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bookshop01.cart.dao.CartDAO;
import com.bookshop01.cart.vo.CartVO;
import com.bookshop01.goods.vo.GoodsVO;

@Service("cartService")
@Transactional(propagation=Propagation.REQUIRED)
public class CartServiceImpl implements CartService{
	@Autowired
	private CartDAO cartDAO;
	
	public Map<String, List> myCartList(CartVO cartVO) throws Exception{
		Map<String, List> cartMap = new HashMap<String, List>();
		//장바구니 페이지에 표시할 장바구니 정보 조회
		List<CartVO> myCartList = cartDAO.selectCartList(cartVO); 
		if(myCartList.size() == 0) //카트에 저장된 상품이 없으면
			return null;
		//장바구니 페이지에 표시할 상품 정보 조회
		List<GoodsVO> myGoodsList = cartDAO.selectGoodsList(myCartList);
		//장바구니 정보와 상품 정보를 cartMap에 저장하여 반환 
		cartMap.put("myCartList", myCartList);
		cartMap.put("myGoodsList", myGoodsList);
		return cartMap;
	}
	//테이블에 추가하기 전에 동일한 상품 번호의 개수를 조회
	public boolean findCartGoods(CartVO cartVO) throws Exception{
		return cartDAO.selectCountInCart(cartVO);
	}
	//장바구니에 추가
	public void addGoodsInCart(CartVO cartVO) throws Exception{
		cartDAO.insertGoodsInCart(cartVO);
	}
	//장바구니 상품 수정
	public boolean modifyCartQty(CartVO cartVO) throws Exception{
		boolean result = true;
		cartDAO.updateCartGoodsQty(cartVO);
		return result;
	}
	//장바구니 상품 삭제
	public void removeCartGoods(int cart_id) throws Exception{
		cartDAO.deleteCartGoods(cart_id);
	}
	
}
