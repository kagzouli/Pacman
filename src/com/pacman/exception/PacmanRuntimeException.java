package com.pacman.exception;

public class PacmanRuntimeException extends RuntimeException{

	public static final long serialVersionUID = 34332435632773L;

    
    public PacmanRuntimeException(final String message){
        super(message);
    }
    
    public PacmanRuntimeException(final Throwable throwable){
        super(throwable);
    }
}
