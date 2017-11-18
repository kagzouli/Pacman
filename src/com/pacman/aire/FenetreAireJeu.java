package com.pacman.aire;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.pacman.actions.PacmanAction;
import com.pacman.constant.Constante;
import com.pacman.exception.TechnicalException;
import com.pacman.message.MessageRessources;

public class FenetreAireJeu implements ActionListener
{
    
  
    private static final Log LOG = LogFactory.getLog(FenetreAireJeu.class);

    /**
     * Frame.<br/>
     */
    protected transient JFrame frame;
    
    /**
     * Aire de jeu.<br/>
     */    
    protected transient AireJeu aireJeu;

    /**
     * Singleton representant l'aire de jeu.<br/>
     */
    protected static FenetreAireJeu fenetreAirJeu = new FenetreAireJeu();

   
    /**
     * Constructeur prive.
     *
     */
    protected FenetreAireJeu(){
        LOG.debug("Constructor");
    }

    /**
     * Methode permettant de retourner une aire de jeu.<br/>
     * 
     * @return retourne une aire de jeu.<br/>
     */
    public static FenetreAireJeu getInstance()
    {
        return fenetreAirJeu;
    }

    /**
     * Cette methode permet d'initialiser la fenetre.<br/>
     * 
     * @param refreshTime Temps raffraichissement (ms)
     * @throws TechnicalException Erreur technique.<br/>
     */
    public void start(final int refreshTime) throws TechnicalException
    {
        LOG.info("DEBUT de la methode init de la classe "+this.getClass().getName());
 
        try
        {
            
            frame = new JFrame();
   
            final Integer longueur = MessageRessources.getInstance(Constante.BUN_NAME_AIJEU).getMessageInteger(Constante.CLE_LONGUEUR);
            final Integer largeur = MessageRessources.getInstance(Constante.BUN_NAME_AIJEU).getMessageInteger(Constante.CLE_LARGEUR);
         

            frame.setSize(longueur.intValue(), largeur.intValue());
            
            //Centre la fenetre.
            this.centrerFenetre();
            
            //Definit l'environnement graphique.
            this.definirEnvironnementGraphique();
            
            //Menu le menu
            frame.setJMenuBar(FactoryMenu.createMenu());
            frame.setBackground(Color.BLACK);
            
            
            // Recupere le panel de la frame.
            final JPanel container = (JPanel) frame.getContentPane();
            container.setLayout(new BorderLayout());
            
            //Initialise l'aire de jeu.
            AireJeu.getInstance().init(refreshTime);
            frame.setContentPane(AireJeu.getInstance());
            
            //Initialisation du listener du clavier
            frame.addKeyListener(new PacmanAction());
            
            //Active la suppression de la fenetre
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            
            //Rend la fenetre visible.
            frame.setVisible(true);

        } catch (Exception e)
        {
            LOG.error(e,e);
            throw new TechnicalException(e);
        }
        LOG.info("FIN de la methode init de la classe "+this.getClass().getName());
        

    }

    /**
     * Methode permettant de centrer la fenetre.<br/>
     * 
     */
    protected void centrerFenetre()
    {
        final Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation((screenSize.width - frame.getWidth()) / 2, (screenSize.height - frame.getHeight()) / 2);
    }

    /**
     * Methode permettant de definir l'environnement graphique.<br/>
     * @throws TechnicalException.  Erreur technique.<br/>
     */
    public void definirEnvironnementGraphique() throws TechnicalException
    {
        try
        {
            final String sys = System.getProperty("os.name");
            LOG.debug(new StringBuilder("Programme tourne sous ").append(sys).toString());

            // Pour linux, on utilise le systeme par default
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        }

        catch (Exception e)
        {
            LOG.error(e,e);
            throw new TechnicalException(e);
        }
    }

    public void actionPerformed(final ActionEvent theEvent)
    {
        LOG.debug(new StringBuilder().append("Methode actionPerformed").append(" de la classe ").append(FenetreAireJeu.class).toString());
    }

}
