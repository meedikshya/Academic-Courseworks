package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import configs.DbConnectionConfig;
import datasource.ProductDataSource;
import models.ProductModel;

public class ProductServices {

	//making the object to create connection with the database
		private final static DbConnectionConfig dbObj = new DbConnectionConfig();
		  
		public int addProduct(ProductModel product) throws ClassNotFoundException {
        try (Connection conn = dbObj.getDbConnection();
             PreparedStatement st = conn.prepareStatement(ProductDataSource.INSERT_PRODUCT)) {
//        	st.setInt(1, product.getId());
            st.setString(1, product.getName());
            st.setString(2, product.getCategory());
            st.setDouble(3, product.getPrice());
            st.setInt(4, product.getQuantity());
            st.setString(5, product.getImageUrlFromPart());

            int result = st.executeUpdate();
            return result > 0 ? 1 : 0;
        } catch (SQLException ex) {
            System.err.println("Error adding product: " + ex.getMessage());
            return -1;
        }
    }
    
    public List<ProductModel> getAllProducts() throws ClassNotFoundException {
        List<ProductModel> productList = new ArrayList<>();
        try (Connection conn = dbObj.getDbConnection();
             PreparedStatement st = conn.prepareStatement(ProductDataSource.SELECT_ALL_PRODUCTS);
             ResultSet rs = st.executeQuery()) {

            while (rs.next()) {
//            	int id = rs.getInt("product_id");
                String name = rs.getString("product_name");
                String category = rs.getString("product_category");
                double price = rs.getDouble("unit_price");
                int quantity = rs.getInt("stockQuantity");
                String imageUrl = rs.getString("product_image");
                
                System.out.println("Retrieved product: " + name); 
                
                ProductModel product = new ProductModel(name, category, price, quantity, imageUrl);
                productList.add(product);
            }
            System.out.println("List of products: " + productList.size());
        } catch (SQLException ex) {
            System.err.println("Error retrieving products: " + ex.getMessage());
        }
        return productList;
    }
    
    public int updateProductInfo(ProductModel product, String updateId) {
    	 try (Connection conn = dbObj.getDbConnection()) {
			PreparedStatement st = conn.prepareStatement(ProductDataSource.UPDATE_PRODUCTS);
//			st.setString(1, product.getProduct_id());
			
			 st.setString(1, product.getName());
	            st.setString(2, product.getCategory());
	            st.setDouble(3, product.getPrice());
	            st.setInt   (4, product.getQuantity());
	            st.setString(5, updateId);
			
			System.out.println("getProduct_name= "+product.getName());
			
			
			int result = st.executeUpdate();
			
			System.out.println("Result= "+result);
			return result > 0 ? 1: 0;
		} catch (SQLException ex) {
			ex.printStackTrace(); // Log the exception for debugging
			return -1;
		}
    	 
	}public static ProductModel getProductByName(String name) {
	    try {
	        PreparedStatement stmt = dbObj.getDbConnection()
	                .prepareStatement("SELECT * from product WHERE product_name = ?");
	        stmt.setString(1, name);
	        ResultSet result = stmt.executeQuery();

	        if (result.next()) {
	        	//text field ma aako name haruuuuuuu
	            String productName = result.getString("product_name");
	            String categoryName = result.getString("product_category");
	            double price = result.getDouble("unit_price");
	            int quantity = result.getInt("stockQuantity");

	            ProductModel prod = new ProductModel();
	            prod.setName(productName);
	            prod.setCategory(categoryName);
	            prod.setPrice(price);
	            prod.setQuantity(quantity);
	            
	            return prod;
	        } else {
	            return null; // Product with given name not found
	        }
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	        return null;
	    }
	}
	
	// Method to check if a product name is unique
    public boolean isProductNameUnique(String productName) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            DbConnectionConfig dbObj = new DbConnectionConfig();
            conn = dbObj.getDbConnection();
            
            // Query to check if the product name already exists in the database
            String query = "SELECT COUNT(*) FROM product WHERE product_name = ?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, productName);
            rs = stmt.executeQuery();
            
            // If count is greater than 0, it means the product name already exists
            if (rs.next()) {
                int count = rs.getInt(1);
                return count == 0; // Return true if count is 0 (product name is unique)
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        // Default to true if an error occurs
        return true;
    }


    public static int deleteProduct(String name) throws ClassNotFoundException {
        try (Connection con = dbObj.getDbConnection();
             PreparedStatement st = con.prepareStatement(ProductDataSource.QUERY_DELETE_PRODUCTS)) {
			st.setString(1, name);
            return st.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Error deleting product: " + ex.getMessage());
            return -1;
        }
    }
}