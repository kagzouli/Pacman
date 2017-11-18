package com.pacman.etre;

import java.awt.Image;
import java.awt.Toolkit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.pacman.aire.AireJeu;
import com.pacman.aire.grille.ICell;
import com.pacman.aire.grille.ITable;
import com.pacman.element.AbstractElement;
import com.pacman.element.ElementVide;
import com.pacman.elementpt.AbstractElementAManger;

public class Pacman extends AbstractElementMobile {

	private static final Log LOG = LogFactory.getLog(Pacman.class);

	/**
	 * Image pacman allant a gauche.<br/>
	 */
	private transient Image imagePacmanGauche = null;

	/**
	 * Image pacman allant a droite.<br/>
	 */
	private transient Image imagePacmanDroite = null;

	
	/**
	 * Image pacman allant en bas.<br/>
	 */
	private transient Image imagePacmanBas = null;

	/**
	 * Image pacman allant en haut.<br/>
	 */
	private transient Image imagePacmanHaut = null;

	private int score;

	/**
	 * Repertoire courant.<br/>
	 */

	private transient int direction;

	private boolean vivant;

	public Pacman(final int indexNumLigne,final int indexNumColonne) {
		super();
		this.indexNumLigne = indexNumLigne;
		this.indexNumColonne = indexNumColonne;
		this.direction = Direction.DIRECTION_DROITE;
		this.score = 0;
		this.vivant = true;

		// Initialisation vue pacman gauche
		if (this.imagePacmanGauche == null) {
			this.imagePacmanGauche = Toolkit.getDefaultToolkit().getImage(Pacman.class.getResource("/image/pacmanGauche.gif"));
		}

		// Initialisation vue pacman droite
		if (this.imagePacmanDroite == null) {
			this.imagePacmanDroite = Toolkit.getDefaultToolkit().getImage(Pacman.class.getResource("/image/pacmanDroite.gif"));
		}

		// Initialisation vue pacman haut
		if (this.imagePacmanHaut == null) {
			this.imagePacmanHaut = Toolkit.getDefaultToolkit().getImage(Pacman.class.getResource("/image/pacmanHaut.gif"));
		}

		// Initialisation vue pacman bas
		if (this.imagePacmanBas == null) {
			this.imagePacmanBas = Toolkit.getDefaultToolkit().getImage(Pacman.class.getResource("/image/pacmanBas.gif"));
		}
	}

	@Override
	public Image getImageElement() {
		Image imagePacman = null;

		if (this.direction == Direction.DIRECTION_GAUCHE) {
			imagePacman = imagePacmanGauche;
		} else if (this.direction == Direction.DIRECTION_DROITE) {
			imagePacman = imagePacmanDroite;
		} else if (this.direction == Direction.DIRECTION_HAUT) {
			imagePacman = imagePacmanHaut;
		} else if (this.direction == Direction.DIRECTION_BAS) {
			imagePacman = imagePacmanBas;
		}

		return imagePacman;
	}

	@Override
	public boolean isAccessible() {
		return true;
	}

	@Override
	public boolean tuePacman() {
		return true;
	}

	/**
	 * Methode permettant de deplacer pacman a gauche.<br/>
	 * 
	 * @param grilleJoueur
	 *            Grille.<br/>
	 */
	public void deplacerGauche(final ITable grilleJoueur) {

		final int indexNumLigne = this.getIndexNumLigne();
		final int indexNumColonne = this.getIndexNumColonne();

		final ICell cellVolDepPacman = grilleJoueur.getCell(indexNumLigne, indexNumColonne - 1);
		final AbstractElement elPresNewPosPac = cellVolDepPacman.getElement();

		if (elPresNewPosPac.isAccessible()) {
			// Si pacman rencontre un element a manger, le score est modifie
			if (elPresNewPosPac instanceof AbstractElementAManger) {
				this.score = this.score + ((AbstractElementAManger) elPresNewPosPac).rapportePoint();
			}

			// pacman rencontre un des fantomes.
			if (elPresNewPosPac instanceof AbstractFantome) {
				this.vivant = false;
			} else {
				this.setIndexNumColonne(indexNumColonne - 1);
				grilleJoueur.getCell(indexNumLigne, indexNumColonne - 1).setElement(this);
				grilleJoueur.getCell(indexNumLigne, indexNumColonne).setElement(new ElementVide(indexNumLigne, indexNumColonne));
			}

		}

		this.direction = Direction.DIRECTION_GAUCHE;
		AireJeu.getInstance().repaint();
	}

	/**
	 * Methode permettant de deplacer pacman a droite.<br/>
	 * 
	 * @param grilleJoueur
	 *            Grille.<br/>
	 */
	public void deplacerDroite(final ITable grilleJoueur) {
		final int indexNumLigne = this.getIndexNumLigne();
		final int indexNumColonne = this.getIndexNumColonne();

		
		final ICell cellVolDepPacman = grilleJoueur.getCell(indexNumLigne, indexNumColonne + 1);
		final AbstractElement elPresNewPosPac = cellVolDepPacman.getElement();

		if (elPresNewPosPac.isAccessible()) {
			if (elPresNewPosPac instanceof AbstractElementAManger) {
				this.score = this.score + ((AbstractElementAManger) elPresNewPosPac).rapportePoint();
			}

			// pacman rencontre un des fantomes.
			if (elPresNewPosPac instanceof AbstractFantome) {
				this.vivant = false;
			} else {
				this.setIndexNumColonne(indexNumColonne + 1);
				grilleJoueur.getCell(indexNumLigne, indexNumColonne + 1).setElement(this);
				grilleJoueur.getCell(indexNumLigne, indexNumColonne).setElement(new ElementVide(indexNumLigne, indexNumColonne));
			}
		}

		this.direction = Direction.DIRECTION_DROITE;
		AireJeu.getInstance().repaint();
	}

	/**
	 * Methode permettant de deplacer pacman en bas.<br/>
	 * 
	 * @param grilleJoueur
	 *            Grille joueur.<br/>
	 */
	public void deplacerBas(final ITable grilleJoueur) {
		final int indexNumLigne = this.getIndexNumLigne();
		final int indexNumColonne = this.getIndexNumColonne();
		
	
		final ICell cellVolDepPacman = grilleJoueur.getCell(indexNumLigne + 1, indexNumColonne);
		final AbstractElement elPresNewPosPac = cellVolDepPacman.getElement();

		if (elPresNewPosPac.isAccessible()) {
			if (elPresNewPosPac instanceof AbstractElementAManger) {
				this.score = this.score + ((AbstractElementAManger) elPresNewPosPac).rapportePoint();
			}

			// pacman rencontre un des fantomes.
			if (elPresNewPosPac instanceof AbstractFantome) {
				this.vivant = false;
			} else {
				this.setIndexNumLigne(indexNumLigne + 1);
				grilleJoueur.getCell(indexNumLigne + 1, indexNumColonne).setElement(this);
				grilleJoueur.getCell(indexNumLigne, indexNumColonne).setElement(new ElementVide(indexNumLigne, indexNumColonne));
			}
		}

		this.direction = Direction.DIRECTION_BAS;
		AireJeu.getInstance().repaint();
	}

	/**
	 * Methode permettant de deplacer pacman en haut.<br/>
	 * 
	 * @param grilleJoueur
	 *            Grille joueur.<br/>
	 */
	public void deplacerHaut(final ITable grilleJoueur) {
		final int indexNumLigne = this.getIndexNumLigne();
		final int indexNumColonne = this.getIndexNumColonne();
		
		final ICell cellVolDepPacman = grilleJoueur.getCell(indexNumLigne - 1, indexNumColonne);
		final AbstractElement elPresNewPosPac = cellVolDepPacman.getElement();

		if (elPresNewPosPac.isAccessible()) {
			if (elPresNewPosPac instanceof AbstractElementAManger) {
				this.score = this.score + ((AbstractElementAManger) elPresNewPosPac).rapportePoint();
			}

			// pacman rencontre un des fantomes.
			if (elPresNewPosPac instanceof AbstractFantome) {
				this.vivant = false;
			} else {

				this.setIndexNumLigne(indexNumLigne - 1);
				grilleJoueur.getCell(indexNumLigne - 1, indexNumColonne).setElement(this);
				grilleJoueur.getCell(indexNumLigne, indexNumColonne).setElement(new ElementVide(indexNumLigne, indexNumColonne));
			}
		}

		this.direction = Direction.DIRECTION_HAUT;
		AireJeu.getInstance().repaint();

	}

	/**
	 * @return the score
	 */
	public int getScore() {
		return score;
	}

	/**
	 * @param score
	 *            the score to set
	 */
	public void setScore(final int score) {
		this.score = score;
	}

	/**
	 * @return the vivant
	 */
	public boolean isVivant() {
		return vivant;
	}

	/**
	 * @param vivant
	 *            the vivant to set
	 */
	public void setVivant(final boolean vivant) {
		this.vivant = vivant;
	}
}
