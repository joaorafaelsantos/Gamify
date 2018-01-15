package com.gamify.api;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;


import com.gamify.impl.AuthManager;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Path("/auth")
public class AuthResource {
	
	// POST auth)
	@POST	
	@Consumes("application/x-www-form-urlencoded")
	
	public Response auth(@FormParam("username") String username, @FormParam("password") String password) {
		AuthManager am = AuthManager.getInstance();	
		boolean validate;
		
		// Validate user data in db store
		
		validate= am.getAuth(username,password);
		
		if(validate) {
			
			
			// Create user data 
			Map<String,Object> user = new HashMap<String,Object>();
			user.put("username", username);
			
			
			// Create JWT token
			String newToken = Jwts.builder()				  
					  .setClaims(user)
					  .setIssuer("Gamify")			
					  .signWith(SignatureAlgorithm.HS512, am.getKey())				  
					  .compact();
			
			// Send token to the client
			return Response.ok().entity(newToken).build();
		} else {
			// Invalid user
			return Response.serverError().status(401).type("text/plain").entity("Invalid User!").build();
		}
		
		
	}
}
