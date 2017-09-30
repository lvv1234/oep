package com.zr.action.LoginRegister;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zr.dao.UserDao_hwx;
import com.zr.dao.impl.UserDaoImpl_hwx;
import com.zr.service.UserService;
import com.zr.service.impl.UserServiceImpl;

/**
*@author VerSion
*@time 2017年9月22日下午5:11:12
*/
@SuppressWarnings("serial")
public class LoginAction extends HttpServlet{
	UserDao_hwx login = new UserDaoImpl_hwx();
	UserService us = new UserServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String userName = req.getParameter("uname");
		String keyINpsw = req.getParameter("password");
		String identifyingcode = req.getParameter("idtfcode");
		
		int u_id = us.getU_idByUname(userName);
		HttpSession session = req.getSession();
		session.setAttribute("u_id", u_id);
		session.setAttribute("u_name", userName);
		
		String upsw = login.Getpswbyuname(userName);
		if ((keyINpsw.equals(upsw))&&(session.getAttribute("codevalue").equals(identifyingcode))) {
			resp.setCharacterEncoding("utf8");
			PrintWriter out = resp.getWriter();
			out.print("314");
		}else if(!(keyINpsw.equals(upsw))){
			resp.setCharacterEncoding("utf8");
			PrintWriter out = resp.getWriter();
			out.print("365");
		}else if ("".equals(identifyingcode)) {
			resp.setCharacterEncoding("utf8");
			PrintWriter out = resp.getWriter();
			out.print("391");
		}else if (!(session.getAttribute("codevalue").equals(identifyingcode))) {
			resp.setCharacterEncoding("utf8");
			PrintWriter out = resp.getWriter();
			out.print("392");
		}
	}
	
	
}
