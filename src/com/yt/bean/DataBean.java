package com.yt.bean;

public class DataBean {
	private int length;
	/**该域为识别码，同一时间id和url一一对应，不能有两个上传/下载操作有相同识别码，
	 * 至于是否废弃过期识别码由服务端根据数据量决定，
	 * 只要保证在该识别码有效的时间内能找到唯一一个对应的url就行*/
	private int id;
	private String url;
	private String type;
	private byte[] data;
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public byte[] getData() {
		return data;
	}
	public void setData(byte[] data) {
		this.data = data;
	}
}
