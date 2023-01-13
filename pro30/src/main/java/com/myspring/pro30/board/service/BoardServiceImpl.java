package com.myspring.pro30.board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.myspring.pro30.board.dao.BoardDAO;
import com.myspring.pro30.board.vo.ArticleVO;
import com.myspring.pro30.board.vo.ImageVO;

@Service("boardService")
@Transactional(propagation = Propagation.REQUIRED)
public class BoardServiceImpl implements BoardService{
	@Autowired
	private BoardDAO boardDAO;
	
	public List<ArticleVO> listArticles() throws Exception{
		List<ArticleVO> articlesList = boardDAO.selectAllArticlesList();
		return articlesList;
	}
	
	//다중 이미지 추가
	@Override
	public int addNewArticle(Map articleMap) throws Exception{
		//글 정보를 저장한 후 글 번호를 가져옴 
		//insertNewArticle()로 articleMap을 전달해 글 정보를 게시판 테이블에 저장한 후 글 번호를 가져옴
		int articleNO = boardDAO.insertNewArticle(articleMap); 
		articleMap.put("articleNO", articleNO); //글 번호를 다시 aritcleMap에 저장하고 
		//insertNewArticle() 호출하여 이미지 저장
		boardDAO.insertNewImage(articleMap);
		return articleNO;
	}
	
	//다중 파일 보이기
	@Override
	public Map viewArticle(int articleNO) throws Exception{
		Map articleMap = new HashMap();
		//글 정보 조회
		ArticleVO articleVO = boardDAO.selectArticle(articleNO);
		List<ImageVO> imageFileList = boardDAO.selectImageFileList(articleNO);
		articleMap.put("article", articleVO);
		articleMap.put("imageFileList", imageFileList);
		return articleMap;
	}
	
	@Override
	public void modArticle(Map articleMap) throws Exception{
		boardDAO.updateArticle(articleMap);
	}
	
	@Override
	public void removeArticle(int articleNO) throws Exception{
		boardDAO.deleteArticle(articleNO);
	}
}
