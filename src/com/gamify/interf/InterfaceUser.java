package com.gamify.interf;

import java.util.List;

import com.gamify.model.User;

public interface InterfaceUser {

	public Object createUser(String userID, String password, String email);
	public List<User> getUsers(String userAuth);
	public List<User> getUser(String userID, String userAuth);
	public Object changeUser(String userID, String newPassword, String newEmail, String userAuth);
	public Object removeUser(String userID, String userAuth);

}



