package com.gamify.impl;

import java.security.Key;
import java.util.ArrayList;
import java.util.List;



import com.gamify.interf.InterfaceAuth;
import com.gamify.model.User;


import io.jsonwebtoken.impl.crypto.MacProvider;

public class AuthManager implements InterfaceAuth {
	
	static List<User> users = new ArrayList<User>();
	
	
	
	static Key key;
	
	static AuthManager am = null;
	
	public static AuthManager getInstance() {
		if(am == null) {
			am = new AuthManager();	
			key = MacProvider.generateKey();
			
			
			User u1 = new User("user1", "123", "mail");
			User u2 = new User("user2", "1234", "mail2");
			
			users.add(u1);
			users.add(u2);
		}
		return am;
	}
	
	public Key getKey() {
		return key;
	}
	public boolean getAuth(String username,String password) {
		for(User user:users) {
			if(user.getUserID().equals(username)) {
				if (user.getPassword().equals(password)) {
					return true;
				}
			}
		}
		return false;
		
	}
}