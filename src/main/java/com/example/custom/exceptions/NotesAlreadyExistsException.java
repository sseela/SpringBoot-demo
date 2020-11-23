package com.example.custom.exceptions;

public class NotesAlreadyExistsException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4403951929713632770L;

	public NotesAlreadyExistsException(String msg) {
		super(msg);
	}
}
