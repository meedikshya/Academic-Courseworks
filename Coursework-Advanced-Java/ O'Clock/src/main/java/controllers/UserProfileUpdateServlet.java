
package controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import configs.DbConnectionConfig;
import datasource.UserRegistrationDataSource;
import models.ProductModel;
import models.UserModel;
import services.ProductServices;
import services.UserProfileService;
import utils.StringUtils;
import utils.StringUtilsProduct;
import utils.UserHelper;

@WebServlet(asyncSupported = true, urlPatterns = StringUtils.SERVLET_URL_USER_PROFILE_UPDATE)
public class UserProfileUpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final UserProfileService userProfileService;

    public UserProfileUpdateServlet() {
        super();
        this.userProfileService = new UserProfileService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = UserHelper.getGlobalUser();
        String userIdParam = request.getParameter("userId");

        Connection conn = null;
        PreparedStatement userStmt = null;
        ResultSet userRs = null;

        try {
            DbConnectionConfig dbObj = new DbConnectionConfig();
            conn = dbObj.getDbConnection();
            String userQuery = UserRegistrationDataSource.GET_USER_INFO;
            userStmt = conn.prepareStatement(userQuery);
            userStmt.setInt(1, Integer.parseInt(userIdParam));
            userRs = userStmt.executeQuery();

            if (userRs.next()) {
                int userId = userRs.getInt("user_id");
                String fullName = userRs.getString("Fullname");
                String gender = userRs.getString("Gender");
                LocalDate birthday = userRs.getDate("Birthday").toLocalDate();
                String phoneNumber = userRs.getString("Phone_Number");
                String email = userRs.getString("Email");
                String address = userRs.getString("Address");
                String password = userRs.getString("Password");
                String imageUrl = userRs.getString("profileImage");

                request.setAttribute("userId", userId);
                request.setAttribute("fullName", fullName);
                request.setAttribute("gender", gender);
                request.setAttribute("birthday", birthday);
                request.setAttribute("phoneNumber", phoneNumber);
                request.setAttribute("email", email);
                request.setAttribute("address", address);
                request.setAttribute("password", password);
                request.setAttribute("profileImage", imageUrl);
            } else {
                request.setAttribute("userName", "No user found");
            }

            request.getRequestDispatcher("./pages/userProfile.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (userRs != null) userRs.close();
                if (userStmt != null) userStmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String updateId = request.getParameter("userId");
        updateUserProfile(request, response, updateId);
    }

    private void updateUserProfile(HttpServletRequest request, HttpServletResponse response, String updateId) throws ServletException, IOException {
        try {
            int userId = Integer.parseInt(updateId);
            String username = request.getParameter(StringUtils.USERNAME);
            String fullName = request.getParameter(StringUtils.FULL_NAME);
            String gender = request.getParameter(StringUtils.GENDER);
            LocalDate birthday = LocalDate.parse(request.getParameter(StringUtils.BIRTHDAY));
            String phoneNumber = request.getParameter(StringUtils.PHONE_NUMBER);
            String email = request.getParameter(StringUtils.EMAIL);
            String address = request.getParameter(StringUtils.ADDRESS);
            String password = request.getParameter(StringUtils.PASSWORD);
            Part imagePart = request.getPart(StringUtils.PROFILE_IMAGE);

            String imageUrlFromPart = ""; // Initialize imageUrlFromPart

            if (imagePart != null) {
                imageUrlFromPart = new UserModel().getImageUrl(imagePart);
            }
            UserModel updatedUser = new UserModel();
            updatedUser.setUserId(userId);
            updatedUser.setUserName(username);
            updatedUser.setFullName(fullName);
            updatedUser.setGender(gender);
            updatedUser.setBirthday(birthday);
            updatedUser.setPhoneNumber(phoneNumber);
            updatedUser.setEmail(email);
            updatedUser.setAddress(address);
            updatedUser.setPassword(password);
            updatedUser.setImageUrlFromDB(imageUrlFromPart);

            int result = userProfileService.updateUserInfo(updatedUser, updateId);
            if (result == 1) {
                response.sendRedirect(request.getContextPath() + StringUtils.URL_INDEX);
            } else {
                response.sendRedirect(request.getContextPath() + StringUtils.URL_INDEX);
            }
        } catch (NumberFormatException ex) {
            response.sendRedirect(request.getContextPath() + StringUtils.URL_INDEX);
        }
    }
    
    //updating
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	System.out.println("put triggered");
    	String userId = req.getParameter(StringUtilsProduct.UPDATE_ID);
    	 ProductModel products = ProductServices.getProductByName(userId);

    	req.setAttribute("user", products);
    	req.getRequestDispatcher("/pages/modifyPage.jsp").forward(req, resp);

    }

}
