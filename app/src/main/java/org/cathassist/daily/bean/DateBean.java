package org.cathassist.daily.bean;

public class DateBean {
	private long id;
	private String date;
	
	public DateBean() {
	}
	
	public DateBean(long id, String date) {
		this.id = id;
		this.date = date;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
}
