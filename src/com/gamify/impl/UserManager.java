package com.gamify.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
			User u1 = new User("joaorsantos", "xpto", "joaorsantos@gamify.pt"); // To remove when add MongoDB
			User u2 = new User("rcosta", "12345", "rcosta@gamify.pt"); // To remove when add MongoDB
			users.add(u1); // To remove when add MongoDB
			users.add(u2); // To remove when add MongoDB
		}
		return um;
	}

	// Create new user

	@Override
	public void createUser(String userID, String password, String email) {
		User u = new User(userID, password, email);
		users.add(u);
	}

	// Get all users

	@Override
	public List<User> getUsers() {
		List<User> filteredUsers = new ArrayList<User>();
		for(User user:users) {
			User fu = new User(user.getUserID(), user.getEmail());
			filteredUsers.add(fu);
		}
		if (filteredUsers.size() != 0) {
			return filteredUsers;
		}
		else {
			// There are no users - TO DO: Send error
			return null;
		}
	}

	// Get specific user

	@Override
	public User getUser(String userID) {
		for(User user:users) {
			if(user.getUserID().equals(userID)) {
				User fu = new User(user.getUserID(), user.getEmail());
				return fu;
			}
		}
		// There are no user with that ID - TO DO: Send error
		return null;

	}

	// Change user

	@Override
	public void changeUser(String userID, String newPassword, String newEmail) {

		boolean permission;
		boolean exists = false;

		if (userID.equals(userAuth)) {
			permission = true;
		}
		else {
			permission = false;
		}

		if (permission == true) {
			for(User user:users) {
				if (user.getUserID().equals(userID) ) {
					exists = true;
					User newUser = new User(userID, newPassword, newEmail);
					int i = users.indexOf(user);
					users.set(i, newUser);
					break;
				}
			}
		}
		else if(permission == false) {
			// The user is not authorized to change another user - TO DO: Send error	
		}


		if (exists == false) {
			// There are no user with that ID - TO DO: Send error
		}

	}

	// Remove user

	@Override
	public void removeUser(String userID) {

		boolean permission;
		boolean exists = false;

		if (userID.equals(userAuth)) {
			permission = true;
		}
		else {
			permission = false;
		}

		if (permission == true) {
			for (Iterator<User> iterator = users.iterator(); iterator.hasNext();) {
				User u = (User) iterator.next();
				if(u.getUserID().equals(userID)) 
					iterator.remove();
				exists = true;
			}
		}
		else if (permission == false) {
			// The user is not authorized to remove another user - TO DO: Send error	
		}

		if (exists == false) {
			// There are no user with that ID - TO DO: Send error
		}
	}

}

