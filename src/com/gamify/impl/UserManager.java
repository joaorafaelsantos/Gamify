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
		}
		return um;
	}

	@Override
	public void createUser(String username, String password, String email) {
		User u = new User(username, password, email);
		users.add(u);
	}

	@Override
	public List<User> getUsers() {
		List<User> filteredUsers = new ArrayList<User>();
		for(User user:users) {
		    User fu = new User(user.getUsername(), user.getEmail());
		    filteredUsers.add(fu);
		}
		return filteredUsers;
	}
	
	@Override
	public User getUser(String username) {
		for (Iterator<User> iterator = users.iterator(); iterator.hasNext();) {
			User u = (User) iterator.next();
			if(u.getUsername().equals(username))
				return u;
		}
		return null;
	}

	@Override
	public void changeUser(String username, User oldUser, User newUser) {
//		for (Iterator<User> iterator = users.iterator(); iterator.hasNext();) {
//			User user = (User) iterator.next();
//			if(user.getUsername().equals(username))
//				//change here
//		}
	}

	@Override
	public void removeUser(String username) {
		for (Iterator<User> iterator = users.iterator(); iterator.hasNext();) {
			User u = (User) iterator.next();
			if(u.getUsername().equals(username))
				iterator.remove();
		}
	}
	
}

