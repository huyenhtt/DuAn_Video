package com.vn.poly.dao;

import java.util.List;

import com.vn.poly.dto.VideoLikedInfor;
import com.vn.poly.model.Users;

public interface StatsDaos {
	List<VideoLikedInfor> findVideoLikedInfo();

	
}