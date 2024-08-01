package controllers;

import java.io.IOException;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.UserProductModel;
import services.UserProductServices;
import utils.StringUtils;
import utils.StringUtilsUserProduct;

/**
 * Servlet implementation class UserProductServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/UserProductServlet" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10, // 10MB
maxRequestSize = 1024 * 1024 * 50)

public class UserProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserProductServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
        	
            List<UserProductModel> allProducts = UserProductServices.getAllProducts();
            
            request.setAttribute("productList", allProducts);
          
         
           
            
//            System.out.println(allProducts);
//            System.out.println("Image directory path: " + StringUtils.IMAGE_DIR_PRODUCT);
//            System.out.println(request.getContextPath());
            request.getRequestDispatcher(StringUtilsUserProduct.PRODUCT_LIST_PAGE).forward(request, response);
            
            
            
        } catch (Exception e) {
            e.printStackTrace();
            // Handle exception, perhaps display an error message
            System.out.println("errorrrr");            
        }
    }

}
