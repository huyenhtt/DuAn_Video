package com.vn.poly.service;

import javax.servlet.ServletContext;

import com.vn.poly.model.Users;

public interface EmailService {
   void sendEMail(ServletContext context,Users recipient,String type);
}
