package controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import configs.DbConnectionConfig;
import utils.UserHelper;

@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = UserHelper.getGlobalUser();
        String searchquery = request.getParameter("keyword");

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            DbConnectionConfig dbObj = new DbConnectionConfig();
            conn = dbObj.getDbConnection();
            String query = "SELECT * FROM product WHERE product_name LIKE ?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, "%" + searchquery + "%");
            rs = stmt.executeQuery();

            if (rs.next()) {
                // Retrieve product details from the ResultSet
                String productName = rs.getString("product_name");
                double price = rs.getDouble("unit_price"); 
                String category = rs.getString("product_category");
                String productImagePath = rs.getString("product_image");

                // Set product details as attributes in the request object
                request.setAttribute("productName", productName);
                request.setAttribute("unit_price", price);
                request.setAttribute("category", category);
                request.setAttribute("productImagePath", productImagePath);
                
            } else {
                // If no product is found, set an appropriate message
                request.setAttribute("productName", "No product found");
            }

            // Forward the request to the JSP
            request.getRequestDispatcher("./pages/search.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle SQLException
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}