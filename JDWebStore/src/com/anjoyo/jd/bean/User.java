package com.anjoyo.jd.bean;

public class User {
	private int user_id ;
	private String username;
	private String profile_image;
	private Double money;
	
	public User() {
		super();
	}
	public User(int user_id, String username,String profile_image, float money) {
		super();
		this.user_id = user_id;
		this.username=username;
		this.profile_image = profile_image;
		this.money = (double) money;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getProfile_image() {
		return profile_image;
	}
	public void setProfile_image(String profile_image) {
		this.profile_image = profile_image;
	}
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", username=" + username
				+ ", profile_image=" + profile_image + ", money=" + money + "]";
	}
	
}
