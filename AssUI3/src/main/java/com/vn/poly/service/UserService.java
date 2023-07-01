package com.vn.poly.service;

import java.util.List;
import java.util.Map;

import com.vn.poly.model.Users;

public interface UserService {
	List<Users> findAll();

	List<Users> findAllActive();

	Users checkLogin(String username, String password);

	Users create(Users users);

	Users update(Users users);

	Users resetPassWord(String email);

	Users delete(String username);

	List<Users> findByRole(List<Users> listU, boolean admin);

	List<Users> findByKeyword(List<Users> list, String keyword);

	List<Users> findAllVideo(int pagenumber, int pagesize);

	Users findOne(String username, String password);

	Users findByID(Integer id);

//	Users findByUsername(String username);

	List<Users> findUserLikedByVideoHref(String href);

}