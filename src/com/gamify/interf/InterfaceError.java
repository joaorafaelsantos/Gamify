package com.gamify.interf;

import java.util.List;
import com.gamify.model.Error;

public interface InterfaceError {

	public List<Error> getErrors();
	public Error getError(int idError);
	
}
