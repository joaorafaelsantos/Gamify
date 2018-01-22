package com.gamify.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.gamify.data.ErrorData;
import com.gamify.impl.AchievementManager;
import com.gamify.impl.AuthManager;
import com.gamify.model.Error;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Path("/apps/{appID}/achievements")
public class AchievementsResource {

	// Create new achievement
	@POST
	@Consumes("application/x-www-form-urlencoded")
	public Response createAchievement(@PathParam("appID") String appID, @FormParam("name") String name,
			@FormParam("structure") String structure, @FormParam("reward") String reward,
			@FormParam("goal") String goal, @FormParam("type") String type,
			@FormParam("description") String description, @FormParam("apiKey") String apiKey,
			@Context UriInfo uriInfo) {

		if (appID != null && name != null && structure != null && reward != null
				&& goal != null && type != null && description != null && apiKey != null) {
			AuthManager authManager = AuthManager.getInstance();
			Claims claims = Jwts.parser().setSigningKey(authManager.getKey()).parseClaimsJws(apiKey).getBody();
			String userAuth = claims.get("userID").toString();

			AchievementManager am = AchievementManager.getInstance();

			return am.createAchievement(appID, name, structure, reward, goal, type, description, userAuth);

		} else {
			// Invalid data
			ErrorData errorData = ErrorData.getInstance();
			Error error = errorData.getData("12");
			return Response.serverError().status(Integer.parseInt(error.getHttp_status())).type("text/plain")
					.entity(error.getMessage()).build();
		}

	}

	// Get all achievements
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Object getAchievements(@PathParam("appID") String appID, @QueryParam("apiKey") String apiKey) {

		if (appID != null && apiKey != null) {
			AuthManager authManager = AuthManager.getInstance();
			Claims claims = Jwts.parser().setSigningKey(authManager.getKey()).parseClaimsJws(apiKey).getBody();
			String userAuth = claims.get("userID").toString();

			AchievementManager am = AchievementManager.getInstance();
			return am.getAchievements(appID, userAuth);
		} else {
			// Invalid data
			ErrorData errorData = ErrorData.getInstance();
			return errorData.getData("12");
		}

	}

	// GET a specific achievement
	@Path("/{achievementID}")
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Object getAchievement(@PathParam("appID") String appID, @PathParam("achievementID") String achievementID,
			@QueryParam("apiKey") String apiKey) {

		if (appID != null && achievementID != null && apiKey != null) {
			AuthManager authManager = AuthManager.getInstance();
			Claims claims = Jwts.parser().setSigningKey(authManager.getKey()).parseClaimsJws(apiKey).getBody();
			String userAuth = claims.get("userID").toString();

			AchievementManager am = AchievementManager.getInstance();
			return am.getAchievement(appID, achievementID, userAuth);
		} else {
			// Invalid data
			ErrorData errorData = ErrorData.getInstance();
			return errorData.getData("12");
		}

	}

	// Add Inputs to achievement
	@Path("/{achievementID}")
	@POST
	@Consumes("application/x-www-form-urlencoded")
	public Object addInputs(@PathParam("appID") String appID, @PathParam("achievementID") String achievementID,
			@FormParam("name") String name, @FormParam("score") String score, @FormParam("apiKey") String apiKey) {

		if (appID != null && achievementID != null && name != null && score != null && apiKey != null) {
			AuthManager authManager = AuthManager.getInstance();
			Claims claims = Jwts.parser().setSigningKey(authManager.getKey()).parseClaimsJws(apiKey).getBody();
			String userAuth = claims.get("userID").toString();

			AchievementManager am = AchievementManager.getInstance();
			am.addInputs(appID, achievementID, name, score, userAuth);
			return Response.ok().entity("Inputs added!").build();
		} else {
			// Invalid data
			ErrorData errorData = ErrorData.getInstance();
			return errorData.getData("12");
		}

	}

	// Change a specific achievement
	@Path("/{achievementID}")
	@PUT
	@Consumes("application/x-www-form-urlencoded")

	public Object changeAchievement(@PathParam("appID") String appID, @PathParam("achievementID") String achievementID,
			@FormParam("name") String name, @FormParam("reward") String reward, @FormParam("goal") String goal,
			@FormParam("type") String type, @FormParam("description") String description,
			@FormParam("apiKey") String apiKey) {

		if (appID != null && achievementID != null && name != null && reward != null && goal != null && type != null
				&& description != null && apiKey != null) {
			AuthManager authManager = AuthManager.getInstance();
			Claims claims = Jwts.parser().setSigningKey(authManager.getKey()).parseClaimsJws(apiKey).getBody();
			String userAuth = claims.get("userID").toString();

			AchievementManager am = AchievementManager.getInstance();
			am.changeAchievement(appID, achievementID, name, reward, goal, type, description, userAuth);

			return Response.ok().entity("Achievement changed!").build();
		} else {
			// Invalid data
			ErrorData errorData = ErrorData.getInstance();
			return errorData.getData("12");
		}

	}

	// DELETE a specific achievement
	@Path("/{achievementID}")
	@DELETE
	public Object removeAchievement(@PathParam("appID") String appID, @PathParam("achievementID") String achievementID,
			@QueryParam("apiKey") String apiKey) {

		if (appID != null && achievementID != null && apiKey != null) {
			AuthManager authManager = AuthManager.getInstance();
			Claims claims = Jwts.parser().setSigningKey(authManager.getKey()).parseClaimsJws(apiKey).getBody();
			String userAuth = claims.get("userID").toString();

			AchievementManager am = AchievementManager.getInstance();
			am.removeAchievement(appID, achievementID, userAuth);

			return Response.ok().entity("Achievement deleted!").build();
		} else {
			// Invalid data
			ErrorData errorData = ErrorData.getInstance();
			return errorData.getData("12");
		}

	}

}
