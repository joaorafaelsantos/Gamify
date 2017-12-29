package com.gamify.api;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import com.gamify.impl.AppManager;
import com.gamify.impl.UserManager;
import com.gamify.model.App;
import com.gamify.model.User;

@Path("/users/{userID}/apps")
public class AppsResource {

	// Create new app
	@POST
	@Consumes("application/x-www-form-urlencoded")
	public Response createApp(
			@FormParam("appID") String appID,
			@FormParam("appName") String appName,
			@FormParam("type") String type,
			@FormParam("description") String description,
			@Context UriInfo uriInfo) {

		AppManager am = AppManager.getInstance();
		// To change when add auth (token)
		String userAuth = "joaorsantos";
		am.createApp(appID, userAuth, appName, type, description);

		UriBuilder builder = uriInfo.getAbsolutePathBuilder();	
		builder.path(appID);
		return Response.created(builder.build()).build();
	}


	// Get all apps
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<App> getApps(@PathParam("userID") String userID) {

		AppManager am = AppManager.getInstance();		
		return am.getApps(userID);
	}

	// GET a specific app
	@Path("/{appID}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public App getApp(@PathParam("userID") String userID, @PathParam("appID") String appID) {

		AppManager am = AppManager.getInstance();		
		return am.getApp(userID,appID);
	}
	
	// Change a specific user
	@Path("/{appID}")
	@POST
	@Consumes("application/x-www-form-urlencoded")
	public Response changeApp(@PathParam("appID") String appID, @FormParam("appName") String appName, @FormParam("type") String type, @FormParam("description") String description) {

		AppManager am = AppManager.getInstance();		
		am.changeApp(appID, appName, type, description);

		return Response.ok().entity("").build();
	}

	//DELETE a specific app
	@Path("/{appID}")
	@DELETE	
	public Response removeUser(@PathParam("appID") String appID) {

		AppManager am = AppManager.getInstance();		
		am.removeApp(appID);

		return Response.ok().entity("App removed!").build();
	}

}


