package com.exception;

public class ServiciosException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ServiciosException(String mensaje, Throwable t) {
		super(mensaje, t);
	}
	
	public ServiciosException(String mensaje) {
		super(mensaje);
	}
}
