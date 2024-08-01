package datasource;

public class AddToCartDataSource {
	 public static final String INSERT_CART = "INSERT INTO Cart(username,productId) VALUES (?, ?)";
	   
	 public static final String SELECT_CART ="SELECT * FROM cart where username = ? ";
	 
	 public static final String SELECT_CART_PRODUCT ="SELECT * FROM product where product_id = ? ";
	 
	 public static final String  DELETE_CART_PRODUCTS= "DELETE FROM cart WHERE username = ? AND productId=?";
	 
	 public static final String  DELETE_CART= "DELETE FROM cart WHERE username = ?";
		
	   
}
