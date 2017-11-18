package com.pacman.action.timer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.pacman.aire.AireJeu;
import com.pacman.aire.grille.ICell;
import com.pacman.etre.Fantome1;
import com.pacman.etre.Fantome2;
import com.pacman.etre.Fantome3;
import com.pacman.etre.Pacman;
import com.pacman.exception.PacmanRuntimeException;
import com.pacman.exception.TechnicalException;

/**
 * Action repetee un certain nombre de fois fait par tetris.
 * 
 */
public class PacmanListener implements ActionListener {
	private static final Log LOG = LogFactory.getLog(PacmanListener.class);

	/**
	 * Timer tetris.
	 */
	private PacmanTimer timer;

	public void actionPerformed(final ActionEvent theE) {
		LOG.info(new StringBuffer("methode actionPerformed de la classe ").append(this.getClass().getName()).toString());

		try {

			// Calcule position fantome1
			this.calculePositionFantome1();

			// Calcule position fantome2
			this.calculePositionFantome2();

			// Calcule position fantome3
			this.calculePositionFantome3();
			
			Pacman pacman  = null;
			final ICell cellPacman = AireJeu.getInstance().getGrilleJoueur().findPacman();
			if (cellPacman == null) {
				this.gestionMortPacman();
			} else {
				pacman = (Pacman) cellPacman.getElement();
				if (!pacman.isVivant()) {
					this.gestionMortPacman();
				}
			}
			
			// Verifie qu'il n'y a plus rien a manger.
			if (!AireJeu.getInstance().getGrilleJoueur().verifieExistElementAManger()){
				// Message Super victoire de pacman.
				JOptionPane.showMessageDialog(AireJeu.getInstance(), "Bravo, vous avez obtenu le score de " +pacman.getScore() + " en "+ AireJeu.getInstance().getTempsEcouleEnSeconde()+" s", "Resultat", JOptionPane.INFORMATION_MESSAGE);
			
				//Vide la grille
				AireJeu.getInstance().getGrilleJoueur().videGrille();
				
				// Reinitialise la partie
				AireJeu.getInstance().reinitialisePartie();

			}
			
		} catch (Exception e) {
			throw new PacmanRuntimeException(e);
		}

		// Reaffiche le jeu.
		AireJeu.getInstance().repaint();
	}

	/**
	 * @return the timer
	 */
	public PacmanTimer getTimer() {
		return timer;
	}

	public void setTimer(final PacmanTimer theTimer) {
		timer = theTimer;
	}

	/**
	 * Methode permettant de calculer la position du fantome1.<br/>
	 */
	private void calculePositionFantome1() {
		final ICell cellFantome1 = AireJeu.getInstance().getGrilleJoueur().findFantome1();
		if (cellFantome1 != null) {
			final Fantome1 fantome1 = (Fantome1) cellFantome1.getElement();
			fantome1.deplacer(AireJeu.getInstance().getGrilleJoueur());
		}

	}

	/**
	 * Methode permettant de calculer la position du fantome2.<br/>
	 */
	private void calculePositionFantome2() {
		final ICell cellFantome2 = AireJeu.getInstance().getGrilleJoueur().findFantome2();

		if (cellFantome2 != null) {
			final Fantome2 fantome2 = (Fantome2) cellFantome2.getElement();
			fantome2.deplacer(AireJeu.getInstance().getGrilleJoueur());
		}

	}

	/**
	 * Methode permettant de calculer la position du fantome3.<br/>
	 */
	private void calculePositionFantome3() {
		final ICell cellFantome3 = AireJeu.getInstance().getGrilleJoueur().findFantome3();
		if (cellFantome3 != null) {
			final Fantome3 fantome3 = (Fantome3) cellFantome3.getElement();
			fantome3.deplacer(AireJeu.getInstance().getGrilleJoueur());
		}
	}

	/**
	 * Methode permettant de gerer la mort de pacman.<br/>
	 * 
	 * @throws TechnicalException
	 */
	private void gestionMortPacman() throws TechnicalException {

		// Message pacman est mort
		JOptionPane.showMessageDialog(AireJeu.getInstance(), "Pacman est mort", "Resultat", JOptionPane.ERROR_MESSAGE);

		
		//Vide la grille
		AireJeu.getInstance().getGrilleJoueur().videGrille();
		
		// Reinitialise la partie
		AireJeu.getInstance().reinitialisePartie();
	}

}
