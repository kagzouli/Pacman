package com.pacman.etre;

import java.awt.Image;
import java.awt.Toolkit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.pacman.strategydeplacement.StrategyAleatoire;

public class Fantome2 extends AbstractFantome {

	private static final Log LOG = LogFactory.getLog(Fantome2.class);

	private static Image imageFantome2;

	public Fantome2(final int indexNumLigne,final int indexNumColonne) {
		super();
		this.indexNumLigne = indexNumLigne;
		this.indexNumColonne = indexNumColonne;
		
		//Fantome2 = Deplacement alatoire
		//this.strategy = new StrategyAleatoire();
		this.strategy = new StrategyAleatoire();
	}

	@Override
	public Image getImageElement() {
		if (imageFantome2 == null) {
			imageFantome2 = Toolkit.getDefaultToolkit().getImage(Fantome2.class.getResource("/image/fantome2.gif"));
			LOG.info("imageFantome2 : "+imageFantome2);
		}

		return imageFantome2;
	}

	@Override
	public boolean isAccessible() {
		return true;
	}

	@Override
	public boolean tuePacman() {
		return true;
	}

}
