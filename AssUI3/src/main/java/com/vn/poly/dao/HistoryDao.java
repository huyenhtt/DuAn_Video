package com.vn.poly.dao;

import java.util.List;

import com.vn.poly.model.History;
import com.vn.poly.model.Users;
import com.vn.poly.model.Video;

public interface HistoryDao {
//	lấy lịch sử xem video của 1 user nào đó khi có username
	List<History> findByUser(String username);
//lấy ra favorite của user bằng username
	List<History> findByUserandIsLiked(String username);

	History findByUserIdAndVideoId(Integer userID, Integer videoID);

	History createHis(History entity);

	History updateHis(History enHistory);
}