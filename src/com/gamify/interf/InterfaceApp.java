package com.gamify.interf;

import java.util.List;

import com.gamify.model.App;
import com.gamify.model.User;

public interface InterfaceApp {
	
	public void createApp(String appID, User userID, String appName, String type, String description);
	public App getApp(String userID, String appID);
	public void changeApp(String appID, App oldApp, App newApp);
	public void removeApp(String appID);
	public List<App> getApps(String userID);

}
