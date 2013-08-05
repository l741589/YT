package com.yt.bean;

import com.yt.bean.enums.ResultCode;

public class ResultBean<T> {
	private ResultCode resultCode;
	private T data;
	public ResultCode getResultCode() {
		return resultCode;
	}
	public void setResultCode(ResultCode resultCode) {
		this.resultCode = resultCode;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
}
