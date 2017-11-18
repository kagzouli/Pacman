package com.pacman.exception;

/**
 * Erreur technique.<br/>
 *
 */
public class TechnicalException extends Exception
{
	public static final long serialVersionUID = 32333436661743L;

    
    public TechnicalException(final String message){
        super(message);
    }
    
    public TechnicalException(final Throwable throwable){
        super(throwable);
    }

}
