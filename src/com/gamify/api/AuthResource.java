package com.gamify.api;

import java.util.HashMap;
import java.util.Map;

import com.gamify.model.Error;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import com.gamify.data.ErrorData;
import com.gamify.impl.AuthManager;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Path("/auth")
public class AuthResource {

	// POST auth
	@POST
	@Consumes("application/x-www-form-urlencoded")

	public Response auth(@FormParam("username") String username, @FormParam("password") String password) {

		if (username != null && password != null) {
			AuthManager am = AuthManager.getInstance();
			boolean validate;

			// Validate user data in db store

			validate = am.getAuth(username, password);

			if (validate) {

				// Create user data
				Map<String, Object> user = new HashMap<String, Object>();
				user.put("username", username);

				// Create JWT token
				String newToken = Jwts.builder().setClaims(user).setSubject(username).setIssuer("Gamify")
						.signWith(SignatureAlgorithm.HS256, am.getKey()).compact();

				// Send token to the client
				Object obj = newToken;
				return Response.ok().entity(newToken).build();
			} else {
				// Invalid user
				ErrorData errorData = ErrorData.getInstance();
				Error error = errorData.getData("5");
				return Response.serverError().status(Integer.parseInt(error.getHttp_status())).type("text/plain")
						.entity(error.getMessage()).build();
			}
		} else {
			// Invalid data
			ErrorData errorData = ErrorData.getInstance();
			Error error = errorData.getData("12");
			return Response.serverError().status(Integer.parseInt(error.getHttp_status())).type("text/plain")
					.entity(error.getMessage()).build();
		}

	}
}
