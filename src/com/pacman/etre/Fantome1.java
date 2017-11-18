package com.pacman.etre;

import java.awt.Image;
import java.awt.Toolkit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.pacman.strategydeplacement.StrategieAlgoAStar;

public class Fantome1 extends AbstractFantome{

	private static final Log LOG = LogFactory.getLog(Fantome1.class);

	private static Image imageFantome1;
	
	public Fantome1(final int indexNumLigne,final int indexNumColonne){
		super();
		this.indexNumLigne = indexNumLigne;
		this.indexNumColonne = indexNumColonne;
		
		this.strategy = new StrategieAlgoAStar();
	}

	@Override
	public Image getImageElement() {
		if (imageFantome1 == null) {
			imageFantome1 = Toolkit.getDefaultToolkit().getImage(Fantome1.class.getResource("/image/fantome1.gif"));
			LOG.info("ImageFantome1 : "+imageFantome1);
		}

		return imageFantome1;
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
