package com.vn.poly.controller.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vn.poly.model.Users;
import com.vn.poly.service.UserService;
import com.vn.poly.service.impl.UserServiceImpl;
@WebServlet(name = "UserServlet", value = { "/admin1/hien-thi-user", "/admin1/detail/*", "/admin1/clear-form",
		"/admin1/update-user", "/admin1/delete-user" })
public class ManageUserController extends HttpServlet{

	private static final long serialVersionUID = 1L;
     private List<Users> list=new ArrayList<>();
     private UserService userService=new UserServiceImpl();
   
     @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	 String uri = req.getRequestURI();
 		resp.setCharacterEncoding("UTF8");
 		resp.setCharacterEncoding("UTF8");
 		if (uri.contains("hien-thi-user")) {
			this.hienThiUser(req, resp);
		} else if (uri.contains("detail")) {
			this.detailUser(req, resp);
		} else if (uri.contains("clear-form")) {
			this.hienThiUser(req,resp );
		} else if (uri.contains("delete-user")) {
			this.deleteUser(req,resp );
		}else if (uri.contains("update-user")) {
			this.updateUser(req,resp );
		} else {
			this.hienThiUser(req,resp );
		}
    }

	private void updateUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF8");
		resp.setCharacterEncoding("UTF8");
		String usernameNew = req.getParameter("username");
		String passwordNew = req.getParameter("password");
		String emailNew = req.getParameter("email");
		String adminNew = req.getParameter("admin");
		int vitri = -1;
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getUsername().equalsIgnoreCase(usernameNew)) {
				vitri = i;
			}
		}
		Users user = list.get(vitri);
		user.setIsAdmin(Boolean.valueOf(adminNew));
		user.setEmail(emailNew);
		user.setUsername(usernameNew);
		user.setPassword(passwordNew);
		list.set(vitri, user);
		userService.update(user);

		resp.sendRedirect("/AssUI/admin1/hien-thi-user");
	}

	private void deleteUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// TODO Auto-generated method stub
		String username = req.getParameter("username");

		Users users = userService.delete(username);
		int vitri = -1;
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getUsername().equalsIgnoreCase(username)) {
				vitri = i;
			}
		}
		list.remove(vitri);
		req.setAttribute("userForm", users);
		resp.sendRedirect("/AssUI/admin1/hien-thi-user");
	}

	private void detailUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String uri = req.getRequestURI();

		String id = uri.substring(uri.lastIndexOf("/") + 1);
		Users users = userService.findByID(Integer.valueOf(id));
		req.setAttribute("userForm", users);
		hienThiUser(req, resp);
	}
 
	private void hienThiUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		list=userService.findAllActive();
		req.setAttribute("listU", list);
//		request.setAttribute("items", users);
		req.getRequestDispatcher("/view/admin/manage-user.jsp").forward(req, resp);

	}
}
