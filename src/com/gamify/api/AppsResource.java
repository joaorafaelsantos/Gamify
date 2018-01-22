package com.gamify.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import com.gamify.data.ErrorData;
import com.gamify.impl.AppManager;
import com.gamify.impl.AuthManager;
import com.gamify.model.Error;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Path("/users/{userID}/apps")
public class AppsResource {

	// Create new app
	@POST
	@Consumes("application/x-www-form-urlencoded")
	public Response createApp(@FormParam("appID") String appID, @FormParam("appName") String appName,
			@FormParam("type") String type, @FormParam("description") String description,
			@FormParam("apiKey") String apiKey, @Context UriInfo uriInfo) {

		if (appID != null && appName != null && type != null && description != null && apiKey != null) {
			AuthManager authManager = AuthManager.getInstance();
			Claims claims = Jwts.parser().setSigningKey(authManager.getKey()).parseClaimsJws(apiKey).getBody();
			String userAuth = claims.get("username").toString();

			AppManager am = AppManager.getInstance();

			am.createApp(appID, userAuth, appName, type, description, userAuth);

			UriBuilder builder = uriInfo.getAbsolutePathBuilder();
			builder.path(appID);
			return Response.created(builder.build()).build();
		} else {
			// Invalid data
			ErrorData errorData = ErrorData.getInstance();
			Error error = errorData.getData("12");
			return Response.serverError().status(Integer.parseInt(error.getHttp_status())).type("text/plain")
					.entity(error.getMessage()).build();
		}
	}

	// Get all apps
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Object getApps(@PathParam("userID") String userID, @QueryParam("apiKey") String apiKey) {

		if (userID != null && apiKey != null) {
			AuthManager authManager = AuthManager.getInstance();
			Claims claims = Jwts.parser().setSigningKey(authManager.getKey()).parseClaimsJws(apiKey).getBody();
			String userAuth = claims.get("username").toString();

			AppManager am = AppManager.getInstance();
			return am.getApps(userID, userAuth);
		} else {
			// Invalid data
			ErrorData errorData = ErrorData.getInstance();
			return errorData.getData("12");
		}

	}

	// GET a specific app
	@Path("/{appID}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Object getApp(@PathParam("userID") String userID, @PathParam("appID") String appID,
			@QueryParam("apiKey") String apiKey) {

		if (userID != null && appID != null && apiKey != null) {
			AuthManager authManager = AuthManager.getInstance();
			Claims claims = Jwts.parser().setSigningKey(authManager.getKey()).parseClaimsJws(apiKey).getBody();
			String userAuth = claims.get("username").toString();

			AppManager am = AppManager.getInstance();
			return am.getApp(userID, appID, userAuth);
		} else {
			// Invalid data
			ErrorData errorData = ErrorData.getInstance();
			return errorData.getData("12");
		}

	}

	// Change a specific app
	@Path("/{appID}")
	@POST
	@Consumes("application/x-www-form-urlencoded")
	public Object changeApp(@PathParam("appID") String appID, @FormParam("appName") String appName,
			@FormParam("type") String type, @FormParam("description") String description,
			@FormParam("apiKey") String apiKey) {

		if (appID != null && appName != null && type != null && description != null && apiKey != null) {
			AuthManager authManager = AuthManager.getInstance();
			Claims claims = Jwts.parser().setSigningKey(authManager.getKey()).parseClaimsJws(apiKey).getBody();
			String userAuth = claims.get("username").toString();

			AppManager am = AppManager.getInstance();
			am.changeApp(appID, appName, type, description, userAuth);

			return Response.ok().entity("App changed!").build();
		} else {
			// Invalid data
			ErrorData errorData = ErrorData.getInstance();
			return errorData.getData("12");
		}

	}

	// DELETE a specific app
	@Path("/{appID}")
	@DELETE

	public Object removeApp(@PathParam("appID") String appID, @FormParam("apiKey") String apiKey) {

		if (appID != null && apiKey != null) {
			AuthManager authManager = AuthManager.getInstance();
			Claims claims = Jwts.parser().setSigningKey(authManager.getKey()).parseClaimsJws(apiKey).getBody();
			String userAuth = claims.get("username").toString();

			AppManager am = AppManager.getInstance();
			am.removeApp(appID, userAuth);

			return Response.ok().entity("User").build();
		} else {
			// Invalid data
			ErrorData errorData = ErrorData.getInstance();
			return errorData.getData("12");
		}

	}

}
