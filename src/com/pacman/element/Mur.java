package com.pacman.element;

import java.awt.Image;
import java.awt.Toolkit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Mur extends AbstractElement {
	
	private static final Log LOG = LogFactory.getLog(Mur.class);

	private static Image imageMur;
	
	public Mur(final int indexNumLigne,final int indexNumColonne){
		super();
		this.indexNumLigne = indexNumLigne;
		this.indexNumColonne = indexNumColonne;
	}

	@Override
	public Image getImageElement() {
		if (imageMur == null) {
			imageMur = Toolkit.getDefaultToolkit().getImage(Mur.class.getResource("/image/mur.gif"));
			LOG.info("Image du mur : "+imageMur);
		}

		return imageMur;
	}

	@Override
	public boolean isAccessible() {
		return false;
	}

	@Override
	public boolean tuePacman() {
		return false;
	}

}
