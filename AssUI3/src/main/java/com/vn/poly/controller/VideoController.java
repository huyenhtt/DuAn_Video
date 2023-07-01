package com.vn.poly.controller;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.vn.poly.constant.SessionAttr;
import com.vn.poly.dao.HistoryDao;
import com.vn.poly.dao.HistoryDaoImpl;
import com.vn.poly.dao.UserDao;
import com.vn.poly.dao.UserDaoImpl;
import com.vn.poly.dao.VideoDao;
import com.vn.poly.dao.VideoDaoImpl;
import com.vn.poly.model.History;
import com.vn.poly.model.Users;
import com.vn.poly.model.Video;
import com.vn.poly.service.HistoryService;
import com.vn.poly.service.VideoService;
import com.vn.poly.service.impl.HistoryServiceImpl;
import com.vn.poly.service.impl.VideoServiceImpl;

@WebServlet(urlPatterns = "/video")
public class VideoController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6757232714363054624L;
	private VideoDao videoDao = new VideoDaoImpl();
	private HistoryDao historyDao = new HistoryDaoImpl();
	private HistoryService historyService = new HistoryServiceImpl();
	private VideoService videoService=new VideoServiceImpl(); 
	private UserDao userDao = new UserDaoImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String actionParam = req.getParameter("action");
		String href = req.getParameter("id");
		HttpSession session = req.getSession();
		switch (actionParam) {
		case "watch": {

			this.doGetWatch(session, href, req, resp);
			break;
		}
		case "like": {

			this.doGetLike(session, href, req, resp);
			break;
		}
		}
	}

	private void doGetLike(HttpSession session, String href, HttpServletRequest req, HttpServletResponse resp) {
//		resp.setContentType("application/json");
		Users currentU= (Users) session.getAttribute(SessionAttr.CURRENT_USER);

		boolean ressult = historyService.updateLikeOrUnlike(currentU, href);

		if (ressult==true) {
			resp.setStatus(204);
		} else {
			resp.setStatus(400);
		}

	}

	private void doGetWatch(HttpSession session, String href, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
//		resp.setContentType("application/json");
		Video video = videoService.findByHref(href);
		req.setAttribute("video", video);
		Users users= (Users) session.getAttribute(SessionAttr.CURRENT_USER);
		if (users!=null) {
			History history=historyService.create(users, video);
			req.setAttribute("flagLikeBtn", history.getIsLike());
		}
		req.getRequestDispatcher("/view/user/video-detail.jsp").forward(req, resp);
	}
}
