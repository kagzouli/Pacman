package com.pacman.action.timer;

import javax.swing.Timer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;



/**
 * Timer utilise pour Tetris.<br/>
 * Le timer est unique.<br/>
 * 
 * @author x587368
 * 
 */
public final class PacmanTimer
{ 
    
    private static final Log LOG = LogFactory.getLog(PacmanTimer.class);
    
    /**
     * Delai du timer par default.
     */
      
    /**
     * Timer.
     */
    private final transient Timer timer;
    
    /**
     * Constructeur permettant de construire un timer pour tetris.<br/>
     * @param delay Delai entre chaque raffraichissement.<br/>
     * @param actionListener Listener.<br/>
     */
    public PacmanTimer(final int delay,final PacmanListener actionListener){
        timer = new Timer(delay,actionListener);
    }
    
    /**
     * Lance le timer de tetris.<br/>
     *
     */
    public void start(){
        if (timer != null){
            LOG.info("Debut du timer");
            timer.start();
        }
    }
    
    /**
     * Methode donnant le tempo pour raffraichir le timer.
     * @param delay
     */
    public void setRaffraichissement(final int delay){
        timer.setDelay(delay);
    }
    
    /**
     * Methode permettant d'arreter le timer.<br/>
     *
     */
    public void stop(){
        timer.stop();
    }
}
