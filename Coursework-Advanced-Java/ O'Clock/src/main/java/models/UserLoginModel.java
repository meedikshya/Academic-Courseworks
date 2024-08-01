package models;

public class UserLoginModel {
    private String userName;
    private String password;
    private String userRole;

    public UserLoginModel(String userName, String password) {
        this.userName = userName;
         
        this.password = password;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

     
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    //Getter to get and set userrole
    public String getUserRole() {
        return this.userRole;
    }
                                
    // Setter to set password
    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }
}
