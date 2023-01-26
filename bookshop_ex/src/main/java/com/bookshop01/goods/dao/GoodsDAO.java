package com.bookshop01.goods.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.bookshop01.goods.vo.GoodsVO;

public interface GoodsDAO {
	public List<GoodsVO> selectGoodsList(String goodsStatus) throws DataAccessException;
}
