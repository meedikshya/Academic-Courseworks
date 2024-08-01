package controllers;

import java.io.IOException;



import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.UserLoginModel;
import services.UserServices;
import utils.StringUtils;
import utils.UserHelper;

@WebServlet(urlPatterns = StringUtils.SERVLET_URL_LOGIN, asyncSupported = true)
public class LoginUserServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final UserServices userServices;

    public LoginUserServlet() {
        this.userServices = new UserServices();
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		request.getRequestDispatcher(StringUtils.PAGE_URL_LOGIN).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	 protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        String userName = request.getParameter(StringUtils.USERNAME);
	        String password = request.getParameter(StringUtils.PASSWORD);

	        UserLoginModel userLoginModel = new UserLoginModel(userName,  password);

	        int loginResult = userServices.getUserLoginInfo(userLoginModel);

	        if (loginResult == 1) {
	            // Fetch user role from the database
	            int userRole = userServices.getUserRole(userLoginModel);

	            HttpSession userSession = request.getSession();
	            userSession.setAttribute(StringUtils.USERNAME, userName);
	            userSession.setAttribute(StringUtils.USER_ROLE, userRole);

	            // Set session timeout
	            userSession.setMaxInactiveInterval(30*60);

	            // Create and add a cookie with the username
	            Cookie userCookie= new Cookie(StringUtils.USER, userName);
	            userCookie.setMaxAge(30*60);
	            response.addCookie(userCookie);
	            
	            if (userRole == 1) {
	                // Redirect admin users to the admin dashboard
	                response.sendRedirect(request.getContextPath() + StringUtils.SERVLET_URL_ADMIN);
	                UserHelper.setGlobalUser(userName);
	            } else {
	                // Redirect regular users to the homepage
	                response.sendRedirect(request.getContextPath() + StringUtils.URL_INDEX);
	                UserHelper.setGlobalUser(userName);
	            }
	        } else if (loginResult == 0) {
	            request.setAttribute(StringUtils.MESSAGE_ERROR, StringUtils.MESSAGE_ERROR_LOGIN);
	            request.setAttribute(StringUtils.USERNAME, userName);
	            request.getRequestDispatcher(StringUtils.PAGE_URL_LOGIN).forward(request, response);
	        } else if (loginResult == -1) {
	            request.setAttribute(StringUtils.MESSAGE_ERROR, StringUtils.MESSAGE_ERROR_CREATE_ACCOUNT);
	            request.setAttribute(StringUtils.USERNAME, userName);
	            request.getRequestDispatcher(StringUtils.PAGE_URL_LOGIN).forward(request, response);
	        } else {
	            request.setAttribute(StringUtils.MESSAGE_ERROR, StringUtils.MESSAGE_ERROR_SERVER);
	            request.setAttribute(StringUtils.USERNAME, userName);
	            request.getRequestDispatcher(StringUtils.PAGE_URL_LOGIN).forward(request, response);
	        }
	    }



	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    // Logout functionality
	    System.out.println("Entering doDelete method for logout");
	    HttpSession session = request.getSession(false);
	    if (session != null) {
	        System.out.println("Invalidating session");
	        session.invalidate();
	    } else {
	        System.out.println("Session is already null");
	    }
	    response.sendRedirect(request.getContextPath() + StringUtils.PAGE_URL_LOGIN);
	}


}

