package com.pacman.etre;

import com.pacman.aire.grille.ITable;
import com.pacman.strategydeplacement.Strategy;

/**
 * Classe abstraite des fantomes.
 * @author GZOULI
 *
 */
public abstract class AbstractFantome extends AbstractElementMobile{

	/**
	 * Strategie.<br/>
	 */
	protected transient Strategy strategy;
	
	/**
	 * Methode permettant de deplacer fantome2.<br/>
	 * 
	 * @param grilleJoueur
	 *            Grille joueur.<br/>
	 */
	public void deplacer(final ITable grilleJoueur) {
		strategy.deplacer(this, grilleJoueur);
	}

	
}
