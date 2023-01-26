package com.bookshop01.goods.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bookshop01.goods.service.GoodsService;

@Controller("goodsControlelr")
@RequestMapping(value="/goods")
public class GoodsControllerImpl implements GoodsController{
	@Autowired
	private GoodsService goodsService;
}
