package com.gamify.interf;

import java.util.List;

import com.gamify.model.User;

public interface InterfaceUser {

	public Object createUser(String userID, String password, String email);
	public List<User> getUsers();
	public List<User> getUser(String userID);
	public Object changeUser(String userID, String newPassword, String newEmail);
	public Object removeUser(String userID);

}



