package services;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import configs.DbConnectionConfig;
import datasource.UserRegistrationDataSource;
import models.PasswordEncryptionWithAes;
import models.UserLoginModel;
import models.UserModel;
import utils.StringUtils;

public class UserManagementServices {
	//making the object to create connection with the database
		DbConnectionConfig dbObj = new DbConnectionConfig();
		/**
		 * Establishes a connection to the database using pre-defined credentials and driver information.
		 * 
		 * @return A `Connection` object representing the established connection to the database.
		 * @throws SQLException if a database access error occurs.
		 * @throws ClassNotFoundException if the JDBC driver class is not found.
		 */
		 
		
		 
		  
		//User
	    public ArrayList<UserModel> getAllUsersInfo() {
	        try (Connection con = dbObj.getDbConnection()) {
	            PreparedStatement stmt = con.prepareStatement(UserRegistrationDataSource.GET_USER_DISPLAY);
	            ResultSet result = stmt.executeQuery();
	            ArrayList<UserModel> users = new ArrayList<UserModel>();
	            while (result.next()) {
	            	
	                // Extract user information from the result set
	                UserModel user = new UserModel();
	                
	                user.setUserId(result.getInt("user_id")); // Set userId
	                user.setFullName(result.getString("Fullname"));
	                user.setUserName(result.getString("user_name"));
	                user.setBirthday(result.getDate("Birthday").toLocalDate());
	                user.setGender(result.getString("Gender"));
	                user.setAddress(result.getString("Address"));
	                user.setPhoneNumber(result.getString("Phone_Number"));
	                user.setEmail(result.getString("Email"));
	                user.setPassword(result.getString("Password")); // Set password
	                user.setImageUrlFromDB(result.getString("profileImage"));
	                user.setUserRole(result.getString("UserRole"));
	                
	                users.add(user);
	            }
	            return users;
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	            return null;
	        }
	    }
}