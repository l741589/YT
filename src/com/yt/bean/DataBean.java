package com.yt.bean;

public class DataBean {
	private int length;
	/**����Ϊʶ���룬ͬһʱ��id��urlһһ��Ӧ�������������ϴ�/���ز�������ͬʶ���룬
	 * �����Ƿ��������ʶ�����ɷ���˸���������������
	 * ֻҪ��֤�ڸ�ʶ������Ч��ʱ�������ҵ�Ψһһ����Ӧ��url����*/
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
