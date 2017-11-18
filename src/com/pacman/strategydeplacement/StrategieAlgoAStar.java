package com.pacman.strategydeplacement;

import com.pacman.aire.AireJeu;
import com.pacman.aire.grille.ICell;
import com.pacman.aire.grille.ITable;
import com.pacman.element.ElementVide;
import com.pacman.etre.AbstractFantome;
import com.pacman.etre.Fantome1;
import com.pacman.etre.Fantome2;
import com.pacman.etre.Fantome3;
import com.pacman.etre.Pacman;
import com.pacman.strategydeplacement.algoaetoile.AStarPathFinder;
import com.pacman.strategydeplacement.algoaetoile.Path;
import com.pacman.strategydeplacement.algoaetoile.PathFinder;

/**
 * Strategie permettant de deplacer le fantome via l'algorithme de recherche du
 * plus court chemin A*.<br/>
 * 
 * @author GZOULI
 * 
 */
public class StrategieAlgoAStar implements Strategy {

	/** The path finder we'll use to search our map */
	private transient PathFinder finder;

	private transient Path path;

	public void deplacer(final AbstractFantome fantome, final ITable grille) {
		final ICell cellPacman = (ICell) grille.findPacman();
		if (cellPacman != null) {
			final Pacman pacman = (Pacman) cellPacman.getElement();
			if (finder == null) {
				finder = new AStarPathFinder(grille, 500, false);
			}
			path = finder.findPath(pacman, fantome.getIndexNumColonne(),
					fantome.getIndexNumLigne(), pacman.getIndexNumColonne(),
					pacman.getIndexNumLigne());

			int posX = path.getX(1);
			int posY = path.getY(1);
			boolean isOccupedByFantome = this
					.isCellOccupedByFantome(posX, posY);

			if (!isOccupedByFantome) {
				grille.getCell(fantome.getIndexNumLigne(),
						fantome.getIndexNumColonne()).setElement(
						new ElementVide(fantome.getIndexNumLigne(), fantome
								.getIndexNumColonne()));
				grille.getCell(posY, posX).setElement(fantome);
				fantome.setIndexNumLigne(posY);
				fantome.setIndexNumColonne(posX);
			}else{
				StrategyAleatoire strategyAleatoire  = new StrategyAleatoire();
				strategyAleatoire.deplacer(fantome, grille);
			}


		}
	}

	/**
	 * Methode permettant de verifier si cette cellule est occupee par un
	 * fantome.<br/>
	 * 
	 * @param posX
	 * @param posY
	 * @return
	 */
	private boolean isCellOccupedByFantome(int posX, int posY) {

		final Fantome1 fantome1 = (Fantome1) AireJeu.getInstance()
				.getGrilleJoueur().findFantome1().getElement();
		final Fantome2 fantome2 = (Fantome2) AireJeu.getInstance()
				.getGrilleJoueur().findFantome2().getElement();
		final Fantome3 fantome3 = (Fantome3) AireJeu.getInstance()
				.getGrilleJoueur().findFantome3().getElement();

		if ((fantome1.getIndexNumColonne() == posX)
				&& (fantome1.getIndexNumLigne() == posY)) {
			return true;
		}

		if ((fantome2.getIndexNumColonne() == posX)
				&& (fantome2.getIndexNumLigne() == posY)) {
			return true;
		}

		if ((fantome3.getIndexNumColonne() == posX)
				&& (fantome3.getIndexNumLigne() == posY)) {
			return true;
		}

		return false;

	}

}
