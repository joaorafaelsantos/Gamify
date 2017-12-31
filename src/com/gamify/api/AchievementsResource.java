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

import com.gamify.impl.AchievementManager;
import com.gamify.model.Achievement;
import com.gamify.model.App;




@Path("/apps/{appID}/achievements")
public class AchievementsResource {

	String userAuth = "joaorsantos"; // To change when add auth (token)

	// Create new achievement
	@POST
	@Consumes("application/x-www-form-urlencoded")
	public Response createAchievement(
			@FormParam("achievementID") String achievementID,
			@PathParam("appID") App appID,
			@FormParam("achievementName") String achievementName,
			@FormParam("structure") String structure,
			@FormParam("goal") String goal,
			@FormParam("type") String type,
			@FormParam("description") String description,
			@Context UriInfo uriInfo) {

		AchievementManager am = AchievementManager.getInstance();

		am.createAchievement(achievementID, appID, achievementName, structure, goal,type,description);

		UriBuilder builder = uriInfo.getAbsolutePathBuilder();	
		builder.path(achievementID);
		return Response.created(builder.build()).build();
	}


	// Get all achievement
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Achievement> getAchievements(String userAuth) {

		AchievementManager am = AchievementManager.getInstance();		
		return am.getAchievements(userAuth);
	}

	// GET a specific achievement
	@Path("/{achievementID}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Achievement getAchievement(String userAuth, @PathParam("achievementID") String achievementID) {

		AchievementManager am = AchievementManager.getInstance();		
		return am.getAchievement(userAuth,achievementID);
	}

	// Change a specific achievement
	@Path("/{achievementID}")
	@POST
	@Consumes("application/x-www-form-urlencoded")
	
	public Response changeAchievement(@PathParam("achievementID") String achievementID, @FormParam("achievementName") String achievementName, @FormParam("goal") String goal, @FormParam("type") String type, @FormParam("description") String description) {

		AchievementManager am = AchievementManager.getInstance();		
		am.changeAchievement(achievementID, achievementName,goal, type, description);

		return Response.ok().entity("").build(); // Send response * TO DO *
	}
	
	//DELETE a specific achievement
	@Path("/{achievementID}")
	@DELETE	
	public Response removeAchievement(@PathParam("achievementID") String achievementID) {

		AchievementManager am = AchievementManager.getInstance();		
		am.removeAchievement(achievementID);

		return Response.ok().entity("").build(); // Send response * TO DO *
	}

}



