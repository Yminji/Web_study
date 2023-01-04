package com.myspring.pro29.ex03;

public class ArticleVO {
	int articleNO;
	String writer;
	String title;
	String content;
	
	public void ArticleVO() {}
	
	public void ArticleVO(int articleNO, String writer, String title, String content) {
		this.articleNO = articleNO;
		this.writer = writer;
		this.title = title;
		this.content = content;
	}
	
	public void setArticleNO(int articleNO) {this.articleNO = articleNO;}
	public void setWriter(String writer) {this.writer = writer;}
	public void setTitle(String title) {this.title = title;}
	public void setContent(String content) {this.content = content;}
	
	public int getAtricleNO() {return articleNO;}
	public String getWriter() {return writer;}
	public String getTitle() {return title;}
	public String getContent() {return content;}
	
}
