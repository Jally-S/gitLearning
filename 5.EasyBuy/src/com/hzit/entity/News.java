package com.hzit.entity;

import freemarker.cache.StrongCacheStorage;

/**
 * 新闻的实体类
 * @author ZhaoQu
 *
 */
public class News {
	private int id;
	private String title;
	private String content;
	private String createTime;
	public News() {
		super();
	}
	public News(int id, String title, String content, String createTime) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.createTime = createTime;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCintent() {
		return content;
	}
	public void setCintent(String content) {
		this.content = content;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	@Override
	public String toString() {
		return "News [id=" + id + ", title=" + title + ", content=" + content
				+ ", createTime=" + createTime + "]";
	}
}
