package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import configs.DbConnectionConfig;
import models.ProductModel;
import services.ProductServices;
import utils.StringUtilsProduct;

/**
 * Servlet implementation class UpdateProductServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/UpdateProductServlet" })
public class UpdateProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	private final DbConnectionConfig dbObj;
    private final ProductServices productService;

    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateProductServlet() {
    	 this.dbObj = new DbConnectionConfig();
         this.productService = new ProductServices();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	//sdfghj
        String name = request.getParameter("name");
        System.out.println("UpdateProductServlet product_id: " + name);
        ProductModel product = productService.getProductByName(name);

        request.setAttribute("product", product);
        request.getRequestDispatcher("/pages/modifyPage.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("update-product-name");
        String priceStr = request.getParameter("update-product-price"); ///
        String category = request.getParameter("update-product-category");
        String quantityStr = request.getParameter("update-product-quantity");
        String updateId = request.getParameter("updateId");
        
     
        System.out.println(name);
        System.out.println(priceStr);
        
    
        // Convert priceStr to double
        double price = 0.0; // Default value in case priceStr is null or empty
        if (priceStr != null && !priceStr.isEmpty()) {
            try {
                price = Double.parseDouble(priceStr);
            } catch (NumberFormatException e) {
                // Handle the case where priceStr is not a valid double
                e.printStackTrace(); // Or log the error
            }
        }
        
        // Convert quantityStr to int
        int quantity = 0; // Default value in case quantityStr is null or empty
        if (quantityStr != null && !quantityStr.isEmpty()) {
            try {
                quantity = Integer.parseInt(quantityStr);
            } catch (NumberFormatException e) {
                // Handle the case where quantityStr is not a valid integer
                e.printStackTrace(); // Or log the error
            }
        }
        
     // Server-side validation
        if (name == null || name.isEmpty()) {
            // Name is required
            request.setAttribute("msg", "Product name is required!");
            request.getRequestDispatcher(StringUtilsProduct.SERVLET_URL_MODIFY_USER).forward(request, response);
            return;
        }

        if (category == null || category.isEmpty()) {
            // Category is required
            request.setAttribute("msg", "Product category is required!");
            request.getRequestDispatcher(StringUtilsProduct.SERVLET_URL_MODIFY_USER).forward(request, response);
            return;
        }

        if (priceStr == null || priceStr.isEmpty()) {
            // Price is required
            request.setAttribute("msg", "Product price is required!");
            request.getRequestDispatcher(StringUtilsProduct.SERVLET_URL_MODIFY_USER).forward(request, response);
            return;
        }

        if (quantityStr == null || quantityStr.isEmpty()) {
            // Quantity is required
            request.setAttribute("msg", "Product quantity is required!");
            request.getRequestDispatcher(StringUtilsProduct.SERVLET_URL_MODIFY_USER).forward(request, response);
            return;
        }

        try {
            // Parse price and quantity, and perform additional validation
            price = Double.parseDouble(priceStr);
            quantity = Integer.parseInt(quantityStr);

            // Additional validation for negative values and zero
            if (price <= 0) {
                // Price must be a positive value
                request.setAttribute("msg", "Product price must be a positive value!");
                request.getRequestDispatcher(StringUtilsProduct.SERVLET_URL_MODIFY_USER).forward(request, response);
                return;
            }

            if (quantity <= 0) {
                // Quantity must be a positive value
                request.setAttribute("msg", "Product quantity must be a positive value!");
                request.getRequestDispatcher(StringUtilsProduct.SERVLET_URL_MODIFY_USER).forward(request, response);
                return;
        }
        } catch (NumberFormatException e) {
            // Handle parsing errors
            request.setAttribute("msg", "Invalid price or quantity format!");
            request.getRequestDispatcher(StringUtilsProduct.SERVLET_URL_MODIFY_USER).forward(request, response);
            return;
        }
        
        // Create ProductModel instance and set its properties
        ProductModel product = new ProductModel();
        product.setName(name);
        product.setPrice(price);
        product.setCategory(category);
      
        product.setQuantity(quantity);

        // Call the updateProductInfo method from the productService
        int result = productService.updateProductInfo(product, updateId);
        response.sendRedirect(request.getContextPath() + "/ProductServlet");
    }
}