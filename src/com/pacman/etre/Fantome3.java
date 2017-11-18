package com.pacman.etre;

import java.awt.Image;
import java.awt.Toolkit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.pacman.strategydeplacement.StrategieAlgoAStar;
import com.pacman.strategydeplacement.StrategySimple;

public class Fantome3 extends AbstractFantome{

	private static final Log LOG = LogFactory.getLog(Fantome3.class);

	private static Image imageFantome3;
	
	public Fantome3(final int indexNumLigne,final int indexNumColonne) {
		super();
		this.indexNumLigne = indexNumLigne;
		this.indexNumColonne = indexNumColonne;
		
		//Fantome3 = Deplacement simple
		//this.strategy = new StrategyAleatoire();
		//this.strategy = new StrategySimple();
		this.strategy = new StrategieAlgoAStar();
	}

	@Override
	public Image getImageElement() {
		if (imageFantome3 == null) {
			imageFantome3 = Toolkit.getDefaultToolkit().getImage(Fantome3.class.getResource("/image/fantome3.gif"));
			LOG.info("imageFantome3  : "+imageFantome3);

		}

		return imageFantome3;
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
