package com.pacman.aire;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.pacman.action.timer.PacmanListener;
import com.pacman.action.timer.PacmanTimer;
import com.pacman.actions.EcouteurMusique;
import com.pacman.aire.grille.ICell;
import com.pacman.aire.grille.ITable;
import com.pacman.constant.Constante;
import com.pacman.etre.Pacman;
import com.pacman.exception.TechnicalException;
import com.pacman.message.MessageRessources;



/**
 * L'aire de jeu represente le panel.<br/>
 * 
 */
public class AireJeu extends JPanel
{
    private static final Log LOG = LogFactory.getLog(AireJeu.class);

    private static final long serialVersionUID = 1234343665656565L;
    
       
    private static AireJeu aireJeuPac = new AireJeu();
    /**
     * Timer.<br/>
     */
    private Integer timer;
    
    /**
     * Grille joueur.<br/>
     */
    private ITable grilleJoueur;

    /**
     * Methode pour savoir si l'aire de jeu est initialisee. Cela permet de savoir quand commencer a raffraichir avec
     * paint.<br/>
     */
    private transient boolean initGame = false;
    
    private transient long tempsInitial = 0l;

    protected AireJeu()
    {
    	super();
    	tempsInitial = System.currentTimeMillis();
    }

    /**
     * Singleton
     * 
     * @return
     */
    public static AireJeu getInstance()
    {
        return aireJeuPac;
    }

    /**
     * Methode permettant d'initialiser l'aire de jeu.<br/>
     * 
     * @param refreshTime Temps de raffraichissement
     * @throws TechnicalException Erreur technique.<br/>
     */
    public void init(final int refreshTime) throws TechnicalException
    {
        LOG.info("DEBUT de la methode init de la classe "+this.getClass().getName());
        
        try
        {
            // Couleur de fond noir.
            this.setBackground(Color.BLACK);

            // Creation de la grille du joueur 1.
            grilleJoueur = FactoryTable.creerTable(150, 50, 22, 25);

           
            // lancement du timer de tetris
            final PacmanListener tetrisListener = new PacmanListener();
            final PacmanTimer tetrisTimer = new PacmanTimer(refreshTime,tetrisListener);
            tetrisTimer.start();
            tetrisListener.setTimer(tetrisTimer);           
               
            //Le panel est initialise.
            initGame = true;
        } catch (Exception e)
        {
            LOG.error(e.getMessage(),e);
            throw new TechnicalException(e);
        }
        
        LOG.info("FIN de la methode init de la classe "+this.getClass().getName());
    }

    public void paint(final Graphics graphic)
    {
        // Couleur de fond noir.
        this.setBackground(Color.BLACK);
        if (initGame)
        {
            
            super.paint(graphic);

            this.traceGrille(graphic, this.grilleJoueur);
            
        }
        
        final ICell cellPacman = this.grilleJoueur.findPacman();
        if (cellPacman != null){
            final Pacman pacman = (Pacman) cellPacman.getElement();
            final long temps = this.getTempsEcouleEnSeconde();
            
            
            graphic.setColor(Color.GREEN);
            final Font font = new Font(null, Font.BOLD, 14);
            graphic.setFont(font);
            final StringBuffer texte = new StringBuffer();
            texte.append("Score : ");
            texte.append(pacman.getScore());
            texte.append(" , Temps écoulé : ");
            texte.append(temps);
            texte.append(" s");
           
            graphic.drawString(texte.toString(), 400, 15);     	
        }
           

    }
    
    /**
     * Methode permettant de tracer la grille.<br/>
     * 
     * @param gc Graphics.<br/>
     * @param grille Grille.<br/>
     * @param positionInitialeX positionInitialeX
     * @param int positionInitialeY positionInitialeY.
     */
    private void traceGrille(final Graphics graphic,final ITable grille)
    {
        if (grille != null)
        {
            grille.draw(graphic);
        }
    }
    
    /**
     * Methode permettant de reinitialiser la partie.<br/>
     * @throws TechnicalException 
     */
    public void reinitialisePartie() throws TechnicalException{
        // Creation de la grille du joueur 1.
        grilleJoueur = FactoryTable.creerTable(150, 50, 22, 25);
        tempsInitial = System.currentTimeMillis();

    }
    
    /**
     * Methode permettant de recuperer le temps ecoule en seconde.<br/>
     * 
     * @return Retourne le temps ecoule en seconde.<br/>
     */
    public long  getTempsEcouleEnSeconde(){
    	final long temps = (System.currentTimeMillis() -  this.tempsInitial) / 1000;
    	return temps;
    }


	/**
	 * @return the timer
	 */
	public Integer getTimer() {
		return timer;
	}

	/**
	 * @param timer the timer to set
	 */
	public void setTimer(final Integer timer) {
		this.timer = timer;
	}

	/**
	 * @return the grilleJoueur
	 */
	public ITable getGrilleJoueur() {
		return grilleJoueur;
	}

	/**
	 * @param grilleJoueur the grilleJoueur to set
	 */
	public void setGrilleJoueur(final ITable grilleJoueur) {
		this.grilleJoueur = grilleJoueur;
	}



}
