package com.vn.poly.service;

import java.util.List;

import com.vn.poly.model.Video;

public interface VideoService {

	List<Video> findAll();

	Video create(Video video);

	Video update(Video video);

	Video delete(String href);

	Video deleteVideo(String href);

	Video findByID(Integer id);

	Video findByHref(String href);

	List<Video> findAllVideo(int pagenumber, int pagesize);

}