package controllers;

import models.User;
import controllers.Secure.Security;

public class Login extends Security {
	 public static boolean authenticate(String username, String password) {
		 	boolean allow=false;
	        User user = User.find("username=?", username).first();
	        if(user != null && user.password.equals(password)){
	        	allow=true;
		        session.put("username", user.username);	
	        }
	        return allow;
	    }
	 public static boolean check(String profile) {
		 boolean allow=false;;
		 String[] str=profile.split(";");
	        for(int i=0;i<str.length;i++){  	        	
	        	if(Home.getNama().level_id.namalevel.equals(str[i])){
	        		allow=true;
	        	}               	      		
	        }
	     return allow;
	 }
}
