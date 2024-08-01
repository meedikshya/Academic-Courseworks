package services;

import java.sql.Connection;



import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import configs.DbConnectionConfig;
import datasource.UserRegistrationDataSource;
import models.UserLoginModel;
import models.UserModel;
import models.PasswordEncryptionWithAes;
import utils.StringUtils;

public class UserServices {
	//making the object to create connection with the database
	DbConnectionConfig dbObj = new DbConnectionConfig();
	 

	public int getUserRegisterInfo(UserModel user) {
	    try (Connection conn = dbObj.getDbConnection()){
	        // Prepare a statement using the predefined query for user registration
	        PreparedStatement stmt = conn.prepareStatement(UserRegistrationDataSource.INSERT_USER);

	        // Set the user information in the prepared statement
	        stmt.setString(1, user.getUserName());  
	        stmt.setString(2, user.getFullName());  
	        stmt.setString(3, user.getGender());
	        stmt.setDate(4, Date.valueOf(user.getBirthday()));
	        stmt.setString(5, user.getPhoneNumber());
	        stmt.setString(6, user.getEmail());
	        stmt.setString(7, user.getAddress());
	        stmt.setString(8, PasswordEncryptionWithAes.encrypt(user.getUserName(), user.getPassword())); // Encrypt the password
	        stmt.setString(9, user.getImageUrlFromPart());
	        stmt.setString(10, user.getUserRole() !=null ? user.getUserRole() : StringUtils.DEFAULT_USER_ROLE);
	        
	        // Execute the update statement and store the number of affected rows
	        int result = stmt.executeUpdate();

	        // Check if the update was successful (i.e., at least one row affected)
	        if (result > 0) {
	            return 1; // Registration successful
	        } else {
	            return 0; // Registration failed (no rows affected)
	        }
	    } catch (SQLException ex) {
	        // Print the stack trace for debugging purposes
	        ex.printStackTrace();
	        return -1; // Internal error
	    }
	}

    /**
	 * This method attempts to validate a student login by checking the username 
	 * and password against a database.
	 * 
	 * @param username The username provided by the user attempting to log in.
	 * @param password The password provided by the user attempting to log in.
	 * @return An integer value indicating the login status:
	 *         - 1: Login successful
	 *         - 0: Username or password mismatch
	 *         - -1: Username not found in the database
	 *         - -2: Internal error (e.g., SQL or ClassNotFound exceptions)
	 * @throws SQLException if a database access error occurs.
	 * @throws ClassNotFoundException if the JDBC driver class is not found.
	 */
    public int getUserLoginInfo(UserLoginModel userLoginModel) {
        try (Connection con = dbObj.getDbConnection()) {
            PreparedStatement stmt = con.prepareStatement(UserRegistrationDataSource.GET_LOGIN_USER_INFO);
            stmt.setString(1, userLoginModel.getUserName());
            //stmt.setString(2, userLoginModel.getPassword());
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String userDb = rs.getString(StringUtils.USER_NAME);
                String encryptedPwd = rs.getString(StringUtils.PASSWORD);
                String decryptedPwd = PasswordEncryptionWithAes.decrypt(encryptedPwd, userDb);

                if (userDb.equals(userLoginModel.getUserName()) && decryptedPwd.equals(userLoginModel.getPassword())) {
                    return 1; // Login Successful
                } else {
                    return 0; // Username or password mismatch
                }
            } else {
                return -1; // Username not found in the database
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return -2; // Internal error
        }
    }

    // UserRole 
    public int getUserRole(UserLoginModel userLoginModel) {
        // Try-catch block to handle potential SQL or ClassNotFound exceptions
        try (Connection con = dbObj.getDbConnection()) {
            PreparedStatement stmt = con.prepareStatement(UserRegistrationDataSource.GET_USER_ROLE);
        
            // Set the username in the first parameter of the prepared statement
            stmt.setString(1, userLoginModel.getUserName());
            ResultSet rs = stmt.executeQuery();
 
            // Check if there's a record returned from the query
            if (rs.next()) {
                // Get the user role from the database
                String userRole = rs.getString(StringUtils.USER_ROLE);

                // Return 1 if the user role matches the admin role, otherwise return 0
                return userRole.equals("Admin") ? 1 : 0;
            } else {
                // Username not found in the database, return -1
                return -1;
            }

            // Catch SQLException and ClassNotFoundException if they occur
        } catch (SQLException ex) {
            // Print the stack trace for debugging purposes
            ex.printStackTrace();
            // Return -2 to indicate an internal error
            return -2;
        }
    }
    
    public Boolean checkEmailIfExists(String email) {
        try (Connection con = dbObj.getDbConnection()) {
            PreparedStatement st = con.prepareStatement(UserRegistrationDataSource.GET_EMAIL);
            st.setString(1, email);
            ResultSet rs = st.executeQuery();
            return rs.next();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public Boolean checkNumberIfExists(String phoneNumber) {
        try (Connection con = dbObj.getDbConnection()) {
            PreparedStatement st = con.prepareStatement(UserRegistrationDataSource.GET_PHONE);
            st.setString(1, phoneNumber);
            ResultSet rs = st.executeQuery();
            return rs.next();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public Boolean checkUsernameIfExists(String userName) {
        try (Connection con = dbObj.getDbConnection()) {
            PreparedStatement st = con.prepareStatement(UserRegistrationDataSource.GET_USERNAME);
            st.setString(1, userName);
            ResultSet rs = st.executeQuery();
            return rs.next();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public Boolean checkPasswordMatch(String password, String retypePassword) {
        return password.equals(retypePassword);
    }

    

    public ArrayList<UserModel> getAllUsersInfo() {
        try (Connection con = dbObj.getDbConnection()) {
            PreparedStatement stmt = con.prepareStatement(UserRegistrationDataSource.GET_ALL_USER_INFO);
            ResultSet result = stmt.executeQuery();
            ArrayList<UserModel> users = new ArrayList<>();
            while (result.next()) {
                // Extract user information from the result set
                UserModel user = new UserModel();
                user.setUserName(result.getString("user_name"));
                user.setFullName(result.getString("Fullname"));
                user.setGender(result.getString("Gender"));
                user.setBirthday(result.getDate("Birthday").toLocalDate());
                user.setPhoneNumber(result.getString("Phone_Number"));
                user.setEmail(result.getString("Email")); 
				user.setAddress(result.getString("Address"));
				user.setImageUrlFromDB(result.getString("profileImage"));
				user.setUserRole(result.getString("UserRole"));
                
                // Set other attributes accordingly if needed
                users.add(user);
            }
            return users;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
  
}