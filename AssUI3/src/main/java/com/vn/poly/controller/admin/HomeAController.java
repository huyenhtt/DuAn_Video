package com.vn.poly.controller.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.jasper.tagplugins.jstl.core.Out;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vn.poly.constant.SessionAttr;
import com.vn.poly.dto.VideoLikedInfor;
import com.vn.poly.model.Users;
import com.vn.poly.service.StasService;
import com.vn.poly.service.UserService;
import com.vn.poly.service.impl.StastSerrviceImpl;
import com.vn.poly.service.impl.UserServiceImpl;

@WebServlet(urlPatterns = { "/admin", "/admin/favorites" }, name = "ControllerAdmin")
public class HomeAController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private StasService service = new StastSerrviceImpl();
	private UserService userService = new UserServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();

		Users curentU = (Users) session.getAttribute(SessionAttr.CURRENT_USER);
		if (curentU != null && curentU.getIsAdmin() == Boolean.TRUE) {
			String path = req.getServletPath();
			switch (path) {
			case "/admin": {

				this.doGetHome(req, resp);
				break;
			}
			case "/admin/favorites": {

				this.doGetFavorite(req, resp);
				break;
			}

			}
		} else {
			resp.sendRedirect("index");
		}

	}

	private void doGetFavorite(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		PrintWriter out = resp.getWriter();
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		String videoHref = req.getParameter("href");
		List<Users> list = userService.findUserLikedByVideoHref(videoHref);
		if (list.isEmpty()) {
			resp.setStatus(400);
		} else {
			ObjectMapper mapper = new ObjectMapper();
			String datareponse = mapper.writeValueAsString(list);
			resp.setStatus(200);
			out.print(datareponse);
			out.flush();
		}
	}

	private void doGetHome(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub

		List<VideoLikedInfor> lisst = service.findVideoLikedInfo();
		req.setAttribute("videos", lisst);

		req.getRequestDispatcher("/view/admin/home.jsp").forward(req, resp);

	}
}
