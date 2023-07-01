package com.vn.poly.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Video")
public class Video {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Integer id;

	@Column(name = "title")
	private String title;

	@Column(name = "href")
	private String href;

	@Column(name = "poster")
	private String poster;

	@Column(name = "views")
	private Integer views;

	@Column(name = "shares")
	private Integer shares;

	@Column(name = "descriptions")
	private String descriptions;

	@Column(name = "isActive")
	private Boolean isActive;

	public String getDescriptions() {
		return descriptions;
	}

	public void setDescriptions(String descriptions) {
		this.descriptions = descriptions;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public Integer getViews() {
		return views;
	}

	public void setViews(Integer views) {
		this.views = views;
	}

	public Integer getShares() {
		return shares;
	}

	public void setShares(Integer shares) {
		this.shares = shares;
	}

	

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "Video [id=" + id + ", title=" + title + ", href=" + href + ", poster=" + poster + ", views=" + views
				+ ", shares=" + shares + ", descriptions=" + descriptions + ", isActive=" + isActive + "]";
	}

}
