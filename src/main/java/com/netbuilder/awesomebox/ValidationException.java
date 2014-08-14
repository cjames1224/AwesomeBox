package com.netbuilder.awesomebox;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class ValidationException extends RuntimeException{
	
	public ValidationException(String message){
		super(message);
	}

}
