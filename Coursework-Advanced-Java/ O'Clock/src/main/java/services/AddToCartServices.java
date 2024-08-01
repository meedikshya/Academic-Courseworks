package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import configs.DbConnectionConfig;
import datasource.AddToCartDataSource;
import models.AddToCartModel;
import models.UserProductModel;

public class AddToCartServices {

    private final static DbConnectionConfig dbObj = new DbConnectionConfig();

    public static int addProduct(AddToCartModel cart) throws ClassNotFoundException {
        try (Connection conn = dbObj.getDbConnection();
             PreparedStatement st = conn.prepareStatement(AddToCartDataSource.INSERT_CART)) {
            st.setString(1, cart.getUsername());
            st.setInt(2, cart.getProductId());
            int result = st.executeUpdate();
            return result > 0 ? 1 : 0;
        } catch (SQLException ex) {
            System.err.println("Error adding product: " + ex.getMessage());
            return -1;
        }
    }

    public static List<UserProductModel> getAllCartProducts(String username) throws ClassNotFoundException {
        List<UserProductModel> productList = new ArrayList<>();

        try (Connection conn = dbObj.getDbConnection()) {
            PreparedStatement st = conn.prepareStatement(AddToCartDataSource.SELECT_CART);
            st.setString(1, username);
            ResultSet result = st.executeQuery();

            while (result.next()) {
                int productId = result.getInt("productId");
                int cartId = result.getInt("cartId");

                AddToCartModel prod = new AddToCartModel();
                prod.setId(cartId);
                prod.setProductId(productId);

                PreparedStatement st2 = conn.prepareStatement(AddToCartDataSource.SELECT_CART_PRODUCT);
                st2.setInt(1, productId);
                ResultSet result2 = st2.executeQuery();

                if (result2.next()) {
                    String productName = result2.getString("product_name");
                    String categoryName = result2.getString("product_category");
                    double price = result2.getDouble("unit_price");
                    String imageUrl = result2.getString("product_image");

                    // Check if the product already exists in the productList
                    boolean found = false;
                    for (UserProductModel existingProduct : productList) {
                        if (existingProduct.getId() == productId) {
                            // Product already exists, increase its quantity
                            existingProduct.setQuantity(existingProduct.getQuantity() + 1);
                            found = true;
                            break;
                        }
                    }

                    if (!found) {
                        // Product does not exist in the productList, add it
                        UserProductModel product = new UserProductModel();
                        product.setName(productName);
                        product.setCategory(categoryName);
                        product.setPrice(price);
                        product.setQuantity(1); // Set the quantity to 1 by default
                        product.setImageUrlFromDB(imageUrl);
                        product.setId(productId);
                        productList.add(product);
                    }
                }
            }
            return productList;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    
    public static int deleteProduct(String username, int productId) throws ClassNotFoundException {
        try (Connection conn = dbObj.getDbConnection();
             PreparedStatement st = conn.prepareStatement(AddToCartDataSource.DELETE_CART_PRODUCTS)) {
            st.setString(1, username);
            st.setInt(2, productId);
            int result = st.executeUpdate();
            return result > 0 ? 1 : 0;
        } catch (SQLException ex) {
            System.err.println("Error deleting product: " + ex.getMessage());
            return -1;
        }
    }

}