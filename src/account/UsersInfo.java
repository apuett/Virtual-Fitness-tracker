package account;

import java.util.ArrayList;
import java.util.Calendar;

import application.Main;

public class UsersInfo {
	
	public static ArrayList<User> Users = new ArrayList<User>();
	
	public static void addUser(String UserName, String Password) {
		Calendar createDate=Calendar.getInstance();
		createDate.set(Calendar.MILLISECOND, 0);
        createDate.set(Calendar.SECOND, 0);
        createDate.set(Calendar.MINUTE, 0);
        createDate.set(Calendar.HOUR_OF_DAY, 0);
		User tempUser = new User(UserName,Main.encrypt(Password),createDate);
		Users.add(tempUser);
	}
	
	
	
	public static boolean UserExist(String userName) {
		boolean exist=false;
		for(User temp:Users) {
			if(userName.equals(temp.getUserName()))exist=true;
		}
		return exist;
	}
	
	public static User getUserByUsername(String userName) {
		User tempUser = null;
			for(User temp:Users) {
				if(userName.equals(temp.getUserName()))tempUser=temp;
			}
		return tempUser;
	}
	
	public static boolean ValidChar(String str) {
		char[] validChars= {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z',
							'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',
							'1','2','3','4','5','6','7','8','9','0','!','(',')','-','.','?','[',']','_','~',';',':','@','#','$','%',
							'^','&','*','+','='};
		int intValidChars=0;
		for(var i=0;i<str.length();i++) {
			for(char k:validChars) {
				if(k==str.charAt(i))intValidChars++;
			}
		}
		if(str.length()==intValidChars)return true;
		else return false;
	}
}
