package com.bookshop01.goods.vo;

import java.sql.Date;

public class GoodsVO {
	private int goods_id; //상품 번호
	private String goods_title; //상품 제목
	private String goods_writer; //저자 이름
	private int goods_price; //상품 정가
	private String goods_publisher; //츨핀사이름
	private String goods_sort; //상품 종류
	private int goods_sales_price; //상품 판매 가격
	private int goods_point; //상품 포인트
	private Date goods_published_date; //상품 출판일
	private int goods_total_page; //상품총페이지수
	private String goods_isbn; //isbn번호
	private String goods_delivery_price; //배송비
	private Date goods_delivery_date; //상품배송일
	private String goods_fileName; //상품 파일이름
	private String goods_status; //상품 분류
	private String goods_writer_intro; //저자소개
	private String goods_contents_order; //상품 목차
	private String goods_intro; //상품소개
	private String goods_publisher_comment; //출판사명
	private String goods_recommendation; //상품추천사
	private Date goods_credate; //상품입고일
	
	public GoodsVO() {}
	
	public void setGoods_id(int goods_id) {this.goods_id = goods_id;}
	public int getGoods_id() {return goods_id;}
	
	public void setGoods_title(String goods_title) {this.goods_title = goods_title;}
	public String getGoods_title() {return goods_title;}
	
	public void setGoods_writer(String goods_writer) {this.goods_writer = goods_writer;}
	public String getGoods_writer() {return goods_writer;}
	
	public void setGoods_price(int goods_price) {this.goods_price = goods_price;}
	public int getGoods_price() {return goods_price;}
	
	public void setGoods_publisher(String goods_publisher) {this.goods_publisher = goods_publisher;}
	public String getGoods_publisher() {return goods_publisher;}
	
	public void setGoods_sort(String goods_sort) {this.goods_sort = goods_sort;}
	public String getGoods_sort() {return goods_sort;}
	
	public void setGoods_sales_price(int goods_sales_price) {this.goods_sales_price = goods_sales_price;}
	public int getGoods_sales_price() {return goods_sales_price;}
	
	public void setGoods_point(int goods_point) {this.goods_point = goods_point;}
	public int getGoods_point() {return goods_point;}
	
	public void setGoods_published_date(Date goods_published_date) {this.goods_published_date = goods_published_date;}
	public Date getGoods_published_date() {return goods_published_date;}
	
	public void setGoods_total_page(int goods_total_page) {this.goods_total_page = goods_total_page;}
	public int getGoods_total_page() {return goods_total_page;}
	
	public void setGoods_isbn(String goods_isbn) {this.goods_isbn = goods_isbn;}
	public String getGoods_isbn() {return goods_isbn;}
	
	public void setGoods_delivery_price(String goods_delivery_price) {this.goods_delivery_price = goods_delivery_price;}
	public String getGoods_delivery_price() {return goods_delivery_price;}
	
	public void setGoods_delivery_date(Date goods_delivery_date) {this.goods_delivery_date = goods_delivery_date;}
	public Date getGoods_delivery_date() {return goods_delivery_date;}
	
	public void setGoods_fileName(String goods_fileName) {this.goods_fileName = goods_fileName;}
	public String getGoods_fileName() {return goods_fileName;}
	
	public void setGoods_status(String goods_status) {this.goods_status = goods_status;}
	public String getGoods_status() {return goods_status;}
	
	public void setGoods_writer_intro(String goods_writer_intro) {this.goods_writer_intro = goods_writer_intro;}
	public String getGoods_writer_intro() {return goods_writer_intro;}
	
	public void setGoods_contents_order(String goods_contents_order) {this.goods_contents_order = goods_contents_order;}
	public String getGoods_contents_order() {return goods_contents_order;}
	
	public void setGoods_intro(String goods_intro) {this.goods_intro = goods_intro;}
	public String getGoods_intro() {return goods_intro;}
	
	public void setGoods_publisher_comment(String goods_publisher_comment) {this.goods_publisher_comment = goods_publisher_comment;}
	public String getGoods_publisher_comment() {return goods_publisher_comment;}
	
	public void setGoods_recommendation(String goods_recommendation) {this.goods_recommendation = goods_recommendation;}
	public String getGoods_recommendation() {return goods_recommendation;}
	
	public void setGoods_credate(Date goods_credate) {this.goods_credate = goods_credate;}
	public Date getGoods_credate() {return goods_credate;}
}
