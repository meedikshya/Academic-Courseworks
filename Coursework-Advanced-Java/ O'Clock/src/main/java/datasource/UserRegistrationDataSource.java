/**
 * 
 */
package datasource;

/**
 * 
 */
public class UserRegistrationDataSource {

	/**
	 * 
	 */
	public static final String INSERT_USER = "INSERT INTO user"
			+ "(user_name, Fullname, Gender, Birthday, Phone_Number, Email, Address, Password, profileImage, userRole)"
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	
	public static final String GET_LOGIN_USER_INFO = "SELECT user_name, password FROM user WHERE user_name = ?";

	public static final String GET_USER_ROLE = "SELECT userRole FROM user WHERE user_name = ?";

	public static final String GET_ALL_USER_INFO = "SELECT * FROM user ";
	
	public static final String GET_USER_INFO = "SELECT * FROM user where user_id = ? ";
	
	public static final String GET_USER_DISPLAY = " Select * FROM user where userRole = 'User'";
	
	public static final String GET_USER = "SELECT count(user_name) as user from user where userRole = 'User'";
	
	
	
	public static final String GET_USERNAME = "SELECT COUNT(*) FROM user WHERE user_name = ?"; 
	public static final String GET_PHONE = "SELECT COUNT(*) FROM user  WHERE phone_number = ?"; 
	public static final String GET_EMAIL = "SELECT COUNT(*) FROM user  WHERE email = ?";  
	
	
	
	public static final String  GET_USER_ID = "SELECT id FROM user WHERE user_name = ?";
	public static final String  QUERY_DELETE_USER = "DELETE FROM user WHERE user_id= ?";
	public static final String QUERY_USER = "SELECT * from user WHERE user_id = ?";
	
	public static final String SELECT_USER_BY_ID = "select user_id, Fullname, Gender, Birthday, Phone_Number, Email, Address, Password, profileImage from user where user_id =?";
 
	public static final String UPDATE_USER  = "update user set user_name=?, Fullname=?, Gender=?, Birthday=?, Phone_Number=?, Email=?, Address=?, Password=? where user_id = ?";

	}


