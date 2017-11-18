package com.pacman.strategydeplacement;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.pacman.aire.grille.ICell;
import com.pacman.aire.grille.ITable;
import com.pacman.element.AbstractElement;
import com.pacman.element.ElementVide;
import com.pacman.etre.AbstractFantome;
import com.pacman.etre.Direction;

public class StrategyAleatoire implements Strategy {
	
	private static final Log LOG = LogFactory.getLog(StrategyAleatoire.class);

	public void deplacer(final AbstractFantome fantome,final ITable grilleJoueur) {
		if (LOG.isDebugEnabled()){
			LOG.debug("Deplacement du fantome");
		}
		
		int indexPosPossible = 1;
		final Map<Integer,Integer> mapDirection = new HashMap<Integer,Integer>();
		// Gauche
		final ICell cellGaucheFantome = grilleJoueur.getCell(fantome.getIndexNumLigne(), fantome.getIndexNumColonne() - 1);
		final AbstractElement elPresentGauche = cellGaucheFantome.getElement();
		final boolean isPresentGauche = elPresentGauche instanceof AbstractFantome;
		if ((elPresentGauche.isAccessible()) && (!isPresentGauche)) {
			mapDirection.put(indexPosPossible, Direction.DIRECTION_GAUCHE);
			indexPosPossible++;
		}

		// Droite
		final ICell cellDroiteFantome = grilleJoueur.getCell(fantome.getIndexNumLigne(), fantome.getIndexNumColonne() + 1);
		final AbstractElement elPresentDroite = cellDroiteFantome.getElement();
		final boolean isPresentDroite = elPresentDroite instanceof AbstractFantome;
		if ((elPresentDroite.isAccessible()) && (!isPresentDroite)) {
			mapDirection.put(indexPosPossible, Direction.DIRECTION_DROITE);
			indexPosPossible++;
		}

		// Bas
		final ICell cellBasFantome = grilleJoueur.getCell(fantome.getIndexNumLigne() + 1, fantome.getIndexNumColonne());
		final AbstractElement elPresentBas = cellBasFantome.getElement();
		final boolean isPresentBas = elPresentBas instanceof AbstractFantome;
		if ((elPresentBas.isAccessible()) && (!isPresentBas)) {
			mapDirection.put(indexPosPossible, Direction.DIRECTION_BAS);
			indexPosPossible++;
		}

		// Haut
		final ICell cellHautFantome = grilleJoueur.getCell(fantome.getIndexNumLigne() - 1, fantome.getIndexNumColonne());
		final AbstractElement elPresentHaut = cellHautFantome.getElement();
		final boolean isPresentHaut = elPresentHaut instanceof AbstractFantome;
		if ((elPresentHaut.isAccessible()) && (!isPresentHaut)) {
			mapDirection.put(indexPosPossible, Direction.DIRECTION_HAUT);
			indexPosPossible++;
		}

		// On recupere la direction et l'element sur lequel va aller le fantome
		final double nombreAuHasard = Math.random();
		final int identifiant = ((int) (nombreAuHasard * mapDirection.size())) + 1;
		final int direction = (Integer) mapDirection.get(identifiant);

		this.deplacer(grilleJoueur,fantome,direction);
	}
	
	/**
	 * Methode permettant de deplacer le fantome dans une direction.<br/>
	 * 
	 * @param grilleJoueur
	 *            grille joueur.<br/>
	 * @param fantome Fantome.<br/>
	 * @param direction
	 *            Direction.<br/>
	 */
	protected void deplacer(final ITable grilleJoueur,final AbstractFantome fantome,final int direction) {
		if (direction == Direction.DIRECTION_GAUCHE) {
			this.deplacerGauche(fantome,grilleJoueur);
		} else if (direction == Direction.DIRECTION_DROITE) {
			this.deplacerDroite(fantome,grilleJoueur);
		} else if (direction == Direction.DIRECTION_BAS) {
			this.deplacerBas(fantome,grilleJoueur);
		} else if (direction == Direction.DIRECTION_HAUT) {
			this.deplacerHaut(fantome,grilleJoueur);
		}
	}

	/**
	 * Methode permettant de deplacer fantome2 a gauche.<br/>
	 * @param fantome Fantome.<br/>
	 * @param grilleJoueur
	 *            Grille.<br/>
	 */
	protected void deplacerGauche(final AbstractFantome fantome,final ITable grilleJoueur) {
		grilleJoueur.getCell(fantome.getIndexNumLigne(), fantome.getIndexNumColonne() - 1).setElement(fantome);
		grilleJoueur.getCell(fantome.getIndexNumLigne(), fantome.getIndexNumColonne()).setElement(new ElementVide(fantome.getIndexNumLigne(), fantome.getIndexNumColonne()));
		fantome.setIndexNumColonne(fantome.getIndexNumColonne() - 1);
	}

	/**
	 * Methode permettant de deplacer fantome2 a droite.<br/>
	 * @param fantome Fantome.<br/>
	 * @param grilleJoueur
	 *            Grille.<br/>
	 */
	protected void deplacerDroite(final AbstractFantome fantome,final ITable grilleJoueur) {
		grilleJoueur.getCell(fantome.getIndexNumLigne(), fantome.getIndexNumColonne() + 1).setElement(fantome);
		grilleJoueur.getCell(fantome.getIndexNumLigne(), fantome.getIndexNumColonne()).setElement(new ElementVide(fantome.getIndexNumLigne(), fantome.getIndexNumColonne()));
		fantome.setIndexNumColonne(fantome.getIndexNumColonne() + 1);
	}
	
	/**
	 * Methode permettant de deplacer fantome2 en bas.<br/>
	 * @param fantome Fantome.<br/>
	 * @param grilleJoueur
	 *            Grille joueur.<br/>
	 */
	protected void deplacerBas(final AbstractFantome fantome,final ITable grilleJoueur) {
		grilleJoueur.getCell(fantome.getIndexNumLigne() + 1, fantome.getIndexNumColonne()).setElement(fantome);
		grilleJoueur.getCell(fantome.getIndexNumLigne(), fantome.getIndexNumColonne()).setElement(new ElementVide(fantome.getIndexNumLigne(), fantome.getIndexNumColonne()));
		fantome.setIndexNumLigne(fantome.getIndexNumLigne() + 1);
	}
	
	/**
	 * Methode permettant de deplacer fantome2 en haut.<br/>
	 * @param fantome Fantome.<br/>
	 * @param grilleJoueur
	 *            Grille joueur.<br/>
	 */
	protected void deplacerHaut(final AbstractFantome fantome,final ITable grilleJoueur) {
		grilleJoueur.getCell(fantome.getIndexNumLigne() - 1, fantome.getIndexNumColonne()).setElement(fantome);
		grilleJoueur.getCell(fantome.getIndexNumLigne(), fantome.getIndexNumColonne()).setElement(new ElementVide(fantome.getIndexNumLigne(), fantome.getIndexNumColonne()));
		fantome.setIndexNumLigne(fantome.getIndexNumLigne() - 1);
	}


}
