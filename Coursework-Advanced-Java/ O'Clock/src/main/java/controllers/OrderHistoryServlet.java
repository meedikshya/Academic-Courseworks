package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.OrderDetailModel;
import models.OrderDetailsViewModel;
import models.OrderModel;
import models.ProductModel;
import models.UserProductModel;
import services.OrderServices;
import utils.StringUtilsCart;
import utils.UserHelper;

/**
 * Servlet implementation class OrderHistoryServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/OrderHistoryServlet" })
public class OrderHistoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderHistoryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = UserHelper.getGlobalUser();
		HttpSession us = request.getSession();
	
		List<OrderModel> allOrders;
		List<OrderDetailsViewModel> orderDetailsVM = new ArrayList<>();
		try {
			//get all order for user
			allOrders = OrderServices.getAllOrders(username);
			
			if(!allOrders.isEmpty()) {
				//For all orders, getting orderItems
				for(OrderModel order: allOrders) {
					var allOrderItems = OrderServices.getAllOrdersItemJoin(order.getOrder_id());
					//Getting all the orderItems for user and adding to another list
					for(OrderDetailsViewModel orderItem: allOrderItems) {
						orderDetailsVM.add(orderItem);
					}		
				}
				request.setAttribute("orderlist", allOrders);
				request.setAttribute("orderDetailsVM", orderDetailsVM);
				request.getRequestDispatcher(StringUtilsCart.ORDER_HISTORY_PAGE).forward(request, response);
			}
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 //request.getRequestDispatcher(StringUtilsCart.ORDER_HISTORY_PAGE).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
