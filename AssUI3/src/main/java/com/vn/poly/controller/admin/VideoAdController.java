package com.vn.poly.controller.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.vn.poly.constant.SessionAttr;
import com.vn.poly.model.Users;
import com.vn.poly.model.Video;
import com.vn.poly.service.VideoService;
import com.vn.poly.service.impl.VideoServiceImpl;

@WebServlet(name = "videoCtrl", value = { "/admin/video",
           "/admin/video-delete","/admin/video-update","/admin/video-create","/admin/video/detail"		

})
public class VideoAdController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private VideoService service = new VideoServiceImpl();
    private List<Video> list=new ArrayList<>();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		Users user = (Users) session.getAttribute(SessionAttr.CURRENT_USER);
		if (user != null && user.getIsAdmin() == Boolean.TRUE) {

			String action = req.getParameter("action");
			switch (action) {
			case "view": {

				doGetOverViewVideo(req, resp);
				break;
			}
			case "delete": {

				doGetDelete(req, resp);
				break;
			}
			case "add": {
              req.setAttribute("isEdit", false);
				doGetAdd(req, resp);
				break;
			}
			case "edit": {
				req.setAttribute("isEdit", true);
				doGetEdit(req, resp);
				break;
			}
			}
		} else {
			resp.sendRedirect("index");
		}
		// admin/video?action=delete&href=${href}
		// admin/video?action=edit&href=${href}
		// admin/video?action=create
		// admin/video?action=view

	}

	private void doGetEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String href=req.getParameter("href");
		Video vi=service.findByHref(href);
		req.setAttribute("video", vi);
		req.getRequestDispatcher("/view/admin/video-edit.jsp").forward(req, resp);

	}

	private void doGetAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/view/admin/video-edit.jsp").forward(req, resp);

	}

	

	private void doGetDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		resp.setContentType("application/json");
		String href = req.getParameter("href");
		Video videoD = service.delete(href);
		if (videoD != null) {
			resp.setStatus(204);
		} else {
			resp.setStatus(400);
		}
		List<Video> listVi = service.findAll();
		req.setAttribute("videos", listVi);
		req.getRequestDispatcher("/view/admin/video-overview.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		Users user = (Users) session.getAttribute(SessionAttr.CURRENT_USER);
		if (user != null && user.getIsAdmin() == Boolean.TRUE) {

			String action = req.getParameter("action");
			switch (action) {
			case "add": {
				doPostAdd(req, resp);
				break;
			}
			case "edit": {
				doPostEdit(req, resp);
				break;
			}

			}
		} else {
			resp.sendRedirect("video");
		}

	}
	private void doPostEdit(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		response.setContentType("application/json");

		String href = request.getParameter("href");
		String hrefO = request.getParameter("hrefO");
		String title = request.getParameter("title");
		String poster = request.getParameter("poster");
		String descriptions = request.getParameter("descriptions");


		Video video = service.findByHref(hrefO);
		video.setDescriptions(descriptions);;
		video.setHref(href);;
		video.setPoster(poster);;
		video.setTitle(title);;
		Video viUp=service.update(video);
		if (viUp != null) {
			response.setStatus(204);
		} else {
			response.setStatus(400);
		}
//		response.sendRedirect("/AssUI/admin/video");
	}

	private void doPostAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");
		String href = req.getParameter("href");
		String title = req.getParameter("title");
		String poster = req.getParameter("poster");
		String descriptions = req.getParameter("descriptions");
		Video video = new Video();
		video.setDescriptions(descriptions);
		video.setHref(href);
		video.setPoster(poster);
		video.setTitle(title);
		Video vid=service.create(video);
		
		if (vid != null) {
			resp.setStatus(204);
		} else {
			resp.setStatus(400);
		}
	
	}
	private void doGetOverViewVideo(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Video> listVi = service.findAll();
		req.setAttribute("videos", listVi);
		req.getRequestDispatcher("/view/admin/video-overview.jsp").forward(req, resp);
	}
}
