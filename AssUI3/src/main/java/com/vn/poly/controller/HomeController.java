package com.vn.poly.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.vn.poly.dao.VideoDao;
import com.vn.poly.dao.VideoDaoImpl;
import com.vn.poly.model.History;
import com.vn.poly.model.Users;
import com.vn.poly.model.Video;
import com.vn.poly.service.HistoryService;
import com.vn.poly.service.VideoService;
import com.vn.poly.service.impl.HistoryServiceImpl;
import com.vn.poly.service.impl.VideoServiceImpl;

@WebServlet(urlPatterns = { "/index", "/favorite", "/history" })
public class HomeController extends HttpServlet {

	private static final long serialVersionUID = -1178500025517877924L;
	private VideoDao videoDao = new VideoDaoImpl();
	private VideoService videoService = new VideoServiceImpl();
	private HistoryService historyService = new HistoryServiceImpl();
	private static int VIDEO_MAX_PAGE_SIZE = 6;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession sec = req.getSession();

		String uri = req.getRequestURI();
		if (uri.contains("index")) {
			this.doGetIndex(req, resp);
		} else if (uri.contains("favorite")) {
			this.doGetFavorite(sec, req, resp);

		} else if (uri.contains("history")) {
			this.doGetHistory(sec, req, resp);
		}

	}

	private void doGetHistory(HttpSession sec, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		Users users = (Users) sec.getAttribute("curentUser");
		List<History> listHistories = historyService.findByUser(users.getUsername());
		List<Video> liVideos = new ArrayList<>();
//		duyệt các phần tử trong history
		listHistories.forEach(item -> liVideos.add(item.getVideo()));
//		for i
		req.setAttribute("videos2", liVideos);
		req.getRequestDispatcher("/view/user/history.jsp").forward(req, resp);

	}

	private void doGetFavorite(HttpSession sec, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		Users users = (Users) sec.getAttribute("curentUser");
		List<History> listHistories = historyService.findByUserAndIsliked(users.getUsername());
		List<Video> liVideos = new ArrayList<>();
//		duyệt các phần tử trong history
		listHistories.forEach(item -> liVideos.add(item.getVideo()));
//		for i
		req.setAttribute("videos", liVideos);
		req.getRequestDispatcher("/view/user/favorite.jsp").forward(req, resp);

	}

//localohost:8080/ass/index?page={page}
	private void doGetIndex(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Video> countVideo = videoService.findAll();
		int maxPage = (int) Math.ceil(countVideo.size() / (double) VIDEO_MAX_PAGE_SIZE);
		req.setAttribute("maxPage", maxPage);
		List<Video> list;
		String pageNumber = req.getParameter("page");
		if (pageNumber == null || Integer.valueOf(pageNumber)>maxPage) {
			list = videoService.findAllVideo(1, VIDEO_MAX_PAGE_SIZE);
			req.setAttribute("curentPage",1);

		} else {
			list = videoService.findAllVideo(Integer.valueOf(pageNumber), VIDEO_MAX_PAGE_SIZE);
			req.setAttribute("curentPage", Integer.valueOf(pageNumber));
		}
		req.setAttribute("list", list);
		req.getRequestDispatcher("/view/user/index.jsp").forward(req, resp);

	}
}
