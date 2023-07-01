package com.vn.poly.service.impl;

import javax.servlet.ServletContext;

import com.vn.poly.model.Users;
import com.vn.poly.service.EmailService;
import com.vn.poly.util.EmailUtil;

public class EmailServiceImpl implements EmailService {
//		 ServletContext context = getServletContext();
     public static final String EMAIL_WELCOME_SUBJECT="Webcome to Online Entertainment";
     public static final String EMAIL_FORGOT_SUBJECT="Online Entertainment - New Password";
	@Override
	public void sendEMail(ServletContext context, Users recipient, String type) {
	    
		String  host = context.getInitParameter("host");
	      String  port = context.getInitParameter("port");
	      String user = context.getInitParameter("user");
	      String pass = context.getInitParameter("pass");
	  try {  
		  String content=null;
		  String subject=null;
		  switch (type) {
		case "welcome": 
			
			subject=EMAIL_WELCOME_SUBJECT;
			content="Dear "+recipient.getUsername()+" !"+",  hope you have a good time";
			break;
		
		case "forgot": 
			
			subject=EMAIL_FORGOT_SUBJECT;
			content="Dear "+recipient.getUsername()+" !"+",your password "+recipient.getPassword();
			break;
		
		default:
			subject="Online Entertainment";
			content="May be email is wrong!";
			break;
		  }
		 EmailUtil.sendEmail(host, port, user, pass, recipient.getEmail(), subject, content);
		  }catch (Exception e) {
			// TODO: handle exception
			  e.printStackTrace();
		  }
	}

}
