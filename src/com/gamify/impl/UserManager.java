package com.gamify.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ws.rs.core.Response;

import com.gamify.data.ErrorData;
import com.gamify.data.UserData;
import com.gamify.interf.InterfaceUser;
import com.gamify.model.App;
import com.gamify.model.User;

public class UserManager implements InterfaceUser {

	static UserManager um = null;

	public static UserManager getInstance() {
		if (um == null) {
			um = new UserManager();
		}
		return um;
	}

	// Create new user

	@Override
	public Object createUser(String userID, String password, String email) {

		User user = new User(userID, password, email);
		UserData userData = UserData.getInstance();
		userData.insertData(user);
		// The user is created with success
		return Response.ok().entity(userID + " created!").build();
	}

	// Get all users

	@Override
	public List<User> getUsers(String userAuth) {
		if (userAuth != null) {
			UserData userData = UserData.getInstance();
			return userData.getData();
		} else {
			// Invalid login
			ErrorData errorData = ErrorData.getInstance();
			errorData.getData("5");
		}
		return null;
	}

	// Get specific user

	@Override
	public List<User> getUser(String userID, String userAuth) {
		
		if (userAuth != null) {
			UserData userData = UserData.getInstance();
			return userData.getData(userID);
		} else {
			// Invalid login
			ErrorData errorData = ErrorData.getInstance();
			errorData.getData("5");
		}
		return null;
	}

	// Change user

	@Override
	public Object changeUser(String userID, String newPassword, String newEmail, String userAuth) {

		if (userID.equals(userAuth)) {
			UserData userData = UserData.getInstance();
			userData.changeData(userID, newPassword, newEmail);
			// The user is changed with success
			return Response.ok().entity(userID + " changed!").build();
		} else {
			// The current user is not authorized to change another user
			ErrorData errorData = ErrorData.getInstance();
			return errorData.getData("3");
		}

	}

	// Remove user

	@Override
	public Object removeUser(String userID, String userAuth) {

		if (userID.equals(userAuth)) {
			UserData userData = UserData.getInstance();
			userData.removeData(userID);
			// The user is removed with success
			return Response.ok().entity(userID + " removed!").build();
		} else {
			// The current user is not authorized to remove another user
			ErrorData errorData = ErrorData.getInstance();
			return errorData.getData("3");
		}

	}

}
