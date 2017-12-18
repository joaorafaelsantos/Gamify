package com.gamify.api;

public class AchievementsResource {

	public AchievementsResource() {
		// TODO Auto-generated constructor stub
	}

}


//package com.gamify.api;
//
//
//import java.util.List;
//
//import javax.ws.rs.Consumes;
//import javax.ws.rs.DELETE;
//import javax.ws.rs.FormParam;
//import javax.ws.rs.GET;
//import javax.ws.rs.POST;
//import javax.ws.rs.Path;
//import javax.ws.rs.PathParam;
//import javax.ws.rs.Produces;
//import javax.ws.rs.QueryParam;
//import javax.ws.rs.core.Context;
//import javax.ws.rs.core.MediaType;
//import javax.ws.rs.core.Response;
//import javax.ws.rs.core.UriBuilder;
//import javax.ws.rs.core.UriInfo;
//
//import com.gamify.impl.GameManager;
//import com.gamify.model.Game;
//
//@Path("/games")
//public class GamesResource {
//
//	// GET all the games
//	@GET
//	@Produces(MediaType.APPLICATION_JSON)
//	public List<Game> getGames(@QueryParam("category") String category) {
//		
//		GameManager gm = GameManager.getInstance();		
//		if (category!=null) {
//			return gm.getGames(category);
//		} 
//		return gm.getGames();
//	}
//	
//	// GET a specific game
//	@Path("/{gameId}")
//	@GET
//	@Produces(MediaType.APPLICATION_JSON)
//	public Game getGame(@PathParam("gameId") String title) {
//		
//		GameManager gm = GameManager.getInstance();		
//		return gm.getGame(title);
//	}
//	
//	// DELETE a specific game
//	@Path("/{gameId}")
//	@DELETE	
//	public Response removeGame(@PathParam("gameId") String title) {
//		
//		GameManager gm = GameManager.getInstance();		
//		gm.removeGame(title);
//		
//		return Response.ok().entity("Game removed!").build();
//	}
//	
//	//Post a new game
//	@POST
//	@Consumes("application/x-www-form-urlencoded")
//	public Response insertGame(
//			@FormParam("title") String title,
//			@FormParam("company") String company,
//			@FormParam("year") int year,
//			@FormParam("category") String category,
//			@Context UriInfo uriInfo) {
//		
//		GameManager gm = GameManager.getInstance();
//		gm.createGame(title, company, year, category);
//					
//		UriBuilder builder = uriInfo.getAbsolutePathBuilder();	
//		builder.path(title);
//		return Response.created(builder.build()).build();
//	}
//	
//}
//
//
//
