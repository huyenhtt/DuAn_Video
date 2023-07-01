package com.vn.poly.controller.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vn.poly.model.Video;
import com.vn.poly.service.VideoService;
import com.vn.poly.service.impl.VideoServiceImpl;

@WebServlet(name = "videoControllerOfAdmin", value = { "/adminv/video-hien-thi", "/adminv/clear-form",
		"/adminv/video-detail/*", "/adminv/video-create", "/adminv/video-delete", "/adminv/video-update" })
public class VideoControllerOfAdmin extends HttpServlet {

	private static final long serialVersionUID = 8533210018553097688L;
	private VideoService service = new VideoServiceImpl();
	private List<Video> listVideo = new ArrayList<>();

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI();
		req.setCharacterEncoding("UTF8");
		resp.setCharacterEncoding("UTF8");
		if (uri.contains("video-hien-thi")) {
			this.hienThiVideo(req, resp);
		} else if (uri.contains("video-detail")) {
			this.detailVideo(req, resp);
		} else if (uri.contains("clear-form")) {
			this.hienThiVideo(req, resp);
		}

		else if (uri.contains("video-create")) {
			this.createVideo(req, resp);
		} else if (uri.contains("video-update")) {
			this.updateVideo(req, resp);
		} else if (uri.contains("video-delete")) {
			this.deleteVideo(req, resp);
		} else {
			this.hienThiVideo(req, resp);
		}
	}

	private void deleteVideo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String href = req.getParameter("href");
		Video videoo = service.delete(href);
		int vtri = -1;
		for (int i = 0; i < listVideo.size(); i++) {
			if (listVideo.get(i).getHref().equalsIgnoreCase(href)) {
				vtri = i;
			}
		}
		listVideo.remove(vtri);
		req.setAttribute("videoForm", videoo);
		resp.sendRedirect("/AssUI/adminv/video-hien-thi");
	}

	private void updateVideo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String hrefN = req.getParameter("href");
		String titleN = req.getParameter("title");
		String descriptionN = req.getParameter("descriptions");
		String posterN = req.getParameter("poster");
		int vitri = -1;

		for (int i = 0; i < listVideo.size(); i++) {
			if (listVideo.get(i).getHref().equalsIgnoreCase(hrefN)) {
				vitri = i;
			}
		}
		Video vi = listVideo.get(vitri);
		vi.setDescriptions(descriptionN);
		vi.setHref(hrefN);

		vi.setPoster(posterN);
		vi.setTitle(titleN);

		Video videoL = service.update(vi);
		listVideo.set(vitri, videoL);
		resp.sendRedirect("/AssUI/adminv/video-hien-thi");
	}

	private void createVideo(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		// TODO Auto-generated method stub
		String href = req.getParameter("href");
		String title = req.getParameter("title");
		String description = req.getParameter("descriptions");
		String poster = req.getParameter("poster");
		for (int i = 0; i < listVideo.size(); i++) {
			if (listVideo.get(i).getHref().equalsIgnoreCase(href)) {
				req.setAttribute("messEror", "trùng href nhập lại");
				req.getRequestDispatcher("/view/admin/manage-video.jsp").forward(req, resp);
				return;
			}
		}
		Video vi = new Video();
		vi.setDescriptions(description);
		vi.setHref(href);
		vi.setTitle(title);
		vi.setIsActive(Boolean.TRUE);
		vi.setPoster(poster);
		Video videoL = service.create(vi);
		listVideo.add(vi);

		resp.sendRedirect("/AssUI/adminv/video-hien-thi");

	}

	private void detailVideo(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		// TODO Auto-generated method stub
		String uri = req.getRequestURI();

		String id = uri.substring(uri.lastIndexOf("/") + 1);
		Video videoa = service.findByID(Integer.valueOf(id));
		req.setAttribute("videoForm", videoa);
		hienThiVideo(req, resp);

	}

	private void hienThiVideo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		listVideo = service.findAll();
		req.setAttribute("listV", listVideo);
		req.getRequestDispatcher("/view/admin/manage-video.jsp").forward(req, resp);
	}

}
