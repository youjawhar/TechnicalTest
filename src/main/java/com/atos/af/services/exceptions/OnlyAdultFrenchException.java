package com.atos.af.services.exceptions;

public class OnlyAdultFrenchException extends FunctionalException {
	
	private static final String CODE_EXCEPTION = "ERREUR-1";
	
	
	
	public OnlyAdultFrenchException() {
		super(CODE_EXCEPTION);
	}

	@Override
	 public String getMessage() {
	      return "Only adult French residents are allowed to create an account";
	 }
}
