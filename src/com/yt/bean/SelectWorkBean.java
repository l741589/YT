package com.yt.bean;

/**����UserBean������ɸѡ��null��ʾͨ�䣬��Ϊ�Ժ���ܻᰴ����������ţ������Ҷ��Ž�ȥ�ˡ�
 * orderby���ʾ����ؼ���*/
public class SelectWorkBean{
	private UserBean user;
	private WorkBean work;
	
	/**ֵ�淶������SQL�����ʽ����������Bean.Fieldģʽ
	 *�磺
	 *orderby = "work.time DESC"
	 *orderby = "user.name ASC, user.uid DESC"
	 *���������滻���ַ�������ֱ���ý�SQL��ORDER BY �Ӿ����ʽ
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
