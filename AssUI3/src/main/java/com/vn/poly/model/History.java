package com.vn.poly.model;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "History")
public class History{
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "userID", referencedColumnName = "id")
	@JsonIgnoreProperties(value = { "applications", "hibernatelazyInitializer" })
	private Users users;
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "videoID", referencedColumnName = "id")
	@JsonIgnoreProperties(value = { "applications", "hibernatelazyInitializer" })
	private Video video;
	@Column(name = "viewDate")
	@CreationTimestamp
	private Timestamp viewDate;
	@Column(name = "isLike")
	private Boolean isLike;
	@CreationTimestamp
	@Column(name = "likeDate")
	private Timestamp likeDate;

	public History(Integer id, Users users, Video video, Timestamp viewDate, Boolean isLike, Timestamp likeDate) {
		super();
		this.id = id;
		this.users = users;
		this.video = video;
		this.viewDate = viewDate;
		this.isLike = isLike;
		this.likeDate = likeDate;
	}

	public History() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public Video getVideo() {
		return video;
	}

	public void setVideo(Video video) {
		this.video = video;
	}

	public Timestamp getViewDate() {
		return viewDate;
	}

	public void setViewDate(Timestamp viewDate) {
		this.viewDate = viewDate;
	}

	public Boolean getIsLike() {
		return isLike;
	}

	public void setIsLike(Boolean isLike) {
		this.isLike = isLike;
	}

	public Timestamp getLikeDate() {
		return likeDate;
	}

	public void setLikeDate(Timestamp likeDate) {
		this.likeDate = likeDate;
	}

	@Override
	public String toString() {
		return "History [id=" + id + ", users=" + users + ", video=" + video + ", viewDate=" + viewDate + ", isLike="
				+ isLike + ", likeDate=" + likeDate + "]";
	}

}
