package com.spring.ex01;

import java.sql.Date;

public class MemberVO {
	private String id;
	private String pwd;
	private String name;
	private String email;
	private Date joinDate;
	
	public MemberVO() {}
	
	public MemberVO(String id, String pwd, String name, String email) {
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.email = email;
	}
	
	public String getId() {return id;}
	public String getPwd() {return pwd;}
	public String getName() {return name;}
	public String getEmail() {return email;}
	public Date getJoinDate() {return joinDate;}
	
	public void setId(String id) {this.id = id;}
	public void setPwd(String pwd) {this.pwd = pwd;}
	public void setName(String name) {this.name=name;}
	public void setEmail(String email) {this.email = email;}
	public void setJoinDate(Date joinDate) {this.joinDate = joinDate;}
}
