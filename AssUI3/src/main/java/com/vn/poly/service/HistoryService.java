package com.vn.poly.service;

import java.util.List;

import com.vn.poly.model.History;
import com.vn.poly.model.Users;
import com.vn.poly.model.Video;

public interface HistoryService {
	List<History> findByUser(String username);

	List<History> findByUserAndIsliked(String username);

	History findByUserIdAnvideoId(Integer userId, Integer videoId);

	History create(History entity);

	History create(Users user, Video video);

	History update(History entity);

	boolean updateLikeOrUnlike(Users user, String videoHref);
}