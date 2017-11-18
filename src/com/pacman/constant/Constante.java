package com.pacman.constant;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Classe contenant des constantes
 * @author x587368
 *
 */
public final class Constante
{
	private static final Log LOG = LogFactory.getLog(Constante.class);
	
	protected Constante(){
		LOG.debug(new StringBuilder("Constructeur de la classe").append(Constante.class).toString());
	}
    
    /**
     * Nom du bundle aire jeu.<br/>
     */
  //  public static final String BUN_NAME_AIJEU = "com.pacman.aire.airejeu";
	public static final String BUN_NAME_AIJEU = "airejeu";

    
    /**
     * Constante representant la cle signifiant la longueur de la fenetre.<br/>
     */
    public static final String CLE_LONGUEUR = "airejeu.longueur";

    /**
     * Constante representant la cle signifiant la largeur de la fenetre.<br/>
     */
    public static final String CLE_LARGEUR = "airejeu.largeur";
      
    /**
     * Temps du timer par default (En ms).
     */
    public static final String CLE_TEMPS_TIMER= "airejeu.timer";
    
    /**
     * Booleen pour savoir si on met de la musique dans le jeu.<br/>
     */
    public static final String IS_SOUND_GAME = "musique.sound";
    

}
