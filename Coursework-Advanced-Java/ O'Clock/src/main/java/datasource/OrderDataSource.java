package datasource;

public class OrderDataSource {
	public static final String INSERT_ORDERS= "INSERT INTO orders (username,grandTotal,totalItems,orderStatus) VALUES (?,?,?,?)";
	
	 public static final String ORDER_DETAILS= "INSERT INTO order_details (orderId, productId,price,quantity) VALUES (?, ?, ?,?)";
	 
	 public static final String USER_ORDER= "SELECT * FROM orders WHERE username = ?";
	 
	 public static final String USER_ORDERITEMS= "SELECT * FROM order_details WHERE orderId = ?";
	 
	 public static final String USER_PRODUCT= "SELECT * FROM product WHERE product_id = ?";
	 
	 public static final String ORDER_UPDATE= "UPDATE product set stockQuantity = stockQuantity - ? where product_id = ?";
	 
	 public static final String USER_ORDERITEMS_JOIN= "SELECT * FROM orders o INNER JOIN order_details od on od.orderId = o.order_id INNER JOIN product p on p.product_id = od.productId where o.order_id=?";
		
	 
}
