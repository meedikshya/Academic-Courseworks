package controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import configs.DbConnectionConfig;
import datasource.ProductDataSource;
import models.ProductModel;
import services.ProductServices;
import utils.StringUtilsProduct;

/**
 * Servlet implementation class ProductServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = StringUtilsProduct.SERVLET_URL_ADD_PRODUCT)
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10, // 10MB
maxRequestSize = 1024 * 1024 * 50)

public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductServices productServices = new ProductServices();
  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = null;
        PreparedStatement productStmt = null;
        ResultSet productRs = null;
        try {
            DbConnectionConfig dbObj = new DbConnectionConfig();
            conn = dbObj.getDbConnection();
            String productQuery = ProductDataSource.SELECT_ALL_PRODUCTS;
            productStmt = conn.prepareStatement(productQuery);
            productRs = productStmt.executeQuery();
            
            List<ProductModel> allProducts = new ArrayList<>();

            while (productRs.next()) {
                String name = productRs.getString("product_name");
                String category = productRs.getString("Product_Category");
                double price = productRs.getDouble("unit_price");
                int quantity = productRs.getInt("stockQuantity");
                String imageUrl = productRs.getString("product_image");

                ProductModel product = new ProductModel(name, category, price, quantity, imageUrl);
                allProducts.add(product);
            }

            if (!allProducts.isEmpty()) {
                request.setAttribute("productLists", allProducts);
                request.getRequestDispatcher(StringUtilsProduct.PRODUCT_LIST_PAGE).forward(request, response);
            } else {
                System.out.println("No products found.");
                // Handle case where no products are found, perhaps display an error message
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception, perhaps display an error message
            System.out.println("errorrrr");

        } finally {
            try {
                if (conn != null) conn.close();
                if (productStmt != null) productStmt.close();
                if (productRs != null) productRs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Extracting parameters from the request
        String name = request.getParameter("productName");
        String category = request.getParameter("category");
        Part imagePart = request.getPart("productImage");
        String priceParam = request.getParameter("unitPrice");
        String quantityParam = request.getParameter("stockQuantity");
        
        // Convert price and quantity to appropriate data types
        double price;
        int quantity;

        // Server-side validation
        if (name == null || name.isEmpty() ||
            category == null || category.isEmpty() ||
            priceParam == null || priceParam.isEmpty() ||
            quantityParam == null || quantityParam.isEmpty()) {
            
            // Validation failed, redirect back to the form page with error message
            request.setAttribute("msg", "Fields can't be empty!");
            request.getRequestDispatcher(StringUtilsProduct.ADD_PRODUCT_PAGE).forward(request, response);
            return;
        }

        try {
            // Parse price and quantity, and perform additional validation
            price = Double.parseDouble(priceParam);
            quantity = Integer.parseInt(quantityParam);

            // Additional validation for negative values and zero
            if (price <= 0 || quantity <= 0) {
                
                // Validation failed, redirect back to the form page with error message
                request.setAttribute("msg", "Please enter positive values only!");
                request.getRequestDispatcher(StringUtilsProduct.ADD_PRODUCT_PAGE).forward(request, response);
                return;
            }
        } catch (NumberFormatException e) {
            // Handle parsing errors
            request.setAttribute("msg", "Something went wrong!");
            request.getRequestDispatcher(StringUtilsProduct.ADD_PRODUCT_PAGE).forward(request, response);
            return;
        }

        // Check if the product name already exists in the database
        boolean isProductNameUnique = productServices.isProductNameUnique(name);
        if (!isProductNameUnique) {
            // Product name already exists, redirect back to the form page with error message
            request.setAttribute("msg", "Product name already exists!");
            request.getRequestDispatcher(StringUtilsProduct.ADD_PRODUCT_PAGE).forward(request, response);
            return;
        }

        

        ProductModel product = new ProductModel(name, category, price, quantity, imagePart);
        
        int result = 0; 
        
        try {
            result = productServices.addProduct(product);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (result > 0) {
        	
        	// Get the image file name from the student object (assuming it was extracted earlier)
        	String fileName = product.getImageUrlFromPart();
        				
        	// Check if a filename exists (not empty or null)
        	if (!fileName.isEmpty() && fileName != null) {
        	// Construct the full image save path by combining the directory path and filename
        	String savePath = StringUtilsProduct.IMAGE_DIR_PRODUCT;
        	imagePart.write(savePath + fileName);  // Save the uploaded image to the specified path
        	}
        	
        	 request.setAttribute("msg", "Successfully Added Product!");
//            request.setAttribute(StringUtils.MESSAGE_SUCCESS, StringUtils.ADD_PRODUCT_SUCCESS_MESSAGE);
            response.sendRedirect(request.getContextPath() + StringUtilsProduct.SERVLET_URL_ADD_PRODUCT);

            List<ProductModel> addedProducts = new ArrayList<>();
            addedProducts.add(product);
            request.setAttribute("addedProducts", addedProducts);
            System.out.println("Added Products ArrayList: " + addedProducts);
        } else {
        	 request.setAttribute("msg", "Something went wrong!");
//            request.setAttribute(StringUtils.MESSAGE_ERROR, StringUtils.ADD_PRODUCT_ERROR_MESSAGE);
            request.getRequestDispatcher(StringUtilsProduct.ADD_PRODUCT_PAGE).forward(request, response);
        }
    }

    private double parseDoubleOrDefault(String value, double defaultValue) {
        if (value != null && !value.trim().isEmpty()) {
            try {
                return Double.parseDouble(value.trim());
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return defaultValue;
    }

    private int parseIntOrDefault(String value, int defaultValue) {
        if (value != null && !value.trim().isEmpty()) {
            try {
                return Integer.parseInt(value.trim());
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return defaultValue;
    }
}