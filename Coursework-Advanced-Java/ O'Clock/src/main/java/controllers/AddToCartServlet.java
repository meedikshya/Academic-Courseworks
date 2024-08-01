package controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import configs.DbConnectionConfig;
import models.AddToCartModel;
import models.UserProductModel;
import services.AddToCartServices;
import services.UserProductServices;
import utils.StringUtils;
import utils.StringUtilsCart;
import utils.StringUtilsProduct;
import utils.UserHelper;

/**
 * Servlet implementation class AddToCartServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/AddToCartServlet" })
public class AddToCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final DbConnectionConfig db;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddToCartServlet() {
    	this.db = new DbConnectionConfig();// TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = UserHelper.getGlobalUser();
		HttpSession us = request.getSession();
	
			List<UserProductModel> allProducts;
			try {
				allProducts = AddToCartServices.getAllCartProducts(username);
				
				request.setAttribute("productList", allProducts);
				
				 if (!allProducts.isEmpty()) {
		                //request.setAttribute("cartLists", allProducts);
		                request.getRequestDispatcher(StringUtilsCart.CART_PAGE ).forward(request, response);
		            } else {
		                System.out.println("No products found.");
		                // Handle case where no products are found, perhaps display an error message
		            }
				response.getWriter().append("Served at: "+allProducts).append(request.getContextPath());
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession us = request.getSession();
		String username = request.getParameter("username");
		
		
		//For delete
		String deleteId = request.getParameter(StringUtilsProduct.DELETE_ID);
		System.out.println("post triggered" + deleteId);
		if (deleteId != null && !deleteId.isEmpty()) {
			doDelete(request, response);
		}
		
		else {
			int productId = Integer.parseInt(request.getParameter("productId"));
			String userId = (String) us.getAttribute("userId");
			AddToCartModel cart = new AddToCartModel();
			cart.setUsername(username);
			cart.setProductId(productId);
			
			//Use select query in cart for username with productid 
			//if()
			
			int result;
			try {
				result = AddToCartServices.addProduct(cart);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			doGet(request, response);
		}
		//System.out.println(result);

	
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
	     HttpSession session = request.getSession();
	     String username = UserHelper.getGlobalUser();
	     int productId = Integer.parseInt(request.getParameter(StringUtilsCart.DELETE_ID));
	     
	     System.out.println("delete triggered");
	     try {
	         if (AddToCartServices.deleteProduct(username, productId) == 1) {
	             request.setAttribute(StringUtilsCart.MESSAGE_SUCCESS, StringUtilsCart.MESSAGE_SUCCESS_DELETE);
	             response.sendRedirect(request.getContextPath() + StringUtilsCart.SERVLET_URL_ADD_TO_CART);
	         } else {
	             request.setAttribute(StringUtilsCart.MESSAGE_ERROR, StringUtilsCart.MESSAGE_ERROR);
	             response.sendRedirect(request.getContextPath() + StringUtilsCart.SERVLET_URL_ADD_TO_CART);
	         }
	     } catch (ClassNotFoundException e) {
	         e.printStackTrace();
	     }
	}


}