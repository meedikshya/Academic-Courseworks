package utils;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class StringUtilsProduct {
	// Utility methods for database operations\

		public static final String FILTER_URL = "/ProductFilter";
	    public static final String SERVLET_URL_ADD_PRODUCT = "/ProductServlet";
	    public static final String ADD_PRODUCT_PAGE = "pages/AddProduct.jsp";
	    public static final String PRODUCT_LIST_PAGE = "/pages/productList.jsp";
	    public static final String SERVLET_URL_MODIFY_USER = "/ModifyProductServlet";
	    public static final String SERVLET_URL_UPDATE_USER = "/UpdateProductServlet";
	    public static final String  PAGE_URL_ADMIN_DASHBOARD = "/pages/adminDashboard";
	    
	    // Message constants for adding products
	    public static final String ADD_PRODUCT_SUCCESS_MESSAGE = "Product added successfully!";
	    public static final String ADD_PRODUCT_ERROR_MESSAGE = "Failed to add product. Please try again.";
	    
	    public static final String MESSAGE_ERROR_SERVER = "An unexpected server error occurred.";
		public static final String MESSAGE_SUCCESS_DELETE = "Successfully Deleted!";
		public static final String MESSAGE_ERROR_DELETE = "Cannot delete the user!";
		
	    
	    public static final String DELETE_ID= "deleteId";
		public static final String UPDATE_ID= "updateId";

	    public static final String MESSAGE_SUCCESS = "successMessage";
	    public static final String MESSAGE_ERROR = "errorMessage";
	    
	     
	    public static final String IMAGE_ROOT_PATH = "Users\\Binita bhandari\\eclipse-workspace\\ O'Clock\\src\\main\\webapp\\resources\\images\\";

	    public static final String IMAGE_DIR_PRODUCT = "C:/" + IMAGE_ROOT_PATH + "product\\";
		public static final String IMAGE_DIR_USER = "C:/" + IMAGE_ROOT_PATH + "user\\";
	  
		
//		public static final String IMAGE_DIR_PRODUCT = "C:\\\\Users\\\\User\\\\eclipse-workspace\\\\ProductCrud\\\\src\\\\main\\\\webapp\\\\Resources\\\\images\\\\product\\";
		public static final String IMAGE_DIR_SAVE_PATH = "" + File.separator + IMAGE_DIR_PRODUCT;
		
//		public static final String IMAGE_DIR_USER = "C:\\Users\\User\\eclipse-workspace\\ProductCrud\\src\\main\\webapp\\Resources\\images\\user\\";
		public static final String IMAGE_DIR_SAVE_PATH_USER = "" + File.separator + IMAGE_DIR_USER;

	//
//		 public static String generateUniqueFileName() {
//		        // Generate a unique file name using UUID
//		        return UUID.randomUUID().toString();
//		    }
		
	    public static void close(Connection conn) {
	        if (conn != null) {
	            try {
	                conn.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }

	    public static void close(PreparedStatement ps) {
	        if (ps != null) {
	            try {
	                ps.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }

	    public static void close(ResultSet rs) {
	        if (rs != null) {
	            try {
	                rs.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	}

