package com.pacman.strategydeplacement;

import com.pacman.aire.grille.ITable;
import com.pacman.etre.AbstractFantome;

public interface Strategy {

	/**
	 * Strategy permettant de deplacer un fantome dans une grille.<br/>
	 * @param fantome Fantome.<br/>
	 * @param grille Grille.<br/>
	 */
	void deplacer(AbstractFantome fantome, ITable grille);

}
