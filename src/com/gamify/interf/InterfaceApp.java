package com.gamify.interf;

import java.util.List;

import com.gamify.model.App;
import com.gamify.model.User;

public interface InterfaceApp {
	
	public void createApp(String appID, String userID, String appName, String type, String description);
	public List<App> getApps(String userID);
	public List<App> getApp(String userID, String appID);
	public void changeApp(String appID, String appName, String type, String description);
	public void removeApp(String appID);
}
