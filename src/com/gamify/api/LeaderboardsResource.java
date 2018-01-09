//package com.gamify.api;
//
//import java.util.List;
//
//import javax.ws.rs.Consumes;
//import javax.ws.rs.DELETE;
//import javax.ws.rs.FormParam;
//import javax.ws.rs.GET;
//import javax.ws.rs.POST;
//import javax.ws.rs.PUT;
//import javax.ws.rs.Path;
//import javax.ws.rs.PathParam;
//import javax.ws.rs.Produces;
//import javax.ws.rs.core.Context;
//import javax.ws.rs.core.MediaType;
//import javax.ws.rs.core.Response;
//import javax.ws.rs.core.UriBuilder;
//import javax.ws.rs.core.UriInfo;
//
//import com.gamify.impl.AchievementManager;
//import com.gamify.impl.LeaderboardManager;
//import com.gamify.model.Achievement;
//import com.gamify.model.App;
//import com.gamify.model.Leaderboard;
//
//
//
//
//
//
//
//@Path("/apps/{appID}/leaderboards")
//public class LeaderboardsResource {
//
//	String userAuth = "joaorsantos"; // To change when add auth (token)
//
//	// Create new  Leaderboard
//	@POST
//	@Consumes("application/x-www-form-urlencoded")
//	public Response createLeaderboard(
//			@FormParam("leaderboardID") String leaderboardID,
//			@PathParam("appID") App appID,
//			@FormParam("leaderboardName") String leaderboardName,
//			@FormParam("type") String type,
//			@FormParam("description") String description,
//			@Context UriInfo uriInfo) {
//
//		LeaderboardManager lm = LeaderboardManager.getInstance();
//
//		lm.createLeaderboard(leaderboardID, appID, leaderboardName, type,description);
//
//		UriBuilder builder = uriInfo.getAbsolutePathBuilder();	
//		builder.path(leaderboardID);
//		return Response.created(builder.build()).build();
//	}
//
//
//	// Get all leaderboard
//	@GET
//	@Produces(MediaType.APPLICATION_JSON)
//	public List<Leaderboard> getLeaderboards(@PathParam("appID") String appID) {
//
//		LeaderboardManager lm = LeaderboardManager.getInstance();		
//		return lm.getLeaderboards(appID);
//	}
//
//	// GET a specific leaderboard
//	@Path("/{leaderboardID}")
//	@GET
//	@Produces(MediaType.APPLICATION_JSON)
//	public Leaderboard getLeaderboard(@PathParam("appID") String appID, @PathParam("leaderboardID") String leaderboardID) {
//
//		LeaderboardManager lm = LeaderboardManager.getInstance();		
//		return lm.getLeaderboard(appID,leaderboardID);
//	}
//
//	
//	// Add Inputs to Leaderboard
//	@Path("/{leaderboardID}")
//	@POST
//	@Consumes("application/x-www-form-urlencoded")
//	public Leaderboard inputsLeaderboards(@PathParam("appID") String appID,@PathParam("leaderboardID") String leaderboardID, @FormParam("name") String name, @FormParam("score") String score) {
//		LeaderboardManager lm = LeaderboardManager.getInstance();			
//		return lm.inputsLeaderboards(appID,leaderboardID,name,score);
//		
//	}
//	
//	// Change a specific Leaderboard
//	@Path("/{leaderboardID}")
//	@PUT
//	@Consumes("application/x-www-form-urlencoded")
//	public Response changeLeaderboard(@PathParam("leaderboardID") String leaderboardID, @FormParam("leaderboardName") String leaderboardName, @FormParam("type") String type, @FormParam("description") String description) {
//
//		LeaderboardManager lm = LeaderboardManager.getInstance();				
//		lm.changeLeaderboard(leaderboardID,leaderboardName, type, description);
//
//		return Response.ok().entity("").build(); // Send response * TO DO *
//	}
//	
//	//DELETE a specific achievement
//	@Path("/{leaderboardID}")
//	@DELETE	
//	@Consumes("application/x-www-form-urlencoded")
//	public Response removeLeaderboard(@PathParam("leaderboardID") String leaderboardID) {
//
//		LeaderboardManager lm = LeaderboardManager.getInstance();		
//		lm.removeLeaderboard(leaderboardID);
//
//		return Response.ok().entity("").build(); // Send response * TO DO *
//	}
//	
//	@Path("/{leaderboardID}/reset/score")
//	@PUT
//	@Consumes("application/x-www-form-urlencoded")
//	@Produces(MediaType.APPLICATION_JSON)
//	public void resetLeaderBoardScore(@PathParam("appID") String appID, @PathParam("leaderboardID") String leaderboardID) {
//
//		LeaderboardManager lm = LeaderboardManager.getInstance();		
//		//return lm.resetLeaderBoardScore(appID, leaderboardID);
//	}
//	
//	@Path("/{leaderboardID}/reset/total")
//	@PUT
//	@Consumes("application/x-www-form-urlencoded")
//	@Produces(MediaType.APPLICATION_JSON)
//	public void resetLeaderBoardTotal(@PathParam("appID") String appID, @PathParam("leaderboardID") String leaderboardID) {
//
//		LeaderboardManager lm = LeaderboardManager.getInstance();		
//		//return lm.resetLeaderBoardScore(appID, leaderboardID);
//	}
//	
//	
//
//}
//
//
//
//
