package com.vn.poly.service.impl;

import java.sql.Timestamp;
import java.util.List;

import com.vn.poly.dao.HistoryDao;
import com.vn.poly.dao.HistoryDaoImpl;
import com.vn.poly.dao.UserDao;
import com.vn.poly.dao.UserDaoImpl;
import com.vn.poly.model.History;
import com.vn.poly.model.Users;
import com.vn.poly.model.Video;
import com.vn.poly.service.HistoryService;
import com.vn.poly.service.VideoService;

public class HistoryServiceImpl implements HistoryService {
	private HistoryDao historyDao;
	private VideoService videoService;
	private UserDao userDao;

	public HistoryServiceImpl() {
		historyDao = new HistoryDaoImpl();
		videoService = new VideoServiceImpl();
		userDao=new UserDaoImpl();
	}

	@Override
	public List<History> findByUser(String username) {
		// TODO Auto-generated method stub
		return historyDao.findByUser(username);
	}

	@Override
	public List<History> findByUserAndIsliked(String username) {
		// TODO Auto-generated method stub
		return historyDao.findByUserandIsLiked(username);
	}

	@Override
	public History findByUserIdAnvideoId(Integer userId, Integer videoId) {
		// TODO Auto-generated method stub
		return historyDao.findByUserIdAndVideoId(userId, videoId);
	}

	@Override
	public History create(History entity) {

		return historyDao.createHis(entity);
	}

	@Override
	public History update(History entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public History create(Users user, Video video) {
		History history =findByUserIdAnvideoId(user.getId(), video.getId());
		if (history == null) {
			history = new History();
			history.setUsers(user);
			history.setVideo(video);
			history.setIsLike(Boolean.FALSE);
			history.setViewDate(new Timestamp(System.currentTimeMillis()));
			return historyDao.createHis(history);
		}
		return history;
		
	}

	@Override
	public boolean updateLikeOrUnlike(Users user, String videoHref) {
		Video video = videoService.findByHref(videoHref);
		History historye = findByUserIdAnvideoId(user.getId(), video.getId());
		
		if (historye.getIsLike() == Boolean.FALSE) {
			historye.setIsLike(Boolean.TRUE);
			historye.setLikeDate(new Timestamp(System.currentTimeMillis()));
		} else {
			historye.setIsLike(Boolean.FALSE);
			historye.setLikeDate(null);
		}
		
		History updatehiss = historyDao.updateHis(historye);

		return updatehiss != null ? true : false;
	}

}
