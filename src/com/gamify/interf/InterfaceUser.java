package com.gamify.interf;

import java.util.List;

import com.gamify.model.User;

public interface InterfaceUser {

	public void createUser(String userID, String password, String email);
	public List<User> getUsers();
	public User getUser(String userID);
	public void changeUser(String userID, User oldUser, User newUser);
	public void removeUser(String userID);

}



