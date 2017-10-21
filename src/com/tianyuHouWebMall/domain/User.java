package com.tianyuHouWebMall.domain;

import java.util.Date;

public class User {
	
	private String uid;
	private String username;
	private String name;
	private String password;
	private String email;
	private String telephone;
	private String sex;
	private Date birthday;
	private int state;
	private String code;
	
	
	public User() {
	}


	public String getUid() {
		return uid;
	}


	public void setUid(String uid) {
		this.uid = uid;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getTelephone() {
		return telephone;
	}


	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}


	public String getSex() {
		return sex;
	}


	public void setSex(String sex) {
		this.sex = sex;
	}


	public Date getBirthday() {
		return birthday;
	}


	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}


	public int getState() {
		return state;
	}


	public void setState(int state) {
		this.state = state;
	}


	public String getCode() {
		return code;
	}


	public User(String uid, String username, String name, String password, String email, String telephone, String sex,
			Date birthday, int state, String code) {
		super();
		this.uid = uid;
		this.username = username;
		this.name = name;
		this.password = password;
		this.email = email;
		this.telephone = telephone;
		this.sex = sex;
		this.birthday = birthday;
		this.state = state;
		this.code = code;
	}


	public void setCode(String code) {
		this.code = code;
	}
	
	

	
	
}
