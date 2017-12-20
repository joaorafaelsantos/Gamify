package com.gamify.interf;

import java.util.List;

import com.gamify.model.App;
import com.gamify.model.User;

public interface InterfaceApp {
	
	public void createApp(String idApp, User username, String appName, String type, String description);
	public List<App> getApps();
	public App getApp(String idApp);
	public void changeApp(String idApp, App oldApp, App newApp);
	public void removeApp(String idApp);

}
