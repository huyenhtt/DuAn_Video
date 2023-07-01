package com.vn.poly.dto;

public class VideoLikedInfor {
	private Integer id;
	private String title;
	private String href;
	private Integer totalLike;

	public VideoLikedInfor() {
		super();
		// TODO Auto-generated constructor stub
	}

	public VideoLikedInfor(Integer id, String title, String href, Integer totalLike) {
		super();
		this.id = id;
		this.title = title;
		this.href = href;
		this.totalLike = totalLike;
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

	public Integer getTotalLike() {
		return totalLike;
	}

	public void setTotalLike(Integer totalLike) {
		this.totalLike = totalLike;
	}

	@Override
	public String toString() {
		return "VideoLikedInfor [id=" + id + ", title=" + title + ", href=" + href + ", totalLike=" + totalLike + "]";
	}

}
