package utils;

import java.io.File;

import javax.servlet.http.Part;

public class UserHelper {
	
public static String globalUser;

public static String getGlobalUser() {
	return globalUser;
}

public static void setGlobalUser(String globalUser) {
	UserHelper.globalUser = globalUser;
}

}
