package com.pacman.element;

import java.awt.Image;

import com.pacman.strategydeplacement.algoaetoile.Mover;

/**
 * Classe mere de tous les elements.<br/>
 * @author GZOULI
 *
 */
public abstract class AbstractElement implements Mover {
	
    protected int indexNumLigne;
    
    protected int indexNumColonne;
    
	
	public abstract Image getImageElement();
	
	/**
	 * Methode permettant de savoir si un element peut etre traverse par pacman
	 * @return
	 */
	public abstract boolean isAccessible();
	
	/**
	 * Methode permettant de savoir si l'element courant tue pacman.<br/>
	 * @return
	 */
	public abstract boolean tuePacman();

	/**
	 * @return the indexNumLigne
	 */
	public int getIndexNumLigne() {
		return indexNumLigne;
	}

	/**
	 * @param indexNumLigne the indexNumLigne to set
	 */
	public void setIndexNumLigne(final int indexNumLigne) {
		this.indexNumLigne = indexNumLigne;
	}

	/**
	 * @return the indexNumColonne
	 */
	public int getIndexNumColonne() {
		return indexNumColonne;
	}

	/**
	 * @param indexNumColonne the indexNumColonne to set
	 */
	public void setIndexNumColonne(final int indexNumColonne) {
		this.indexNumColonne = indexNumColonne;
	}

	

}
