package com.vn.poly.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.vn.poly.dao.UserDao;
import com.vn.poly.dao.UserDaoImpl;
import com.vn.poly.model.Users;
import com.vn.poly.service.UserService;

public class UserServiceImpl implements UserService {
	private UserDao userDao;

	public UserServiceImpl() {
		userDao = new UserDaoImpl();
	}

	@Override
	public List<Users> findAll() {
		// TODO Auto-generated method stub
		return userDao.findAll();
	}

	@Override
	public Users checkLogin(String username, String password) {
		// TODO Auto-generated method stub
		return userDao.checkLogin(username, password);
	}

	@Override
	public Users create(Users users) {
		// TODO Auto-generated method stub
		return userDao.create(users);
	}

	@Override
	public Users update(Users users) {
		// TODO Auto-generated method stub
		return userDao.update(users);
	}

	@Override
	public Users delete(String username) {
		// TODO Auto-generated method stub
		Users users=userDao.findUsername(username);
		users.setIsActive(Boolean.FALSE);
		return userDao.update(users);
	}

	@Override
	public List<Users> findByRole(List<Users> listU, boolean admin) {
		// TODO Auto-generated method stub
		return userDao.findByRole(listU, admin);
	}

	@Override
	public List<Users> findByKeyword(List<Users> list, String keyword) {
		// TODO Auto-generated method stub
		return userDao.findByKeyword(list, keyword);
	}

	@Override
	public Users findOne(String username, String password) {
		// TODO Auto-generated method stub
		return userDao.findOne(username, password);
	}

	@Override
	public Users findByID(Integer id) {
		// TODO Auto-generated method stub
		return userDao.findByID(id);
	}

	@Override
	public Users resetPassWord(String email) {
		Users exit = userDao.findEmail(email);
		if (exit != null) {
			String newPass = String.valueOf((int) (Math.random()) * ((999 - 100) + 1) + 1000);
			exit.setPassword(newPass);
			return userDao.update(exit);
		}
		return null;
	}

	@Override
	public List<Users> findAllVideo(int pagenumber, int pagesize) {

		return userDao.findAllVideo(pagenumber, pagesize);
	}

	@Override
	public List<Users> findUserLikedByVideoHref(String href) {
		Map<String, Object>param=new HashMap<>();
		param.put("videoHref", href);
		
		return userDao.findUserLikedByVideoHref(param);
	}

	@Override
	public List<Users> findAllActive() {
		// TODO Auto-generated method stub
		return userDao.findAllActive();
	}



}
