package com.gamify.interf;

import java.util.List;
import com.gamify.model.User;

public interface InterfaceUser {

	public void createUser(String username, String password, String email);
	public List<User> getUsers();
	public User getUser(String username);
	public void changeUser(String username, User oldUser, User newUser);
	public void removeUser(String username);

}



