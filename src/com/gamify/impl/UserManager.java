package com.gamify.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.gamify.data.UserData;
import com.gamify.interf.InterfaceUser;
import com.gamify.model.App;
import com.gamify.model.User;

public class UserManager implements InterfaceUser {

	String userAuth = "joaorsantos"; // To change when add auth (token)

	static List<User> users = new ArrayList<User>();

	static UserManager um = null;

	public static UserManager getInstance() {
		if(um == null) {
			um = new UserManager();
		}
		return um;
	}

	// Create new user

	@Override
	public void createUser(String userID, String password, String email) {
		User user = new User(userID, password, email);
		
		UserData userData = UserData.getInstance();				
		userData.insertUser(user);
	}

	// Get all users

	@Override
	public List<User> getUsers() {
		UserData userData = UserData.getInstance();				
		return userData.getData();
	}

	// Get specific user

	@Override
	public List<User> getUser(String userID) {
		UserData userData = UserData.getInstance();				
		return userData.getData(userID);
	}

	// Change user

	@Override
	public void changeUser(String userID, String newPassword, String newEmail) {

		if (userID.equals(userAuth)) {
			UserData userData = UserData.getInstance();				
			userData.changeData(userID, newPassword, newEmail);
		}
		else {
			// The user is not authorized to change another user - TO DO: Send error;
		}

	}

	// Remove user

	@Override
	public void removeUser(String userID) {


		if (userID.equals(userAuth)) {
			UserData userData = UserData.getInstance();				
			userData.removeData(userID);
		}
		else {
			// The user is not authorized to remove another user - TO DO: Send error	
		}

	}

}

