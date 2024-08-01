package services;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import configs.DbConnectionConfig;
import datasource.UserRegistrationDataSource;
import models.PasswordEncryptionWithAes;
import models.UserModel;

public class UserProfileService {
	 //making the object to create connection with the database
		private final static DbConnectionConfig dbObj = new DbConnectionConfig();
		 
		/*
		 * public int updateUserInfo(UserModel user, String updateId) { try (Connection
		 * conn = dbObj.getDbConnection()) { PreparedStatement st =
		 * conn.prepareStatement(UserRegistrationDataSource.UPDATE_USER); st.setInt(1,
		 * user.getUserId()); st.setString(2, user.getUserName()); st.setString(3,
		 * user.getFullName()); st.setString(4, user.getGender()); st.setDate(5,
		 * Date.valueOf(user.getBirthday())); st.setString(6, user.getPhoneNumber());
		 * st.setString(7, user.getEmail()); st.setString(8, user.getAddress());
		 * st.setString(9, PasswordEncryptionWithAes.encrypt(user.getUserName(),
		 * user.getPassword())); // Encrypt the password st.setString(10,
		 * user.getImageUrlFromPart()); st.setString(11, updateId);
		 * 
		 * System.out.println("getUser_id= "+user.getUserId());
		 * 
		 * 
		 * int result = st.executeUpdate();
		 * 
		 * System.out.println("Result= "+result); return result > 0 ? 1: 0; } catch
		 * (SQLException ex) { ex.printStackTrace(); // Log the exception for debugging
		 * return -1; }
		 * 
		 * }
		 */ 
		
			public static UserModel getUserById(int userId) {
	        try (Connection con = dbObj.getDbConnection()) {
	            PreparedStatement stmt = con.prepareStatement(UserRegistrationDataSource.QUERY_USER);
	            stmt.setInt(1, userId);
	            //stmt.setString(2, userLoginModel.getPassword());
	            ResultSet result = stmt.executeQuery();

	            if (result.next()) {
	            	int user_Id =   result.getInt("user_id"); // Set userId
		             String FullName =result.getString("Fullname");
		             String userName = result.getString("user_name");
		           LocalDate Birthday = result.getDate("Birthday").toLocalDate();
		             String Gender = result.getString("Gender");
		             String Address = result.getString("Address");
		             String PhoneNumber = result.getString("Phone_Number");
		             String Email = result.getString("Email");
		             String Password = result.getString("Password"); // Set password
		             String ProfileImage = result.getString("profileImage");
		              
		            UserModel users = new UserModel();
		               users.setUserId(userId);
		               users.setFullName(FullName);  
		               users.setUserName(userName);
		               users.setBirthday(Birthday);
		               users.setGender(Gender);
		               users.setAddress(Address);
		               users.setPhoneNumber(PhoneNumber);
		               users.setEmail(Email);
		               users.setPassword(Password);
		               users.setImageUrlFromDB(ProfileImage);
		               
		               return users;
	                 
	            } else {
	                return null; // Username not found in the database
	            }
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	            return null; // Internal error
	        }
	    }
	
	
	
		 public int deleteUserInfo(int user_id) throws ClassNotFoundException {
				try (Connection con = dbObj.getDbConnection()) {
					PreparedStatement st = con.prepareStatement(UserRegistrationDataSource.QUERY_DELETE_USER);
					st.setInt(1, user_id);
					return st.executeUpdate();
				} catch (SQLException ex) {
					ex.printStackTrace(); // Log the exception for debugging
					return -1;
				}
			}



		public int updateUserInfo(UserModel updatedUser, String updateId) {
			// TODO Auto-generated method stub
			return 0;
		} 
}
