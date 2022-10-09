package com.atos.af.services.exceptions;

public abstract class FunctionalException extends Exception{
	private final String code;

    protected FunctionalException(final String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
