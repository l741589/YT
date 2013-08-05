package com.yt.bean;

import java.sql.Timestamp;

public class WorkBean {
	private Integer wid;
	private String name;
	private String uid;	
	private Integer favorCount;
	private Integer playCount;
	private Integer timeLength;
	/**这东西其实就是扩展名,表示格式用的，图片和视屏也靠这个区分，不区分大小写*/
	private String datatype;
	private String dataurl;
	private String coverurl;
	private Timestamp time;
	public Integer getWid() {
		return wid;
	}
	public void setWid(Integer wid) {
		this.wid = wid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public Integer getFavorCount() {
		return favorCount;
	}
	public void setFavorCount(Integer favorCount) {
		this.favorCount = favorCount;
	}
	public Integer getPlayCount() {
		return playCount;
	}
	public void setPlayCount(Integer playCount) {
		this.playCount = playCount;
	}
	public Integer getTimeLength() {
		return timeLength;
	}
	public void setTimeLength(Integer timeLength) {
		this.timeLength = timeLength;
	}
	public String getDatatype() {
		return datatype;
	}
	public void setDatatype(String datatype) {
		this.datatype = datatype;
	}
	public String getDataurl() {
		return dataurl;
	}
	public void setDataurl(String dataurl) {
		this.dataurl = dataurl;
	}
	public String getCoverurl() {
		return coverurl;
	}
	public void setCoverurl(String coverurl) {
		this.coverurl = coverurl;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
}
	
