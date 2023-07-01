package com.vn.poly.dao;

import java.util.List;

import com.vn.poly.model.Video;

public interface VideoDao {
	List<Video> findAll();

	Video create(Video video);

	Video update(Video video);

	Video delete(String id);

	Video findByID(Integer id);

	Video findByHref( String href);

	List<Video> findAllVideo(int pagenumber, int pagesize);

}