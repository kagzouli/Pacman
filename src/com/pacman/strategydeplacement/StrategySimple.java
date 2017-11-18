package com.pacman.strategydeplacement;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.pacman.aire.grille.ICell;
import com.pacman.aire.grille.ITable;
import com.pacman.element.AbstractElement;
import com.pacman.element.ElementVide;
import com.pacman.etre.AbstractFantome;
import com.pacman.etre.Direction;
import com.pacman.etre.Pacman;

public class StrategySimple implements Strategy {

	public void deplacer(final AbstractFantome fantome,final ITable grille) {
		// Trouve pacman.
		final ICell cellPacman = (ICell) grille.findPacman();
		if (cellPacman != null) {
			final Pacman pacman = (Pacman) cellPacman.getElement();

			// Choix des deplacements preferees pour le fantome
			final Map<Integer,Integer> positionPrefere = this.recherchePositionPreferee(fantome, pacman);

			final Map<Integer,Integer> mapDirectionPos = this.getMapDirection(fantome, grille);

			final Iterator<Integer> itPositionPref = positionPrefere.keySet().iterator();
			while (itPositionPref.hasNext()) {
				final Integer positionAPrendre = (Integer) positionPrefere.get((Integer) itPositionPref.next());

				if (mapDirectionPos.get(positionAPrendre) != null) {
					this.deplacer(grille, fantome, positionAPrendre);
					break;
				}

			}
		}

	}

	/**
	 * Remplit la map de direction.<br/>
	 * 
	 * @param fantome
	 *            Fantome.<br/>
	 * @param grilleJoueur
	 *            Grille du joueur.<br/>
	 * @return Retourne la position preferee.
	 */
	protected Map<Integer,Integer> getMapDirection(final AbstractFantome fantome,final ITable grilleJoueur) {
		final Map<Integer,Integer> mapDirection = new HashMap<Integer,Integer>();

		int indexPosPossible = 1;
		// Gauche
		final ICell cellGaucheFantome = grilleJoueur.getCell(fantome.getIndexNumLigne(), fantome.getIndexNumColonne() - 1);
		final AbstractElement elPresentGauche = cellGaucheFantome.getElement();
		final boolean isPresentGauche = elPresentGauche instanceof AbstractFantome;
		if ((elPresentGauche.isAccessible()) && (!isPresentGauche)) {
			mapDirection.put(Direction.DIRECTION_GAUCHE, Direction.DIRECTION_GAUCHE);
			indexPosPossible++;
		}

		// Droite
		final ICell cellDroiteFantome = grilleJoueur.getCell(fantome.getIndexNumLigne(), fantome.getIndexNumColonne() + 1);
		final AbstractElement elPresentDroite = cellDroiteFantome.getElement();
		final boolean isPresentDroite = elPresentDroite instanceof AbstractFantome;
		if ((elPresentDroite.isAccessible()) && (!isPresentDroite)) {
			mapDirection.put(Direction.DIRECTION_DROITE, Direction.DIRECTION_DROITE);
			indexPosPossible++;
		}

		// Bas
		final ICell cellBasFantome = grilleJoueur.getCell(fantome.getIndexNumLigne() + 1, fantome.getIndexNumColonne());
		final AbstractElement elPresentBas = cellBasFantome.getElement();
		final boolean isPresentBas = elPresentBas instanceof AbstractFantome;
		if ((elPresentBas.isAccessible()) && (!isPresentBas)) {
			mapDirection.put(Direction.DIRECTION_BAS, Direction.DIRECTION_BAS);
			indexPosPossible++;
		}

		// Haut
		final ICell cellHautFantome = grilleJoueur.getCell(fantome.getIndexNumLigne() - 1, fantome.getIndexNumColonne());
		final AbstractElement elPresentHaut = cellHautFantome.getElement();
		final boolean isPresentHaut = elPresentHaut instanceof AbstractFantome;
		if ((elPresentHaut.isAccessible()) && (!isPresentHaut)) {
			mapDirection.put(Direction.DIRECTION_HAUT, Direction.DIRECTION_HAUT);
			indexPosPossible++;
		}
		return mapDirection;
	}

	protected Map<Integer,Integer> recherchePositionPreferee(final AbstractFantome fantome,final Pacman pacman) {

		final Map<Integer,Integer> positionPrefFinal = new HashMap<Integer,Integer>();
		boolean depPrefGauche = true;
		boolean depPrefereHaut = true;

		final int indNumLignePac = pacman.getIndexNumLigne();
		final int indNumColonnePac = pacman.getIndexNumColonne();
		final int indNumLigneFant = fantome.getIndexNumLigne();
		final int indNumColonneFant = fantome.getIndexNumColonne();

		// Gauche/Droite
		final int ecartGaucheDroite = Math.abs(indNumColonnePac - indNumColonneFant);
		if (indNumColonneFant < indNumColonnePac) {
			depPrefGauche = false;
		} else {
			depPrefGauche = true;
		}

		// Haut/Bas
		final int ecartHautBas = Math.abs(indNumLignePac - indNumLigneFant);
		if (indNumLigneFant < indNumLignePac) {
			depPrefereHaut = false;
		} else {
			depPrefereHaut = true;
		}

		// Choix entre gauche et haut
		if ((depPrefGauche) && (depPrefereHaut)) {
			if (ecartGaucheDroite < ecartHautBas) {
				positionPrefFinal.put(1, Direction.DIRECTION_GAUCHE);
				positionPrefFinal.put(2, Direction.DIRECTION_HAUT);
			} else {
				positionPrefFinal.put(1, Direction.DIRECTION_HAUT);
				positionPrefFinal.put(2, Direction.DIRECTION_GAUCHE);
			}
			positionPrefFinal.put(3, Direction.DIRECTION_DROITE);
			positionPrefFinal.put(4, Direction.DIRECTION_BAS);

		}// Choix entre droite et haut
		else if ((!depPrefGauche) && (depPrefereHaut)) {
			if (ecartGaucheDroite < ecartHautBas) {
				positionPrefFinal.put(1, Direction.DIRECTION_DROITE);
				positionPrefFinal.put(2, Direction.DIRECTION_HAUT);
			} else {
				positionPrefFinal.put(1, Direction.DIRECTION_HAUT);
				positionPrefFinal.put(2, Direction.DIRECTION_DROITE);
			}
			positionPrefFinal.put(3, Direction.DIRECTION_GAUCHE);
			positionPrefFinal.put(4, Direction.DIRECTION_BAS);

		}
		// Choix entre droite et bas
		else if ((!depPrefGauche) && (!depPrefereHaut)) {
			if (ecartGaucheDroite < ecartHautBas) {
				positionPrefFinal.put(1, Direction.DIRECTION_DROITE);
				positionPrefFinal.put(2, Direction.DIRECTION_BAS);
			} else {
				positionPrefFinal.put(1, Direction.DIRECTION_BAS);
				positionPrefFinal.put(2, Direction.DIRECTION_DROITE);
			}
			positionPrefFinal.put(3, Direction.DIRECTION_GAUCHE);
			positionPrefFinal.put(4, Direction.DIRECTION_HAUT);

		}
		// Choix entre gauche et bas
		else if ((depPrefGauche) && (!depPrefereHaut)) {
			if (ecartGaucheDroite < ecartHautBas) {
				positionPrefFinal.put(1, Direction.DIRECTION_GAUCHE);
				positionPrefFinal.put(2, Direction.DIRECTION_BAS);

			} else {
				positionPrefFinal.put(1, Direction.DIRECTION_BAS);
				positionPrefFinal.put(2, Direction.DIRECTION_GAUCHE);
			}
			positionPrefFinal.put(3, Direction.DIRECTION_DROITE);
			positionPrefFinal.put(4, Direction.DIRECTION_HAUT);

		}
		return positionPrefFinal;
	}

	/**
	 * Methode permettant de deplacer le fantome dans une direction.<br/>
	 * 
	 * @param grilleJoueur
	 *            grille joueur.<br/>
	 * @param fantome
	 *            Fantome.<br/>
	 * @param direction
	 *            Direction.<br/>
	 */
	protected void deplacer(final ITable grilleJoueur,final AbstractFantome fantome,final int direction) {
		if (direction == Direction.DIRECTION_GAUCHE) {
			this.deplacerGauche(fantome, grilleJoueur);
		} else if (direction == Direction.DIRECTION_DROITE) {
			this.deplacerDroite(fantome, grilleJoueur);
		} else if (direction == Direction.DIRECTION_BAS) {
			this.deplacerBas(fantome, grilleJoueur);
		} else if (direction == Direction.DIRECTION_HAUT) {
			this.deplacerHaut(fantome, grilleJoueur);
		}
	}

	/**
	 * Methode permettant de deplacer fantome2 a gauche.<br/>
	 * 
	 * @param fantome
	 *            Fantome.<br/>
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
	 * 
	 * @param fantome
	 *            Fantome.<br/>
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
	 * 
	 * @param fantome
	 *            Fantome.<br/>
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
	 * 
	 * @param fantome
	 *            Fantome.<br/>
	 * @param grilleJoueur
	 *            Grille joueur.<br/>
	 */
	protected void deplacerHaut(final AbstractFantome fantome,final ITable grilleJoueur) {
		grilleJoueur.getCell(fantome.getIndexNumLigne() - 1, fantome.getIndexNumColonne()).setElement(fantome);
		grilleJoueur.getCell(fantome.getIndexNumLigne(), fantome.getIndexNumColonne()).setElement(new ElementVide(fantome.getIndexNumLigne(), fantome.getIndexNumColonne()));
		fantome.setIndexNumLigne(fantome.getIndexNumLigne() - 1);
	}

}
