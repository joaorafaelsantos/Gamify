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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import com.gamify.data.ErrorData;
import com.gamify.impl.AuthManager;
import com.gamify.impl.UserManager;
import com.gamify.model.Error;
import com.gamify.model.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Path("/users")
public class UsersResource {
	// Create new user
	@POST
	@Consumes("application/x-www-form-urlencoded")
	public Response createUser(@FormParam("userID") String userID, @FormParam("password") String password,
			@FormParam("email") String email) {

		if (userID != null && password != null && email != null) {
			UserManager um = UserManager.getInstance();
			return um.createUser(userID, password, email);
		} else {
			// Invalid data
			ErrorData errorData = ErrorData.getInstance();
			Error error = errorData.getData("12");
			return Response.serverError().status(Integer.parseInt(error.getHttp_status())).type("text/plain")
					.entity(error.getMessage()).build();
		}

	}

	// Get all users
	@GET
	@Produces(MediaType.APPLICATION_JSON)

	public Object getUsers(@QueryParam("apiKey") String apiKey) {
		if (apiKey != null) {
			AuthManager authManager = AuthManager.getInstance();
			Claims claims = Jwts.parser().setSigningKey(authManager.getKey()).parseClaimsJws(apiKey).getBody();
			String userAuth = claims.get("userID").toString();

			UserManager um = UserManager.getInstance();
			return um.getUsers(userAuth);
		} else {
			// Invalid data
			ErrorData errorData = ErrorData.getInstance();
			return errorData.getData("12");
		}
	}

	// GET a specific user
	@Path("/{userID}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Object getUser(@PathParam("userID") String userID, @QueryParam("apiKey") String apiKey) {
		if (userID != null && apiKey != null) {
			AuthManager authManager = AuthManager.getInstance();
			Claims claims = Jwts.parser().setSigningKey(authManager.getKey()).parseClaimsJws(apiKey).getBody();
			String userAuth = claims.get("userID").toString();

			UserManager um = UserManager.getInstance();
			return um.getUser(userID, userAuth);
		} else {
			// Invalid data
			ErrorData errorData = ErrorData.getInstance();
			return errorData.getData("12");
		}

	}

	// Change a specific user
	@Path("/{userID}")
	@POST
	@Consumes("application/x-www-form-urlencoded")
	public Object changeUser(@PathParam("userID") String userID, @FormParam("password") String password,
			@FormParam("email") String email, @QueryParam("apiKey") String apiKey) {
		if (userID != null && password != null && email != null && apiKey != null) {
			AuthManager authManager = AuthManager.getInstance();
			Claims claims = Jwts.parser().setSigningKey(authManager.getKey()).parseClaimsJws(apiKey).getBody();
			String userAuth = claims.get("userID").toString();

			UserManager um = UserManager.getInstance();
			um.changeUser(userID, password, email, userAuth);

			return Response.ok().entity("User changed!").build();
		} else {
			// Invalid data
			ErrorData errorData = ErrorData.getInstance();
			return errorData.getData("12");
		}
	}

	// DELETE a specific user
	@Path("/{userID}")
	@DELETE
	public Object removeUser(@PathParam("userID") String userID, @FormParam("apiKey") String apiKey) {
		if (userID != null && apiKey != null) {
			AuthManager authManager = AuthManager.getInstance();
			Claims claims = Jwts.parser().setSigningKey(authManager.getKey()).parseClaimsJws(apiKey).getBody();
			String userAuth = claims.get("userID").toString();

			UserManager um = UserManager.getInstance();
			return um.removeUser(userID, userAuth);
		} else {
			// Invalid data
			ErrorData errorData = ErrorData.getInstance();
			return errorData.getData("12");
		}

	}

}
