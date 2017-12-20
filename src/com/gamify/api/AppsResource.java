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
import com.gamify.model.App;
import com.gamify.model.User;

@Path("/apps")
public class AppsResource {

	// Create new app
	@POST
	@Consumes("application/x-www-form-urlencoded")
	public Response createApp(
			@FormParam("idApp") String idApp,
			@FormParam("appName") String appName,
			@FormParam("type") String type,
			@FormParam("description") String description,
			@Context UriInfo uriInfo) {

		AppManager am = AppManager.getInstance();
		//User username = USER IDENTIFIER *TO DO* ...
//		am.createApp(idApp, username, appName, type, description);

		UriBuilder builder = uriInfo.getAbsolutePathBuilder();	
		builder.path(idApp);
		return Response.created(builder.build()).build();
	}


	// Get all apps
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<App> getApps() {

		AppManager am = AppManager.getInstance();		
		return am.getApps();
	}

	// GET a specific app
	@Path("/{idApp}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public App getApp(@PathParam("idApp") String idApp) {

		AppManager am = AppManager.getInstance();		
		return am.getApp(idApp);
	}

	//DELETE a specific app
	@Path("/{idApp}")
	@DELETE	
	public Response removeUser(@PathParam("idApp") String idApp) {

		AppManager am = AppManager.getInstance();		
		am.removeApp(idApp);

		return Response.ok().entity("App removed!").build();
	}

}


