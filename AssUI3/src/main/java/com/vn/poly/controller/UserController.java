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

import com.vn.poly.dao.UserDao;
import com.vn.poly.dao.UserDaoImpl;
import com.vn.poly.model.Users;
import com.vn.poly.service.EmailService;
import com.vn.poly.service.UserService;
import com.vn.poly.service.impl.EmailServiceImpl;
import com.vn.poly.service.impl.UserServiceImpl;

@WebServlet({ "/login", "/logout", "/register", "/forgotPass" })
public class UserController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UserDao userDao = new UserDaoImpl();
	private UserService service = new UserServiceImpl();
	private List<Users> list = new ArrayList<>();
	private EmailService emailService = new EmailServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession sec = req.getSession();
		String uri = req.getRequestURI();
		if (uri.contains("login")) {
			this.doLogin(req, resp);
		} else if (uri.contains("register")) {
			this.doRegister(req, resp);

		} else if (uri.contains("logout")) {
			this.doLogOut(sec, req, resp);
		} else if (uri.contains("forgotPass")) {
			this.doForgotPass(sec, req, resp);
		}

	}

	private void doForgotPass(HttpSession sec, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.getRequestDispatcher("/view/user/forgotPass.jsp").forward(req, resp);
	}

	private void doLogOut(HttpSession sec, HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// TODO Auto-generated method stub
		sec.removeAttribute("curentUser");
		resp.sendRedirect("index");
	}

	private void doRegister(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/view/user/register.jsp").forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String uri = req.getRequestURI();
		if (uri.contains("login")) {
			this.doPostLogin(session, req, resp);
		} else if (uri.contains("register")) {
			this.doPostRegister(session, req, resp);
		} else if (uri.contains("forgotPass")) {
			this.doPostForgotPass(session, req, resp);
		}
	}

	private void doPostForgotPass(HttpSession session, HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		resp.setContentType("application/json");
		String email = req.getParameter("email");
		Users uss = service.resetPassWord(email);
		if (uss != null) {
			emailService.sendEMail(getServletContext(), uss, "forgot");
			resp.setStatus(204);
		} else {
			resp.setStatus(400);
		}
	}

	private void doPostRegister(HttpSession session, HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		try {
			String username = req.getParameter("username");
			String password = req.getParameter("password");
			String email = req.getParameter("email");
			String admin = req.getParameter("admin");
			Users usersw = userDao.checkLogin(username, password);
			if (usersw != null) {
				req.setAttribute("Amessage", "Password or Username is duplicate!");
				req.getRequestDispatcher("/view/user/register.jsp").forward(req, resp);

			} else if ("".equals(username) || "".equals(password) || "".equals(email)) {
				req.setAttribute("errorMessageA", "Password or Username or Email must be not blank!");
				req.getRequestDispatcher("/view/user/register.jsp").forward(req, resp);
				return;
			}

			if (usersw.getEmail().equals(email) || usersw.getUsername().equals(username)) {
				req.setAttribute("Amessage", "Password or Username is duplicate!");
				req.getRequestDispatcher("/view/user/register.jsp").forward(req, resp);
				return;
			}

			else {
				Users userss = new Users(username, password, email, Boolean.valueOf(admin), true);
				userDao.create(userss);
				list.add(userss);
				req.getSession().setAttribute("curentUser", usersw);

				resp.sendRedirect(req.getContextPath() + "/index");

			}

		} catch (Exception e) {
			req.setAttribute("Amessage", "Đăng ký thất bại!");
			req.getRequestDispatcher("/view/user/register.jsp").forward(req, resp);

		}

	}

	private void doPostLogin(HttpSession session, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String user = req.getParameter("username");
		String pass = req.getParameter("password");
		try {
			if ("".equals(user) || "".equals(pass)) {
				req.setAttribute("errorMessage", "Password or Username must be not blank!");
				req.getRequestDispatcher("/view/user/login.jsp").forward(req, resp);
				return;
			}

			Users users = userDao.checkLogin(user, pass);

			if (users == null) {
				req.setAttribute("errorMessage", "Password or Username is incorrect");
				req.getRequestDispatcher("/view/user/login.jsp").forward(req, resp);
			}

			else {
				req.getSession().setAttribute("curentUser", users);

				resp.sendRedirect(req.getContextPath() + "/index");

			}
		}

		catch (Exception e) {
			req.setAttribute("errorMessage", "Password or Username is incorrect");
			req.getRequestDispatcher("/view/user/login.jsp").forward(req, resp);
		}

	}

	private void doLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/view/user/login.jsp").forward(req, resp);

	}
}
