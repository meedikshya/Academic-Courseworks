package controllers;

import java.io.IOException;

import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import models.UserModel;
import services.UserServices;
import utils.StringUtils;
import utils.ValidationUtils;

/**
 * Servlet implementation class RegisterUserServlet
 * 
 * @author Binita bhandari(binitabhandari2003@gmail.com)
 */
@WebServlet(asyncSupported = true, urlPatterns = { StringUtils.SERVLET_URL_REGISTER })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
		maxFileSize = 1024 * 1024 * 10, // 10MB
		maxRequestSize = 1024 * 1024 * 50)
public class RegisterUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final UserServices userServices;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterUserServlet() {
		this.userServices = new UserServices();// Initialize UserServices object
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Extract user information from request parameters
		String username = request.getParameter(StringUtils.USERNAME);
		String fullName = request.getParameter(StringUtils.FULL_NAME);
		String gender = request.getParameter(StringUtils.GENDER);
		LocalDate birthday = LocalDate.parse(request.getParameter(StringUtils.BIRTHDAY));
		String phoneNumber = request.getParameter(StringUtils.PHONE_NUMBER);
		String email = request.getParameter(StringUtils.EMAIL);
		String address = request.getParameter(StringUtils.ADDRESS);
		String password = request.getParameter(StringUtils.PASSWORD);
		String confirmPassword = request.getParameter(StringUtils.RETYPE_PASSWORD);
		String userRole = request.getParameter(StringUtils.USER_ROLE);
		Part imagePart = request.getPart(StringUtils.PROFILE_IMAGE);

		// Validate if password and confirm password match
		if (!password.equals(confirmPassword)) {
			request.setAttribute(StringUtils.MESSAGE_ERROR, StringUtils.MESSAGE_ERROR_PASSWORD_UNMATCHED);
			request.getRequestDispatcher(StringUtils.PAGE_URL_REGISTER).forward(request, response);
			return;
		}

		// Implement data validation for all fields
		if (!ValidationUtils.isTextOnly(fullName) || !ValidationUtils.isAlphanumeric(username)
				|| !ValidationUtils.isEmail(email) || !ValidationUtils.isNumbersOnly(phoneNumber)
				|| !ValidationUtils.isGenderMatches(gender) || !ValidationUtils.isTextOnly(address)) {
			request.setAttribute(StringUtils.MESSAGE_ERROR, StringUtils.MESSAGE_ERROR_INCORRECT_DATA);
			request.getRequestDispatcher(StringUtils.PAGE_URL_REGISTER).forward(request, response);
			return; // Stop further execution
		}

		// Call UserService to register the user with Create a UserModel object to hold
		// user information
		UserModel user = new UserModel(username,fullName,gender, birthday, phoneNumber, email,  address, password, imagePart,  userRole);
		
		/*
		 * user.setUserName(username); user.setFullName(fullName);
		 * user.setGender(gender); user.setDob(birthday);
		 * user.setPhoneNumber(phoneNumber); user.setEmail(email);
		 * user.setAddress(address); user.setPassword(password);
		 * user.setImageUrlFromPart(imagePart); user.setUserRole(userRole);
		 */
		 

		// Call UserService to register the user
		int result = userServices.getUserRegisterInfo(user);
		System.out.println(result);
		if (result == 1) {
			// file upload
			// Get the image file name from the student object (assuming it was extracted
			// earlier)
			String fileName = user.getImageUrlFromPart();

			// Check if a filename exists (not empty or null)
			if (!fileName.isEmpty() && fileName != null) {
				// Construct the full image save path by combining the directory path and
				// filename
				String savePath = StringUtils.IMAGE_DIR_USER;
				imagePart.write(savePath + fileName); // Save the uploaded image to the specified path
			}

			request.setAttribute(StringUtils.MESSAGE_SUCCESS, StringUtils.MESSAGE_SUCCESS_REGISTER);
			response.sendRedirect(request.getContextPath() + StringUtils.PAGE_URL_LOGIN + "?success=true");

		} else if (result == 0) {
			request.setAttribute(StringUtils.MESSAGE_ERROR, StringUtils.MESSAGE_ERROR_REGISTER);
			request.getRequestDispatcher(StringUtils.PAGE_URL_REGISTER).forward(request, response);
		} else {
			request.setAttribute(StringUtils.MESSAGE_ERROR, StringUtils.MESSAGE_ERROR_SERVER);
			request.getRequestDispatcher(StringUtils.PAGE_URL_REGISTER).forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}