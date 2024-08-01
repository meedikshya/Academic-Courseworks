package controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.UserModel;
import services.UserManagementServices;
import utils.StringUtils;

/**
 * Servlet implementation class UserDisplayServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = StringUtils.SERVLET_URL_USER_DISPLAY)
public class UserDisplayServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserManagementServices userManagementServices = new UserManagementServices();   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserDisplayServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
        	 
            // Retrieve all users
            ArrayList<UserModel> users = userManagementServices.getAllUsersInfo();
            
          
            System.out.println("Total number of users: " + users.size());
            
            // Set users as a request attribute
            request.setAttribute("users", users);
            
            // Forward the request to the JSP page
            System.out.println(request.getContextPath());
           request.getRequestDispatcher(StringUtils.PAGE_URL_USER_LIST).forward(request, response);
          // response.sendRedirect(request.getContextPath() + StringUtils.PAGE_URL_USER_LIST);
        } catch (Exception e) {
            e.printStackTrace();
            // Handle exception, perhaps display an error message
              }
    }

}
