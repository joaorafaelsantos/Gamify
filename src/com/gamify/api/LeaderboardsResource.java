package com.gamify.api;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import com.gamify.impl.LeaderboardManager;


import com.gamify.model.Leaderboard;




@Path("/apps/{appID}/leaderboards")
public class LeaderboardsResource {

	String userAuth = "joaorsantos"; // To change when add auth (token)

	// Create new leaderboard
	@POST
	@Consumes("application/x-www-form-urlencoded")
	public Response createAchievement(
			@FormParam("leaderboardID") String leaderboardID,
			@PathParam("appID") String appID,
			@FormParam("name") String name,
			@FormParam("type") String type,
			@FormParam("description") String description,
			@Context UriInfo uriInfo)  {

		LeaderboardManager lm = LeaderboardManager.getInstance();
		
				lm.createLeaderboard(leaderboardID, appID, name, type,description);
		
				UriBuilder builder = uriInfo.getAbsolutePathBuilder();	
				builder.path(leaderboardID);
				return Response.created(builder.build()).build();
	}


	// Get all leaderboards
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Leaderboard> getLeaderboards(@PathParam("appID") String appID) {

		LeaderboardManager lm = LeaderboardManager.getInstance();
		return lm.getLeaderboards(appID);
	}

	// GET a specific leaderboard
	@Path("/{leaderboardID}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Leaderboard getLeaderboard(@PathParam("appID") String appID, @PathParam("leaderboardID") String leaderboardID) {

		LeaderboardManager lm = LeaderboardManager.getInstance();
		return lm.getLeaderboard(appID,leaderboardID);
	}

	// Add Inputs to leaderboard
	@Path("/{leaderboardID}")
	@POST
	@Consumes("application/x-www-form-urlencoded")
	public Response addInputs(@PathParam("appID") String appID, @PathParam("leaderboardID") String leaderboardID, @FormParam("name") String name, @FormParam("score") String score) {
		LeaderboardManager lm = LeaderboardManager.getInstance();
		lm.addInputs(appID,leaderboardID,name,score);
		return Response.ok().entity("").build();
		
		
	}
	
	// Change a specific leaderboard
	@Path("/{leaderboardID}")
	@PUT
	@Consumes("application/x-www-form-urlencoded")
	
	public Response changeLeaderboard(@PathParam("appID") String appID, @PathParam("leaderboardID") String leaderboardID, @FormParam("name") String name, @FormParam("type") String type, @FormParam("description") String description) {

		LeaderboardManager lm = LeaderboardManager.getInstance();	
		lm.changeLeaderboard(appID, leaderboardID, name,type, description);

		return Response.ok().entity("").build(); // Send response * TO DO *
	}
	
	//DELETE a specific leaderboard
	@Path("/{leaderboardID}")
	@DELETE	
	public Response removeLeaderboard(@PathParam("appID") String appID, @PathParam("leaderboardID") String leaderboardID) {

		LeaderboardManager lm = LeaderboardManager.getInstance();	
		lm.removeLeaderboard(appID, leaderboardID);

		return Response.ok().entity("").build(); // Send response * TO DO *
	}
	
	
	// Resets leaderboards 
	
	@Path("/{leaderboardID}/reset/score")
	@POST
	@Consumes("application/x-www-form-urlencoded")
	public void resetLeaderBoardScore(@PathParam("appID") String appID, @PathParam("leaderboardID") String leaderboardID) {

		LeaderboardManager lm = LeaderboardManager.getInstance();		
		lm.resetLeaderboardScore(appID, leaderboardID);
	}
	
	@Path("/{leaderboardID}/reset/total")
	@POST
	@Consumes("application/x-www-form-urlencoded")
	public Response resetLeaderBoardTotal(@PathParam("appID") String appID, @PathParam("leaderboardID") String leaderboardID) {

		LeaderboardManager lm = LeaderboardManager.getInstance();		
		lm.resetLeaderboardTotal(appID, leaderboardID);
		return Response.ok().entity("").build();
	}

}



