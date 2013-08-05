package com.yt.bean;

/**根据UserBean的域来筛选，null表示通配，因为以后可能会按更多的条件排，所以我都放进去了。
 * orderby域表示排序关键字*/
public class SelectWorkBean{
	private UserBean user;
	private WorkBean work;
	
	/**值规范，采用SQL排序格式，域名采用Bean.Field模式
	 *如：
	 *orderby = "work.time DESC"
	 *orderby = "user.name ASC, user.uid DESC"
	 *反正就是替换了字符串就能直接拿进SQL的ORDER BY 子句的形式
	 **/
	private String orderby;
	private Integer from;
	private Integer count;
	public UserBean getUser() {
		return user;
	}
	public void setUser(UserBean user) {
		this.user = user;
	}
	public WorkBean getWork() {
		return work;
	}
	public void setWork(WorkBean work) {
		this.work = work;
	}
	public String getOrderby() {
		return orderby;
	}
	public void setOrderby(String orderby) {
		this.orderby = orderby;
	}
	public Integer getFrom() {
		return from;
	}
	public void setFrom(Integer from) {
		this.from = from;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
}
