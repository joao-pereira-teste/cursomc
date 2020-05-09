package com.estudandoemcasa03.cursomc.services.exceptions;

public class ObjectNotFoundExcepction extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
public ObjectNotFoundExcepction(String msg) {
	
	super(msg);
}
	
public ObjectNotFoundExcepction(String msg,Throwable cause) {
	
	super(msg,cause);
}

}
