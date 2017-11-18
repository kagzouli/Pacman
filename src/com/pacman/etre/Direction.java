package com.pacman.etre;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Interface gerant les directions.<br/>
 * @author GZOULI
 *
 */
public final class Direction {
	
	private static final Log LOG = LogFactory.getLog(Direction.class);
	
	private Direction(){
		LOG.debug(new StringBuilder("Constructeur de la classe ").append(Direction.class).toString());
	}
	
	/**
	 * Direction haut.<br/>
	 */
	public static final int DIRECTION_HAUT = 0;
	
	/**
	 * Direction gauche.<br/>
	 */
	public static final int DIRECTION_GAUCHE = 1;
	
	
	/**
	 * Direction droite.<br/>
	 */
	public static final int DIRECTION_DROITE = 2;
	
	
	/**
	 * Direction bas.<br/>
	 */
	public static final int DIRECTION_BAS = 3;
	
	

}
