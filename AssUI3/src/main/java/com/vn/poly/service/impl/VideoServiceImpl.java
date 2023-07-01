package com.vn.poly.service.impl;

import java.util.List;

import com.vn.poly.dao.VideoDao;
import com.vn.poly.dao.VideoDaoImpl;
import com.vn.poly.model.Video;
import com.vn.poly.service.VideoService;

public class VideoServiceImpl implements VideoService {
private VideoDao videoDao;

	public VideoServiceImpl() {
		
		videoDao=new VideoDaoImpl();
	}

	@Override
	public List<Video> findAll() {
		List<Video> listVideos=videoDao.findAll();
		return listVideos;

	}

	@Override
	public Video create(Video video) {
		// TODO Auto-generated method stub
		video.setIsActive(Boolean.TRUE);
		video.setViews(0);
		video.setShares(0);
		return videoDao.create(video);
	}

	@Override
	public Video update(Video video) {
		// TODO Auto-generated method stub
		return videoDao.update(video);
	}

	@Override
	public Video delete(String href) {
		// TODO Auto-generated method stub
		Video vi=findByHref(href);
		vi.setIsActive(Boolean.FALSE);
		return videoDao.update(vi);
	}

	@Override
	public Video findByID(Integer id) {
		// TODO Auto-generated method stub
		return videoDao.findByID(id);
	}

	@Override
	public Video findByHref(String href) {
		
		return videoDao.findByHref(href);
	}

	@Override
	public List<Video> findAllVideo(int pagenumber, int pagesize) {
		// TODO Auto-generated method stub
		return videoDao.findAllVideo(pagenumber, pagesize);
	}

	@Override
	public Video deleteVideo(String href) {
		Video vi=findByHref(href);
		vi.setIsActive(Boolean.FALSE);
		
		return videoDao.update(vi);
	}

}
