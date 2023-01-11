package com.myspring.pro30.board.vo;

import java.sql.Date;

import org.springframework.stereotype.Component;

@Component("articleVO")
public class ArticleVO {
	private int level;
	private int articleNO;
	private int parentNO;
	private String title;
	private String content;
	private String imageFileName;
	private String id;
	private Date writeDate;
	
	public void setLevel(int level) {this.level = level;}
	public void setArticleNO(int articleNO) {this.articleNO = articleNO;}
	public void setParentNO(int parentNO) {this.parentNO = parentNO;}
	public void setTitle(String title) {this.title = title;}
	public void setContent(String content) {this.content = content;}
	public void setImageFileName(String imageFileName) {this.imageFileName = imageFileName;}
	public void setId(String id) {this.id = id;}
	public void setWriteDate(Date writeDate) {this.writeDate = writeDate;}
	
	public int getLevel() {return level;}
	public int getArticleNO() {return articleNO;}
	public int getParentNO() {return parentNO;}
	public String getTitle() {return title;}
	public String getContent() {return content;}
	public String getImageFileName() {return imageFileName;}
	public String getId() {return id;}
	public Date getWriteDate() {return writeDate;}
}
