package com.vn.poly.service.impl;

import java.util.List;

import com.vn.poly.dao.StatsDaoImpl;
import com.vn.poly.dao.StatsDaos;
import com.vn.poly.dto.VideoLikedInfor;
import com.vn.poly.service.StasService;

public class StastSerrviceImpl implements StasService{
private StatsDaos statsDaos;
	public StastSerrviceImpl() {
		statsDaos=new StatsDaoImpl();
	}

	@Override
	public List<VideoLikedInfor> findVideoLikedInfo() {
		// TODO Auto-generated method stub
		return statsDaos.findVideoLikedInfo();
	}

}
