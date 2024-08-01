package controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.UserModel;
import services.UserServices;
import utils.StringUtils;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet(asyncSupported = true, urlPatterns =  StringUtils.SERVLET_URL_HOME)
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserServices userServices = new UserServices(); 
    /**
     * @see HttpServlet#HttpServlet()
     */
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<UserModel> users = userServices.getAllUsersInfo();
		request.setAttribute(StringUtils.USER_LISTS, users);
		request.getRequestDispatcher(StringUtils.URL_INDEX).forward(request, response);
	}
	 
}
