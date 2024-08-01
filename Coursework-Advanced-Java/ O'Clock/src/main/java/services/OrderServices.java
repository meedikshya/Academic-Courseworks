package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import configs.DbConnectionConfig;
import datasource.AddToCartDataSource;
import datasource.OrderDataSource;
import datasource.ProductDataSource;
import models.OrderModel;
import models.ProductModel;
import models.UserProductModel;
import models.AddToCartModel;
import models.OrderDetailModel;
import models.OrderDetailsViewModel;

public class OrderServices {

	private final static DbConnectionConfig dbObj = new DbConnectionConfig();
	
    public static int setOrder(OrderModel order) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int result = 0;

        try {
            DbConnectionConfig dbObj = new DbConnectionConfig();
            conn = dbObj.getDbConnection();
            
            // Prepare the SQL statement to insert an order
            String query = OrderDataSource.INSERT_ORDERS;
            stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, order.getUsername());
            stmt.setDouble(2, order.getGrandTotal());
            stmt.setInt(3, order.getTotalItems());
            stmt.setString(4, order.getOrderStatus());
            
            // Execute the SQL statement
            result = stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            int auto_id = rs.getInt(1);
            result = auto_id;
           
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle SQLException
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    public static int setOrderDetails(OrderDetailModel orderDetail) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int result = 0;

        try {
            DbConnectionConfig dbObj = new DbConnectionConfig();
            conn = dbObj.getDbConnection();
            
            // Prepare the SQL statement to insert an order detail
            String query = OrderDataSource.ORDER_DETAILS;
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, orderDetail.getOrderId());
            stmt.setInt(2, orderDetail.getProductId());
            stmt.setDouble(3, orderDetail.getPrice());
            stmt.setInt(4, orderDetail.getQuantity());
            
            // Execute the SQL statement
            result = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle SQLException
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return result;
    }
    
    public static int deleteCart( String username) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int result = 0;

        try {
            DbConnectionConfig dbObj = new DbConnectionConfig();
            conn = dbObj.getDbConnection();
            
            // Prepare the SQL statement to insert an order detail
            String query = AddToCartDataSource.DELETE_CART;
            stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            
            // Execute the SQL statement
            result = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle SQLException
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return result;
    }
    
    public static List<OrderModel> getAllOrders(String username) throws ClassNotFoundException {
        List<OrderModel> orderList = new ArrayList<>();

        try (Connection conn = dbObj.getDbConnection()) {
            PreparedStatement st = conn.prepareStatement(OrderDataSource.USER_ORDER );
            st.setString(1, username);
            ResultSet result = st.executeQuery();

            while (result.next()) {
                int orderId = result.getInt("order_id");
                double grandTotal = result.getDouble("grandTotal");
                int totalItems = result.getInt("totalItems");;
                String orderStatus = result.getString("orderStatus");

                OrderModel order = new OrderModel();
                order.setOrder_id(orderId);
                order.setGrandTotal(grandTotal);
                order.setTotalItems(totalItems);
                order.setOrderStatus(orderStatus);
                
                orderList.add(order);
            }        
            return orderList;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public static List<OrderDetailsViewModel> getAllOrdersItemJoin(int orderId) throws ClassNotFoundException {
        List<OrderDetailsViewModel> orderItemList = new ArrayList<>();

        try (Connection conn = dbObj.getDbConnection()) {
            PreparedStatement st = conn.prepareStatement(OrderDataSource.USER_ORDERITEMS_JOIN);
            st.setInt(1, orderId);
            ResultSet result = st.executeQuery();

            while (result.next()) {
                //int orderDetailsId1 = result.getInt("orderdetails_id");
                double price = result.getDouble("price");
                int quantity = result.getInt("quantity");
                int orderId1 = result.getInt("orderId");
                int productId = result.getInt("productId");
                String productName = result.getString("product_name");
                String imageUrl = result.getString("product_image");
                
                OrderDetailsViewModel odvm = new OrderDetailsViewModel();
                odvm.setName(productName);
                odvm.setProductId(productId);
                odvm.setPrice(price);
                odvm.setQuantity(quantity);
                odvm.setOrderId(orderId);
                odvm.setImageUrl(imageUrl);
                
                orderItemList.add(odvm);
            }        
            return orderItemList;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
	public static void reduceProductQuantity(int productId, int quantity) {
		// TODO Auto-generated method stub
		 try (Connection conn = dbObj.getDbConnection()) {
	            PreparedStatement st = conn.prepareStatement(OrderDataSource.ORDER_UPDATE);
	            st.setInt(1, quantity);
	            st.setInt(2, productId);
	            int result = st.executeUpdate();
	            
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	            
	        }
		
	}
      
    
}
