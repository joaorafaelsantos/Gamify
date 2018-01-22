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

import com.gamify.data.ErrorData;
import com.gamify.impl.AuthManager;
import com.gamify.impl.LeaderboardManager;
import com.gamify.model.Error;
import com.gamify.model.Leaderboard;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Path("/apps/{appID}/leaderboards")
public class LeaderboardsResource {

	// Create new leaderboard
	@POST
	@Consumes("application/x-www-form-urlencoded")
	public Response createAchievement(@PathParam("appID") String appID, @FormParam("name") String name,
			@FormParam("type") String type, @FormParam("description") String description,
			@FormParam("apiKey") String apiKey, @Context UriInfo uriInfo) {

		if (appID != null && name != null && type != null && description != null && apiKey != null) {
			AuthManager authManager = AuthManager.getInstance();
			Claims claims = Jwts.parser().setSigningKey(authManager.getKey()).parseClaimsJws(apiKey).getBody();
			String userAuth = claims.get("userID").toString();
			LeaderboardManager lm = LeaderboardManager.getInstance();
			return lm.createLeaderboard(appID, name, type, description, userAuth);
		} else {
			// Invalid data
			ErrorData errorData = ErrorData.getInstance();
			Error error = errorData.getData("12");
			return Response.serverError().status(Integer.parseInt(error.getHttp_status())).type("text/plain")
					.entity(error.getMessage()).build();
		}

	}

	// Get all leaderboards
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Object getLeaderboards(@PathParam("appID") String appID, @QueryParam("apiKey") String apiKey) {

		if (appID != null && appID != null && apiKey != null) {
			AuthManager authManager = AuthManager.getInstance();
			Claims claims = Jwts.parser().setSigningKey(authManager.getKey()).parseClaimsJws(apiKey).getBody();
			String userAuth = claims.get("userID").toString();

			LeaderboardManager lm = LeaderboardManager.getInstance();
			return lm.getLeaderboards(appID, userAuth);
		} else {
			// Invalid data
			ErrorData errorData = ErrorData.getInstance();
			return errorData.getData("12");
		}

	}

	// GET a specific leaderboard
	@Path("/{leaderboardID}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Object getLeaderboard(@PathParam("appID") String appID, @PathParam("leaderboardID") String leaderboardID,
			@QueryParam("apiKey") String apiKey) {

		if (appID != null && leaderboardID != null && apiKey != null) {
			AuthManager authManager = AuthManager.getInstance();
			Claims claims = Jwts.parser().setSigningKey(authManager.getKey()).parseClaimsJws(apiKey).getBody();
			String userAuth = claims.get("userID").toString();

			LeaderboardManager lm = LeaderboardManager.getInstance();
			return lm.getLeaderboard(appID, leaderboardID, userAuth);
		} else {
			// Invalid data
			ErrorData errorData = ErrorData.getInstance();
			return errorData.getData("12");
		}

	}

	// Add Inputs to leaderboard
	@Path("/{leaderboardID}")
	@POST
	@Consumes("application/x-www-form-urlencoded")
	public Object addInputs(@PathParam("appID") String appID, @PathParam("leaderboardID") String leaderboardID,
			@FormParam("name") String name, @FormParam("score") String score, @FormParam("apiKey") String apiKey) {

		if (appID != null && leaderboardID != null && name != null && score != null && apiKey != null) {
			AuthManager authManager = AuthManager.getInstance();
			Claims claims = Jwts.parser().setSigningKey(authManager.getKey()).parseClaimsJws(apiKey).getBody();
			String userAuth = claims.get("userID").toString();
			LeaderboardManager lm = LeaderboardManager.getInstance();
			lm.addInputs(appID, leaderboardID, name, score, userAuth);
			return Response.ok().entity("Inputs added!").build();
		} else {
			// Invalid data
			ErrorData errorData = ErrorData.getInstance();
			return errorData.getData("12");
		}

	}

	// Change a specific leaderboard
	@Path("/{leaderboardID}")
	@PUT
	@Consumes("application/x-www-form-urlencoded")

	public Object changeLeaderboard(@PathParam("appID") String appID, @PathParam("leaderboardID") String leaderboardID,
			@FormParam("name") String name, @FormParam("type") String type,
			@FormParam("description") String description, @FormParam("apiKey") String apiKey) {

		if (appID != null && leaderboardID != null && name != null && type != null && description != null
				&& apiKey != null) {
			AuthManager authManager = AuthManager.getInstance();
			Claims claims = Jwts.parser().setSigningKey(authManager.getKey()).parseClaimsJws(apiKey).getBody();
			String userAuth = claims.get("userID").toString();

			LeaderboardManager lm = LeaderboardManager.getInstance();
			lm.changeLeaderboard(appID, leaderboardID, name, type, description, userAuth);

			return Response.ok().entity("").build();
		} else {
			// Invalid data
			ErrorData errorData = ErrorData.getInstance();
			return errorData.getData("12");
		}

	}

	// DELETE a specific leaderboard
	@Path("/{leaderboardID}")
	@DELETE
	public Object removeLeaderboard(@PathParam("appID") String appID, @PathParam("leaderboardID") String leaderboardID,
			@FormParam("apiKey") String apiKey) {

		if (appID != null && leaderboardID != null && apiKey != null) {
			AuthManager authManager = AuthManager.getInstance();
			Claims claims = Jwts.parser().setSigningKey(authManager.getKey()).parseClaimsJws(apiKey).getBody();
			String userAuth = claims.get("userID").toString();

			LeaderboardManager lm = LeaderboardManager.getInstance();
			lm.removeLeaderboard(appID, leaderboardID, userAuth);

			return Response.ok().entity("Leaderboard deleted!").build();
		} else {
			// Invalid data
			ErrorData errorData = ErrorData.getInstance();
			return errorData.getData("12");
		}

	}

	// Resets leaderboards

	@Path("/{leaderboardID}/reset/score")
	@POST
	@Consumes("application/x-www-form-urlencoded")
	public Object resetLeaderBoardScore(@PathParam("appID") String appID,
			@PathParam("leaderboardID") String leaderboardID, @FormParam("apiKey") String apiKey) {

		if (appID != null && leaderboardID != null && apiKey != null) {
			AuthManager authManager = AuthManager.getInstance();
			Claims claims = Jwts.parser().setSigningKey(authManager.getKey()).parseClaimsJws(apiKey).getBody();
			String userAuth = claims.get("userID").toString();

			LeaderboardManager lm = LeaderboardManager.getInstance();
			lm.resetLeaderboardScore(appID, leaderboardID, userAuth);
			return Response.ok().entity("Leaderboard reset score!").build();
		} else {
			// Invalid data
			ErrorData errorData = ErrorData.getInstance();
			return errorData.getData("12");
		}

	}

	@Path("/{leaderboardID}/reset/total")
	@POST
	@Consumes("application/x-www-form-urlencoded")
	public Object resetLeaderBoardTotal(@PathParam("appID") String appID,
			@PathParam("leaderboardID") String leaderboardID, @FormParam("apiKey") String apiKey) {

		if (appID != null && leaderboardID != null && apiKey != null) {
			AuthManager authManager = AuthManager.getInstance();
			Claims claims = Jwts.parser().setSigningKey(authManager.getKey()).parseClaimsJws(apiKey).getBody();
			String userAuth = claims.get("userID").toString();

			LeaderboardManager lm = LeaderboardManager.getInstance();
			lm.resetLeaderboardTotal(appID, leaderboardID, userAuth);
			return Response.ok().entity("Leaderboard reset total!").build();
		} else {
			// Invalid data
			ErrorData errorData = ErrorData.getInstance();
			return errorData.getData("12");
		}
	}

}
