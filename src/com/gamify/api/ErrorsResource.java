package com.gamify.api;


import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.gamify.impl.AuthManager;
import com.gamify.impl.ErrorManager;
import com.gamify.model.Error;

import io.jsonwebtoken.Jwts;

@Path("/errors")
public class ErrorsResource {
	
	// Get all errors
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Error> getErrors() {

		ErrorManager em = ErrorManager.getInstance();		
		return em.getErrors();
	}
	
	// GET a specific error
	@Path("/{errorID}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Error getError(@PathParam("errorID") String errorID) {
		
		ErrorManager em = ErrorManager.getInstance();	
		return em.getError(errorID);
	}

}



