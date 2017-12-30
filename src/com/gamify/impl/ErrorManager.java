package com.gamify.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.gamify.interf.InterfaceError;
import com.gamify.model.Error;



public class ErrorManager implements InterfaceError {

	static List<Error> errors = new ArrayList<Error>();
	static ErrorManager em = null;

	public static ErrorManager getInstance() {
		if (em == null) {
			em = new ErrorManager();
			Error e1 = new Error(1, "Username already exists", "/api/errors/1", 409);
			Error e2 = new Error(2, "Email already exists", "/api/errors/2", 409);
			errors.add(e1);
			errors.add(e2);
		}
		return em;
	}

	// Get errors

	@Override
	public List<Error> getErrors() {
		if (errors.size() != 0) {
			return errors;
		}
		else {
			// There are no error with that ID - TO DO: Send error
			return null;
		}

	}

	// Get specific error

	@Override
	public Error getError(int idError) {
		Error tempError = null;
		if (errors.size() != 0) {
			for (Iterator<Error> iterator = errors.iterator(); iterator.hasNext();) {
				Error e = (Error) iterator.next();
				if(e.getErrorID() == idError)
					tempError = e;
			}
			return tempError;
		}
		else {
			// There are no error with that ID - TO DO: Send error
			return null;
		}
	}

}

