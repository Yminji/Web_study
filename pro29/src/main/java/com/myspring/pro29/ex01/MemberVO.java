package com.myspring.pro29.ex01;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test/*")
public class MemberVO {
	String id;
	String pwd;
	String name;
	String email;
	
	public void setId(String id) {this.id = id;}
	public void setPwd(String pwd) {this.pwd = pwd;}
	public void setName(String name) {this.name = name;}
	public void setEmail(String email) {this.email = email;}
	
	public String getId() {return id;}
	public String getPwd() {return pwd;}
	public String getName() {return name;}
	public String getEmail() {return email;}
	
	@Override
	public String toString() {
		String info = id+", "+pwd+", "+name+", "+email;
		return info;
	}
}
