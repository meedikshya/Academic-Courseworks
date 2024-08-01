package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import configs.DbConnectionConfig;
import datasource.UserProductDataSource;
import models.UserProductModel;

public class UserProductServices {
static DbConnectionConfig dbObj = new DbConnectionConfig();
	

    public static List<UserProductModel> getAllProducts() throws ClassNotFoundException {
    	
        List<UserProductModel> productList = new ArrayList<UserProductModel>();
        
    	try (Connection conn = dbObj.getDbConnection()){
             PreparedStatement st = conn.prepareStatement(UserProductDataSource.GET_ALL_PRODUCTS);
             ResultSet result = st.executeQuery();

            while (result.next()) {
            	
            	String productName = result.getString("product_name");
	            String categoryName = result.getString("product_category");
	            double price = result.getDouble("unit_price");
	            int quantity = result.getInt("stockQuantity");
	            String imageUrl = result.getString("product_image");
	            int productId = result.getInt("product_id");
                
                //System.out.println("Retrieved product: " + name); 
                
	            UserProductModel prod = new UserProductModel();
	            prod.setName(productName);
	            prod.setCategory(categoryName);
	            prod.setPrice(price);
	            prod.setQuantity(quantity);
	            prod.setImageUrlFromDB(imageUrl);
	            prod.setId(productId);
	            
	             
                productList.add(prod);
            }
            return productList;
            
        } catch (SQLException ex) {
        	
        	ex.printStackTrace();
        	return null;
        	
        }
    }
   
}