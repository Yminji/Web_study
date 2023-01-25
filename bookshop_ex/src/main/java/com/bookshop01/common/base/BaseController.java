package com.bookshop01.common.base;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.bookshop01.goods.vo.ImageFileVO;

public class BaseController {
	private static final String CURR_IMAGE_REPO_PATH ="C:\\shopping\\file_repo";
	
	protected List<ImageFileVO> upload(MultipartHttpServletRequest multipartRequest) throws Exception{
		//파일 정보를 저장할 fileList 선언
		List<ImageFileVO> fileList = new ArrayList<ImageFileVO>();
		Iterator<String> fileNames = multipartRequest.getFileNames();
		//상품 등록창에서 전송된 파일들의 정보를 fileList에 저장
		while(fileNames.hasNext()) {
			ImageFileVO imageFileVO = new ImageFileVO();
			String fileName = fileNames.next(); 
			imageFileVO.setFileType(fileName); //이미지파일 정류
			MultipartFile mFile = multipartRequest.getFile(fileName);
			String originalFileName = mFile.getOriginalFileName();
			imageFileVO.setFileName(originalFileName); //이미지 파일명
			fileList.add(imageFileVO); //fileList에 저장
			
			File file = new File(CURR_IMAGE_REPO_PATH+"\\"+fileName);
			if(mFile.getSize() != 0) { //파일 널 확인
				if(! file.exists()) { //경로상에 파일이 존재하지 않을 경우
					if(file.getParentFile().mkdirs()) { //경로에 해당하는 디렉토리들을 생성하면
						file.createNewFile(); //이후 파일 생성
					}
				}
				//임시로 저장된 multipartFile을 실제 파일로 전송
				mFile.transferTo(new File(CURR_IMAGE_REPO_PATH+"\\"+"temp"+"\\"+originalFileName));
			}
		}
		return fileList;
	}
	
	private void deleteFile(String fileName) {
		File file = new File(CURR_IMAGE_REPO_PATH+"\\"+fileName);
		try {
			file.delete();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/*.do", method= {RequestMethod.POST, RequestMethod.GET})
	protected ModelAndView viewForm(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);
		return mav;
	}

}
