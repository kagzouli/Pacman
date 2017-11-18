package com.pacman.actions;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.pacman.aire.AireJeu;
import com.pacman.aire.grille.ICell;
import com.pacman.aire.grille.ITable;
import com.pacman.etre.Pacman;

public class PacmanAction implements KeyListener
{
	private static final Log LOG = LogFactory.getLog(PacmanAction.class);
  
    public void keyPressed(final KeyEvent theE)
    {
        actionJoueur(theE);
    	
          //Reaffiche l'ecran.
        AireJeu.getInstance().repaint();
 
    }

    public void keyReleased(final KeyEvent theE)
    {
    	LOG.debug("keyReleased");   
    }

    public void keyTyped(final KeyEvent theE)
    {
    	LOG.debug("keyTyped"); 
    }
    
    
    /**
     * Methode permettant d'enregistrer les actions de pacman.<br/>
     * @param theE KeyEvent.<br/>
      *
     */
    protected void actionJoueur(final KeyEvent theE){
        
        
        final int codeTouche = theE.getKeyCode();
        
        if (codeTouche == KeyEvent.VK_S){
            deplacementGauche();
        }
        
        if (codeTouche == KeyEvent.VK_F){
            deplacementDroite();
        }
        
        if (codeTouche == KeyEvent.VK_X){
            deplacementBas();
        }
        
        if (codeTouche == KeyEvent.VK_E){
            deplacementHaut();
        }
    }
    
    /**
     * Methode permettant de deplacer pacman a gauche.<br/>
     */
    protected void deplacementGauche(){
     	final ITable grilleJoueur = AireJeu.getInstance().getGrilleJoueur();
    	final ICell cellPacman = grilleJoueur.findPacman();
    	if (cellPacman != null){
    		final Pacman pacman = (Pacman) cellPacman.getElement();
     		pacman.deplacerGauche(grilleJoueur);    	   		
    	}
      }
    
    /**
     * Methode permettant de deplacer pacman a droite.<br/>
     */
    protected void deplacementDroite(){
    	final ITable grilleJoueur = AireJeu.getInstance().getGrilleJoueur();
    	final ICell cellPacman = grilleJoueur.findPacman();
    	if (cellPacman != null){
    		final Pacman pacman = (Pacman) cellPacman.getElement();
     		pacman.deplacerDroite(grilleJoueur);   		
    	}
     }
    
    /**
     * Methode permettant de deplacer pacman en bas.<br/>
     */
    protected void deplacementBas(){
    	final ITable grilleJoueur = AireJeu.getInstance().getGrilleJoueur();
    	final ICell cellPacman = grilleJoueur.findPacman();
    	if (cellPacman != null){
    		final Pacman pacman = (Pacman) cellPacman.getElement();
     		pacman.deplacerBas(grilleJoueur);   		
    	}
    }
    
    /**
     * Methode permettant de deplacer pacman en haut.<br/>
     */
    protected void deplacementHaut(){
    	final ITable grilleJoueur = AireJeu.getInstance().getGrilleJoueur();
    	final ICell cellPacman = grilleJoueur.findPacman();
    	if (cellPacman != null){
    		final Pacman pacman = (Pacman) cellPacman.getElement();
     		pacman.deplacerHaut(grilleJoueur); 	   		
    	}
     }
}
