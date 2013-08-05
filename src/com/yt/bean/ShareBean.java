package com.yt.bean;

import com.yt.bean.enums.ShareType;

public class ShareBean {
	private ShareType type;
	private String text;
	public ShareType getType() {
		return type;
	}
	public void setType(ShareType type) {
		this.type = type;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
}
