package com.gamify.impl;

import java.util.ArrayList;
import java.util.List;

import com.gamify.data.ErrorData;
import com.gamify.interf.InterfaceError;
import com.gamify.model.Error;

public class ErrorManager implements InterfaceError {

	static List<Error> errors = new ArrayList<Error>();
	static ErrorManager em = null;

	public static ErrorManager getInstance() {
		if (em == null) {
			em = new ErrorManager();
		}
		return em;
	}

	// Get all errors

	@Override
	public List<Error> getErrors() {
		ErrorData errorData = ErrorData.getInstance();
		return errorData.getData();
	}

	// Get specific user

	@Override
	public Error getError(String errorID) {
		ErrorData errorData = ErrorData.getInstance();
		return errorData.getData(errorID);
	}

}
