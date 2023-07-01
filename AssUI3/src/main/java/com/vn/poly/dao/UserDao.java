package com.vn.poly.dao;

import java.util.List;
import java.util.Map;

import com.vn.poly.model.Users;
import com.vn.poly.model.Video;

public interface UserDao {
	List<Users> findAll();

	List<Users> findAllActive();

	Users checkLogin(String username, String password);

	Users create(Users users);

	Users update(Users users);

	Users delete(String id);

	List<Users> findAllVideo(int pagenumber, int pagesize);

	List<Users> findByRole(List<Users> listU, boolean admin);

	List<Users> findByKeyword(List<Users> list, String keyword);

	Users findOne(String username, String password);

	Users findByID(Integer integer);

	Users findEmail(String email);

	Users findUsername(String username);

	List<Users> findUserLikedByVideoHref(Map<String, Object> params);
}