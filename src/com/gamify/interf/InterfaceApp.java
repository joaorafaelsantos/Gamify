package com.gamify.interf;

public interface InterfaceApp {
	
	public void createApp(String appID, String userID, String appName, String type, String description);
	public Object getApps(String userID);
	public Object getApp(String userID, String appID);
	public void changeApp(String appID, String appName, String type, String description);
	public void removeApp(String appID);
}
