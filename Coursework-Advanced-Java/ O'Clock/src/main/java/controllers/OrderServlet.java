package controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.OrderDetailModel;
import models.OrderModel;
import models.UserProductModel;
import services.AddToCartServices;
import services.OrderServices;
import utils.StringUtilsCart;
import utils.UserHelper;

/**
 * Servlet implementation class OrderServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/OrderServlet" })
public class OrderServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = UserHelper.getGlobalUser();

        List<UserProductModel> allProducts;
        try {
            allProducts = AddToCartServices.getAllCartProducts(username);
            int totalItems = allProducts.size();

            float grandTotal = 0.0f;
            for (UserProductModel product : allProducts) {
                grandTotal += product.getPrice() * product.getQuantity();
            }

            if (!allProducts.isEmpty()) {
                // Create an order
                OrderModel order = new OrderModel();
                order.setUsername(username);
                order.setTotalItems(totalItems);
                order.setGrandTotal(grandTotal);
                order.setOrderStatus("Pending");

                // Save the order
                int orderId = OrderServices.setOrder(order);
                order.setOrder_id(orderId);

             // Pass attributes to the JSP
                request.setAttribute("orderId", orderId); // Pass the orderId attribute to the JSP

                
                // Save order details
                if (orderId != 0) {
                    for (UserProductModel product : allProducts) {
                    	int prodId = product.getId();
                    	int quantity = product.getQuantity();
                    	
                        OrderDetailModel orderDetail = new OrderDetailModel();
                        orderDetail.setOrderId(orderId);
                        orderDetail.setProductId(product.getId());
                        orderDetail.setQuantity(product.getQuantity());
                        orderDetail.setPrice(product.getPrice());
                        
                        //For reducing quantity
                        OrderServices.reduceProductQuantity(prodId, quantity);

                        OrderServices.setOrderDetails(orderDetail);
                    }

                    // Delete cart items
                    OrderServices.deleteCart(username);

                    // Pass attributes to the JSP
                    request.setAttribute("order", order);
                    
                    request.setAttribute("orderDetails", allProducts);

                    // Forward to the JSP
     
                    request.getRequestDispatcher(StringUtilsCart.ORDER_PAGE).forward(request, response);
                } else {
                    // Handle if order creation fails
                    System.out.println("Failed to create order.");
                    // You can redirect or show an error message to the user
                }
            } else {
                System.out.println("No products found.");
                // Handle case where no products are found, perhaps display an error message
            }
        } catch (ClassNotFoundException e) {
            // Handle ClassNotFoundException
            e.printStackTrace();
        }
    }

}
