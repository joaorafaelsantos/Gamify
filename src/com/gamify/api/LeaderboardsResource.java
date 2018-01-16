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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import com.gamify.impl.AuthManager;
import com.gamify.impl.LeaderboardManager;

import com.gamify.model.Leaderboard;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Path("/apps/{appID}/leaderboards")
public class LeaderboardsResource {

	// Create new leaderboard
	@POST
	@Consumes("application/x-www-form-urlencoded")
	public Response createAchievement(@FormParam("leaderboardID") String leaderboardID,
			@PathParam("appID") String appID, @FormParam("name") String name, @FormParam("type") String type,
			@FormParam("description") String description, @FormParam("apiKey") String apiKey,
			@Context UriInfo uriInfo) {

		AuthManager authManager = AuthManager.getInstance();
		Claims claims = Jwts.parser().setSigningKey(authManager.getKey()).parseClaimsJws(apiKey).getBody();
		String userAuth = claims.get("username").toString();

		LeaderboardManager lm = LeaderboardManager.getInstance();

		lm.createLeaderboard(leaderboardID, appID, name, type, description, userAuth);

		UriBuilder builder = uriInfo.getAbsolutePathBuilder();
		builder.path(leaderboardID);
		return Response.created(builder.build()).build();
	}

	// Get all leaderboards
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Leaderboard> getLeaderboards(@PathParam("appID") String appID, @QueryParam("apiKey") String apiKey) {

		AuthManager authManager = AuthManager.getInstance();
		Claims claims = Jwts.parser().setSigningKey(authManager.getKey()).parseClaimsJws(apiKey).getBody();
		String userAuth = claims.get("username").toString();

		LeaderboardManager lm = LeaderboardManager.getInstance();
		return lm.getLeaderboards(appID, userAuth);
	}

	// GET a specific leaderboard
	@Path("/{leaderboardID}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Leaderboard getLeaderboard(@PathParam("appID") String appID,
			@PathParam("leaderboardID") String leaderboardID, @QueryParam("apiKey") String apiKey) {

		AuthManager authManager = AuthManager.getInstance();
		Claims claims = Jwts.parser().setSigningKey(authManager.getKey()).parseClaimsJws(apiKey).getBody();
		String userAuth = claims.get("username").toString();

		LeaderboardManager lm = LeaderboardManager.getInstance();
		return lm.getLeaderboard(appID, leaderboardID, userAuth);
	}

	// Add Inputs to leaderboard
	@Path("/{leaderboardID}")
	@POST
	@Consumes("application/x-www-form-urlencoded")
	public Response addInputs(@PathParam("appID") String appID, @PathParam("leaderboardID") String leaderboardID,
			@FormParam("name") String name, @FormParam("score") String score, @FormParam("apiKey") String apiKey) {

		AuthManager authManager = AuthManager.getInstance();
		Claims claims = Jwts.parser().setSigningKey(authManager.getKey()).parseClaimsJws(apiKey).getBody();
		String userAuth = claims.get("username").toString();

		LeaderboardManager lm = LeaderboardManager.getInstance();
		lm.addInputs(appID, leaderboardID, name, score, userAuth);
		return Response.ok().entity("").build();

	}

	// Change a specific leaderboard
	@Path("/{leaderboardID}")
	@PUT
	@Consumes("application/x-www-form-urlencoded")

	public Response changeLeaderboard(@PathParam("appID") String appID,
			@PathParam("leaderboardID") String leaderboardID, @FormParam("name") String name,
			@FormParam("type") String type, @FormParam("description") String description,
			@FormParam("apiKey") String apiKey) {

		AuthManager authManager = AuthManager.getInstance();
		Claims claims = Jwts.parser().setSigningKey(authManager.getKey()).parseClaimsJws(apiKey).getBody();
		String userAuth = claims.get("username").toString();

		LeaderboardManager lm = LeaderboardManager.getInstance();
		lm.changeLeaderboard(appID, leaderboardID, name, type, description, userAuth);

		return Response.ok().entity("").build(); // Send response * TO DO *
	}

	// DELETE a specific leaderboard
	@Path("/{leaderboardID}")
	@DELETE
	public Response removeLeaderboard(@PathParam("appID") String appID,
			@PathParam("leaderboardID") String leaderboardID, @FormParam("apiKey") String apiKey) {

		AuthManager authManager = AuthManager.getInstance();
		Claims claims = Jwts.parser().setSigningKey(authManager.getKey()).parseClaimsJws(apiKey).getBody();
		String userAuth = claims.get("username").toString();

		LeaderboardManager lm = LeaderboardManager.getInstance();
		lm.removeLeaderboard(appID, leaderboardID, userAuth);

		return Response.ok().entity("").build(); // Send response * TO DO *
	}

	// Resets leaderboards

	@Path("/{leaderboardID}/reset/score")
	@POST
	@Consumes("application/x-www-form-urlencoded")
	public void resetLeaderBoardScore(@PathParam("appID") String appID,
			@PathParam("leaderboardID") String leaderboardID, @FormParam("apiKey") String apiKey) {

		AuthManager authManager = AuthManager.getInstance();
		Claims claims = Jwts.parser().setSigningKey(authManager.getKey()).parseClaimsJws(apiKey).getBody();
		String userAuth = claims.get("username").toString();

		LeaderboardManager lm = LeaderboardManager.getInstance();
		lm.resetLeaderboardScore(appID, leaderboardID, userAuth);
	}

	@Path("/{leaderboardID}/reset/total")
	@POST
	@Consumes("application/x-www-form-urlencoded")
	public Response resetLeaderBoardTotal(@PathParam("appID") String appID,
			@PathParam("leaderboardID") String leaderboardID, @FormParam("apiKey") String apiKey) {

		AuthManager authManager = AuthManager.getInstance();
		Claims claims = Jwts.parser().setSigningKey(authManager.getKey()).parseClaimsJws(apiKey).getBody();
		String userAuth = claims.get("username").toString();

		LeaderboardManager lm = LeaderboardManager.getInstance();
		lm.resetLeaderboardTotal(appID, leaderboardID, userAuth);
		return Response.ok().entity("").build();
	}

}
