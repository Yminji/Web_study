package com.bookshop01.common.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.coobird.thumbnailator.Thumbnails;

@Controller
public class FileDownloadController {
	private static String CURR_IMAGE_REPO_PATH = "C:\\shopping\\file_repo";
	
	@RequestMapping("/download")
	//이미지 파일 이름과 상품 id를 가져옴
	protected void download(@RequestParam("fileName") String fileName, 
			@RequestParam("goods_id") String goods_id, HttpServletResponse response) throws Exception{
		OutputStream out = response.getOutputStream(); //response에서 outputStream 객체를 가져옴
		String filePath = CURR_IMAGE_REPO_PATH+"\\"+goods_id+"\\"+fileName;
		File image = new File(filePath);
		
		//파일을 다운로드할 수 있음
		response.setHeader("Cache-Control", "no_cache");
		response.addHeader("Content-disposition", "attachment; fileName="+fileName);
		FileInputStream in = new FileInputStream(image);
		byte[] buffer = new byte[1024 * 8]; //버퍼 8KB
		while(true) { 
			int count = in.read(buffer); //버퍼에 읽어들인 문자개수
			if(count == -1) //버퍼의 마지막에 도달했는지 체크
				break;
			out.write(buffer, 0, count);
		}//버퍼 기능을 이용해 파일에서 버퍼로 데이터를 읽어와 한꺼번에 출력
		in.close();
		out.close();
	}
	
	@RequestMapping("/thumbnails.do")
	protected void thumbnails(@RequestParam("fileName") String fileName,
			@RequestParam("goods_id") String goods_id, HttpServletResponse response) throws Exception{
		OutputStream out = response.getOutputStream(); //response에서 OuputStream 객체를 가져옴
		String filePath = CURR_IMAGE_REPO_PATH+"\\"+goods_id+"\\"+fileName;
		File image = new File(filePath);
		
		if(image.exists()) { //이미지가 존재한다면
			Thumbnails.of(image).size(121, 154).outputFormat("png").toOutputStream(out);
		}//메인 페이지 이미지를 썸네일로 표시함
		byte[] buffer = new byte[1024 * 8];
		out.write(buffer); //8KB 버퍼 출력
		out.close();
	}
}
