package com.myspring.pro30.board.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.myspring.pro30.board.vo.ArticleVO;
import com.myspring.pro30.board.vo.ImageVO;

@Repository("boardDAO")
public class BoardDAOImpl implements BoardDAO{
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List selectAllArticlesList() throws Exception{
		List<ArticleVO> articlesList = sqlSession.selectList("mapper.board.selectAllArticlesList");
		return articlesList;
	}
	
	//글 정보를 게시판 테이블에 추가한 후 글 번호 반환
	@Override
	public int insertNewArticle(Map articleMap) throws DataAccessException{
		int articleNO = selectNewArticleNO(); //새 글에 대한 번호를 가져옴
		articleMap.put("articleNO", articleNO); //글 번호를 articleMap에 저장
		sqlSession.insert("mapper.board.insertNewArticle", articleMap); //id에 대한 insert문을 호출하면서 articleMap을 전달
		return articleNO;
	}
	
	private int selectNewArticleNO() throws DataAccessException{
		return sqlSession.selectOne("mapper.board.selectNewArticleNO");
	}
	
	@Override
	public void insertNewImage(Map articleMap) throws DataAccessException{
		List<ImageVO> imageFileList = (ArrayList)articleMap.get("imageFileList"); //articleMap이 imageFileList을 가져와서 리스트로 저장
		int articleNO = (Integer)articleMap.get("articleNO"); //articleMap이 글 번호를 가져와 저장
		int imageFileNO = selectNewImageFileNO(); //이미지 파일을 가져옴
		//ImageVO 객체를 차례대로 가져와 이미지 번호와 글 번호 속성을 설정
		for(ImageVO imageVO : imageFileList) {
			imageVO.setImageFileNO(++imageFileNO);
			imageVO.setArticleNO(articleNO);
		}
		sqlSession.insert("mapper.board.insertNewImage",imageFileList);
	}
	

	private int selectNewImageFileNO() throws DataAccessException {
		return sqlSession.selectOne("mapper.board.selectNewImageFileNO");
	}
	
	@Override
	public List selectImageFileList(int articleNO) throws DataAccessException{
		List<ImageVO> imageFileList = null;
		imageFileList = sqlSession.selectList("mapper.board.selectImageFileList", articleNO);
		return imageFileList;
	}
	
	@Override
	public ArticleVO selectArticle(int articleNO) throws DataAccessException{
		return sqlSession.selectOne("mapper.board.selectArticle", articleNO);
	}
	
	@Override
	public void updateArticle(Map articleMap) throws DataAccessException{
		sqlSession.update("mapper.board.updateArticle",articleMap);
	}
	
	@Override
	public void deleteArticle(int articleNO) throws DataAccessException{
		sqlSession.delete("mapper.board.deleteArticle", articleNO);
	}
}
