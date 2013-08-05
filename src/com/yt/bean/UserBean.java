package com.yt.bean;

import java.util.Set;

import com.yt.bean.enums.Province;
import com.yt.bean.enums.Sex;
import com.yt.bean.enums.UserType;

public class UserBean {
	private String uid;
	private String name;
	private Sex sex;
	private String phonenumber;
	private String email;
	private Set<Province> division;
	private Province location;
	private String photourl;
	private UserType type;
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Sex getSex() {
		return sex;
	}
	public void setSex(Sex sex) {
		this.sex = sex;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Set<Province> getDivision() {
		return division;
	}
	public void setDivision(Set<Province> division) {
		this.division = division;
	}
	public Province getLocation() {
		return location;
	}
	public void setLocation(Province location) {
		this.location = location;
	}
	public String getPhotourl() {
		return photourl;
	}
	public void setPhotourl(String photourl) {
		this.photourl = photourl;
	}
	public UserType getType() {
		return type;
	}
	public void setType(UserType type) {
		this.type = type;
	}
}
