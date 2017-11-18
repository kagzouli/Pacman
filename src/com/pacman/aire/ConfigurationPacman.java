package com.pacman.aire;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.pacman.constant.Constante;
import com.pacman.exception.TechnicalException;
import com.pacman.message.MessageRessources;


/**
 * 
 * Boite de dialogue permettant de configurer pacman
 * @author visiteur
 *
 */
public class ConfigurationPacman extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5125419027338369214L;

	private static final Log LOG = LogFactory.getLog(ConfigurationPacman.class);
	
	/** Define form label **/
	private transient JLabel timeRefreshLabel = new JLabel("Durée rafraichissement");
	private transient JLabel dbLabelTouche = new JLabel("Touche pacman --> E : Direction gauche, X en bas, S à gauche, F à droite");
	
	
	/** Define form field **/
	private transient JTextField timeRefresh = new JLimitedTextField(3,4);
	

	/** Define Button **/
	private transient JButton launchAppliButton = new JButton("Lancer");
	private transient JButton cancelButton = new JButton("Annuler");

	
	/*
	 * (non-Javadoc)
	 * @see suncertify.gui.IHotelRoomView#display()
	 */
	public void display() throws TechnicalException {
		
		LOG.info("DEBUT de la methode de la classe "+ ConfigurationPacman.class.getName());

		/** Initialise window - know which type of window to display **/
		this.defineGraphicalEnvironment();
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Configuration du jeu");
				

		/** Define the center panel of the border layout **/
		JPanel centerPanel = createCenterPanel();
		if (centerPanel != null){
			this.add(centerPanel, BorderLayout.CENTER);		
		}
	
		/** Define the south panel of the border layout **/
		JPanel southPanel = createSouthPanel();
		if (southPanel != null){
			this.add(southPanel, BorderLayout.SOUTH);			
		}

		this.setMinimumSize(new Dimension(500, 250));
		this.setSize(new Dimension(500, 250));

		// Centre la fenetre
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((d.getWidth() - this.getWidth()) / 4);
		int y = (int) ((d.getHeight() - this.getHeight()) / 4);
		this.setLocation(x, y);
		
		this.setVisible(true);
		
		LOG.info("FIN de la methode display de la classe "+ConfigurationPacman.class.getName());
	}
	
	
	protected JPanel createCenterPanel() throws TechnicalException {

		JPanel formPanel = new JPanel();
		formPanel.setLayout(new GridBagLayout());
		formPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

		/** Define gridBagConstraint **/
		GridBagConstraints gbcLabels = new GridBagConstraints();
		GridBagConstraints gbcFields = new GridBagConstraints();

		/** Server name **/
		gbcLabels.gridx = 0;
		gbcLabels.gridy = 0;
		gbcLabels.gridheight = 1;
		gbcLabels.gridwidth = 1;
		gbcLabels.weightx = 1;
		gbcLabels.anchor = GridBagConstraints.WEST;
		gbcLabels.insets = new Insets(0, 10, 0, 0);
		formPanel.add(timeRefreshLabel, gbcLabels);

		gbcFields.gridx = 1;
		gbcFields.gridy = 0;
		gbcFields.gridwidth = 1;
		gbcFields.gridheight = 1;
		gbcFields.weightx = 1;
		gbcFields.anchor = GridBagConstraints.WEST;
		gbcFields.insets = new Insets(5, 10, 0, 10);
		
		gbcLabels.gridy = 1;
		gbcLabels.gridwidth = 2;
		gbcLabels.insets = new Insets(50, 10, 0, 10);
		formPanel.add(dbLabelTouche, gbcLabels);


		this.timeRefresh.setToolTipText("Temps de rafraichissement du jeu (en ms)");
        final String delay = MessageRessources.getInstance(Constante.BUN_NAME_AIJEU).getMessage(Constante.CLE_TEMPS_TIMER);
        this.timeRefresh.setText(delay);

		formPanel.add(this.timeRefresh, gbcFields);
		return formPanel;
	}
	
	public JPanel createSouthPanel() {

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridBagLayout());
		buttonPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

		/** Define gridBagConstraint **/
		GridBagConstraints gbcButtons = new GridBagConstraints();

		/*
		 * Popur les boutons nous allons utiliser notre troisième contrainte que
		 * nous adapterons pour chacun des deux boutons.
		 */
		gbcButtons.gridx = 0;
		gbcButtons.gridy = 0;
		gbcButtons.weightx = 1;
		gbcButtons.insets = new Insets(5, 0, 10, 17);
		this.launchAppliButton.addActionListener(new LaunchAppliActionListener(this));
		this.launchAppliButton.setToolTipText("Lancer l'application");
		this.launchAppliButton.setMnemonic(KeyEvent.VK_L);
		buttonPanel.add(this.launchAppliButton, gbcButtons);

		gbcButtons.gridx = 1;
		gbcButtons.weightx = 0;
		gbcButtons.insets = new Insets(5, 0, 10, 20);
		this.cancelButton.addActionListener(new ExitListener());
		this.cancelButton.setToolTipText("Fermer la fenêtre");
		this.cancelButton.setMnemonic(KeyEvent.VK_A);
		buttonPanel.add(this.cancelButton, gbcButtons);

		/** connect server is the default button **/
		this.getRootPane().setDefaultButton(launchAppliButton);

		return buttonPanel;
	}


	
	/**
	 * This class helps swing to know how he will display this window (Window XP/Linux)
	 */
	protected void defineGraphicalEnvironment() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			SwingUtilities.updateComponentTreeUI(this);
		}

		catch (Exception exception) {
			LOG.error(exception.getMessage(),exception);
			this.displayError("Problème technique : "+exception.getMessage());
		}

	}
	
	protected void displayError(String message){
		JOptionPane.showMessageDialog(this, message,
			    "error",
			    JOptionPane.ERROR_MESSAGE);	
	}
	
    /**
     * Methode pour fermer la fenetre.<br/>
     * 
     */
	public void close(){
		this.dispose();
	}



	/**
	 * Listener to close the window.<br/>
	 * 
	 * @author Karim
	 *
	 */
	class ExitListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			System.exit(0);
			
		}
	}
	
	/**
	 * Classe permettant de lancer le jeu.<br/>
	 * 
	 * @author visiteur
	 *
	 */
	class LaunchAppliActionListener implements ActionListener{
		
		private ConfigurationPacman configurationPacman;
		
		protected LaunchAppliActionListener(ConfigurationPacman configurationPacman){
			this.configurationPacman = configurationPacman;
		}
		
		public void actionPerformed(ActionEvent e) {
			int refreshTime = 0;
			 try {
				 
				// Validation de la donnee saisie.
				final String refeshTimeString = configurationPacman.timeRefresh.getText();
				if ((refeshTimeString == null) || (refeshTimeString.trim().equals(""))){
					configurationPacman.displayError("La durée de raffraichissement doit être renseignée.");
					configurationPacman.timeRefresh.requestFocus();
					return;
				}
				
				try{ 
					refreshTime = Integer.parseInt(refeshTimeString);
					if (refreshTime <= 0){
						configurationPacman.displayError("La durée de raffraichissement doit être un entier strictement positif.");
						configurationPacman.timeRefresh.requestFocus();
						return;
					}
					
				}catch(NumberFormatException exception){
					configurationPacman.displayError("La durée de raffraichissement doit être un entier strictement positif.");
					configurationPacman.timeRefresh.requestFocus();
					return;
				}
				
				
				 
				configurationPacman.close();
				FenetreAireJeu.getInstance().start(refreshTime);
				
			} catch (Exception exception) {
				LOG.error(exception.getMessage(),exception);
	            System.exit(1);
			}
			
		}
	}


}
