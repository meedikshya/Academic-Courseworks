package controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import configs.DbConnectionConfig;
import datasource.ProductDataSource;
import datasource.UserRegistrationDataSource;
import utils.StringUtils;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = StringUtils.SERVLET_URL_ADMIN)
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession userSession = request.getSession();
        String currentUser = (String) userSession.getAttribute(StringUtils.USER_NAME);
        String contextPath = request.getContextPath();

        String userRole = "Admin";
        int totalUsers = 0; // Variable to store the total number of users
        int totalProducts = 0; // Variable to store the total number of products
        int totalOrders = 0;
        
        double totalIncome = 0.0;
        
        Connection conn = null;
        PreparedStatement roleStmt = null;
        ResultSet roleRs = null;
        PreparedStatement productStmt = null;
        ResultSet productRs = null;
        
        PreparedStatement orderStmt = null;
        ResultSet orderRs = null;
        
        PreparedStatement incomeStmt = null;
        ResultSet incomeRs = null;
        

        try {
            DbConnectionConfig dbObj = new DbConnectionConfig();
            conn = dbObj.getDbConnection();

            // Query to get the total number of users
            String userQuery = UserRegistrationDataSource.GET_USER;
            roleStmt = conn.prepareStatement(userQuery);
            roleRs = roleStmt.executeQuery();

            // Count the number of users
           if (roleRs.next()) {
            	userRole = roleRs.getString("user");
                System.out.println(userRole);
            }

            // Query to get the total number of products
            String productQuery = ProductDataSource.COUNT_ALL_PRODUCTS;
            productStmt = conn.prepareStatement(productQuery);
            productRs = productStmt.executeQuery();

            // Count the number of products
            if (productRs.next()) {
                totalProducts = productRs.getInt("total");
            }
            
         // Query to get the total number of products
            String orderQuery = ProductDataSource.COUNT_ALL_ORDER;
            orderStmt = conn.prepareStatement(orderQuery);
            orderRs = orderStmt.executeQuery();

            // Count the number of orders
            if (orderRs.next()) {
                totalOrders = orderRs.getInt("totalOrders");
            }
            
         // Query to get the total income
            String incomeQuery = ProductDataSource.GET_INCOME;
            incomeStmt = conn.prepareStatement(incomeQuery);
            incomeRs = incomeStmt.executeQuery();

            // Count the income
            if (incomeRs.next()) {
                totalIncome = incomeRs.getInt("totalIncome");
            }
            

            // Set userRole, totalUsers, and totalProducts as attributes in the request object
            request.setAttribute("userRole", userRole);
            request.setAttribute("total", totalProducts);
            request.setAttribute("totalOrders", totalOrders);
            request.setAttribute("totalIncome", totalIncome);
            
            

            // Forward the request to the JSP
            request.getRequestDispatcher(StringUtils.PAGE_URL_ADMIN_DASHBOARD).forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (roleRs != null) roleRs.close();
                if (roleStmt != null) roleStmt.close();
                if (productRs != null) productRs.close();
                if (productStmt != null) productStmt.close();
                if (orderRs != null) orderRs.close();
                if (orderStmt != null) orderStmt.close();
                if (incomeRs != null) incomeRs.close();
                if (incomeStmt != null) incomeStmt.close();
                
                if (conn != null) conn.close(); // Close connection
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}