package com.gamify.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.gamify.interf.InterfaceUser;
import com.gamify.model.User;

public class UserManager implements InterfaceUser {

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

	@Override
	public void createUser(String userID, String password, String email) {
		User u = new User(userID, password, email);
		users.add(u);
	}

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
			// There are no users
			return null;
		}
	}

	@Override
	public User getUser(String userID) {
		for(User user:users) {
			if(user.getUserID().equals(userID)) {
				User fu = new User(user.getUserID(), user.getEmail());
				return fu;
			}
		}
		// There are no user with that ID
		return null;

	}

	@Override
	public void changeUser(String userID, User oldUser, User newUser) {
		// Change here
	}

	@Override
	public void removeUser(String userID) {
		for (Iterator<User> iterator = users.iterator(); iterator.hasNext();) {
			User u = (User) iterator.next();
			if(u.getUserID().equals(userID))
				iterator.remove();
		}
	}

}

