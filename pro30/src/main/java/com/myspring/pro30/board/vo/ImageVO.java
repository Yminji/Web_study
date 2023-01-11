package com.myspring.pro30.board.vo;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Date;

public class ImageVO {
	private int imageFileNO;
	private String imageFileName;
	private Date regDate;
	private int articleNO;
	
	public int getImageFileNO() {return imageFileNO;}
	public String getImageFileName() {return imageFileName;}
	public Date getRegDate() {return regDate;}
	public int getArticleNO() {return articleNO;}
	
	public void setImageFileNO(int imgeFileNO) {this.imageFileNO = imageFileNO;}
	public void setImageFileName(String imageFileName) {
		try {
			if(imageFileName!=null && imageFileName.length()!=0) {
				this.imageFileName = URLEncoder.encode(imageFileName, "UTF-8");
			}
		}catch(UnsupportedEncodingException e) {
				e.printStackTrace();
		}
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public void setArticleNO(int articleNO) {
		this.articleNO = articleNO;
	}
}
