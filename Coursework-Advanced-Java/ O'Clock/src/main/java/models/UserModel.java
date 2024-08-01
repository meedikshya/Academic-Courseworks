package models;

import java.time.LocalDate; // Import LocalDate class

import javax.servlet.http.Part;
import java.io.File;

import utils.StringUtils;

public class UserModel {
	 private int userId;
    private String fullName;
    private String email;
    private String address;
    private LocalDate birthday;
    private String userName;
    private String gender;
    private String phoneNumber;
    private String password;
    private String  imageUrlFromPart;
    private String userRole;
     
    public UserModel() {
		// TODO Auto-generated constructor stub
	}
    
    public UserModel(String userName,String fullName,String gender, LocalDate birthday,String phoneNumber,String email,String address, String password,Part imagePart, String userRole) {
        super();
    	this.fullName = fullName;
        this.email= email;
        this.address = address;
        this.birthday = birthday;
        this.userName = userName;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.imageUrlFromPart = getImageUrl(imagePart);
        this.userRole = userRole;
    }
    
   
    
	

    public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	

	// Getter to read firstName
    public String getUserName() {
        return this.userName;
    }
    
    // Setter to set firstName
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    // Getter to read firstName
    public String getFullName() {
        return this.fullName;
    }
    
    
    // Setter to set firstName
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

 
    
    // Getter to read dob
    public LocalDate getBirthday() {
        return this.birthday;
    }
            
    // Setter to set dob
    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }
    
   
    
    // Getter to read email
    public String getEmail() {
        return this.email;
    }
                        
    // Setter to set email
    public void setEmail(String email) {
        this.email = email;
    }

    // Getter to read email
    public String getGender() {
        return this.gender;
    }
                        
    // Setter to set email
    public void setGender(String gender) {
        this.gender = gender;
    }

    
    // Getter to read email
    public String getPhoneNumber() {
        return this.phoneNumber;
    }
                        
    // Setter to set email
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    } 
    
    // Getter to readaddress
    public String getAddress() {
        return this.address;
    }
                        
    // Setter to set email
    public void setAddress(String address) {
        this.address = address;
    } 
    
    // Getter to read Password
    public String getPassword() {
        return this.password;
    }
                                
    // Setter to set password
    public void setPassword(String password) {
        this.password = password;
    }
    
    
    
 // Getter to read Password
    public String getUserRole() {
        return this.userRole;
    }
                                
    // Setter to set password
    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }
    
    public String getImageUrlFromPart() {
  		return imageUrlFromPart;
  	}
     
    public void setImageUrlFromPart(Part part) {
        this.imageUrlFromPart = getImageUrl(part);
    }

    
	
    public void setImageUrlFromDB(String imageUrl) {
		this.imageUrlFromPart = imageUrl;
}

    public String getImageUrl(Part part) {
		// Define the directory path to store uploaded user images. This path should be configured elsewhere in the application.
	    String savePath = StringUtils.IMAGE_DIR_USER;

	    // Create a File object representing the directory to store uploaded images.
	    File fileSaveDir = new File(savePath);

	    // Initialize the variable to store the extracted image file name.
	    String imageUrlFromPart = null;

	    // Check if the directory to store uploaded images already exists.
	    if (!fileSaveDir.exists()) {
	        // If the directory doesn't exist, create it.
	    	// user mkdirs() method to create multiple or nested folder
	        fileSaveDir.mkdir();
	    }

	    // Get the Content-Disposition header from the request part. This header contains information about the uploaded file, including its filename.
	    String contentDisp = part.getHeader("content-disposition");

	    // Split the Content-Disposition header into individual parts based on semicolons.
	    String[] items = contentDisp.split(";");

	    // Iterate through each part of the Content-Disposition header.
	    for (String s : items) {
	        // Check if the current part starts with "filename" (case-insensitive trim is used).
	        if (s.trim().startsWith("filename")) {
	            // Extract the filename from the current part.
	            // The filename is assumed to be enclosed in double quotes (").
	            // This part removes everything before the equal sign (=) and the double quotes surrounding the filename.
	            imageUrlFromPart = s.substring(s.indexOf("=") + 2, s.length() - 1);
	            break; // Exit the loop after finding the filename
	        }
	    }

	    // If no filename was extracted from the Content-Disposition header, set a default filename.
	    if (imageUrlFromPart == null || imageUrlFromPart.isEmpty()) {
	        imageUrlFromPart = "default_user.jpg";
	    }

	    // Return the extracted or default image file name.
	    return imageUrlFromPart;
	} 
      
}
