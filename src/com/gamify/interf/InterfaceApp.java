package com.gamify.interf;

public interface InterfaceApp {
	
	public void createApp(String appID, String userID, String appName, String type, String description, String userAuth);
	public Object getApps(String userID, String userAuth);
	public Object getApp(String userID, String appID, String userAuth);
	public void changeApp(String appID, String appName, String type, String description, String userAuth);
	public void removeApp(String appID, String userAuth);
}
