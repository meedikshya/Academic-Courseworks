package datasource;

public class UserProductDataSource {
	public static final String INSERT_PRODUCT = "INSERT INTO product"
			+ "(product_name, product_category, unit_price, stockQuantity, product_image)"
			+"VALUES(?,?,?,?,?)";
	
	public static final String GET_ALL_PRODUCTS = "SELECT * FROM product";
	
	public static final String GET_PRODUCT_ID  = "SELECT count(*) FROM product WHERE id = ?";
	
	public static final String GET_PRODUCT_NAME  = "SELECT count(*) FROM product WHERE name = ?";
	
	public static final String GET_PRODUCT_DESCRIPTION = "SELECT count(*) FROM product WHERE description = ?";
	
	public static final String GET_PRICE  = "SELECT count(*) FROM product WHERE price = ?";
	
	public static final String GET_IMAGE  = "SELECT count(*) FROM product WHERE image = ?";
	
	public static final String DELETE_PRODUCT = "SELECT FROM product WHERE id = ?";	
	
	public static final String USER_NAME = "SELECT id FROM user WHERE user_name = ?";
	
	public static final String CART_PRODUCT = "SELECT product_name FROM cart WHERE userId = ?";
	
	
}
