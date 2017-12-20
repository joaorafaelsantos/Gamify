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
		}
		return em;
	}

	@Override
	public List<Error> getErrors() {
		return errors;
	}

	@Override
	public Error getError(int idError) {
		for (Iterator<Error> iterator = errors.iterator(); iterator.hasNext();) {
			Error e = (Error) iterator.next();
			if(e.getIdError() == idError)
				return e;
		}
		return null;
	}

}

