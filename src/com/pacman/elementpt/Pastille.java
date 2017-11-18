package com.pacman.elementpt;

import java.awt.Image;
import java.awt.Toolkit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Pastille extends AbstractElementAManger{

	private static final Log LOG = LogFactory.getLog(Pastille.class);
	
	private transient static Image imagePastille;
	
	public Pastille(final int indexNumLigne,final int indexNumColonne){
		super();
		this.indexNumLigne = indexNumLigne;
		this.indexNumColonne = indexNumColonne;
	}

	@Override
	public Image getImageElement() {
		if (imagePastille == null){
			
			imagePastille = Toolkit.getDefaultToolkit().getImage(Pastille.class.getResource("/image/pastille.gif"));
			LOG.info("imagePastille : " + imagePastille);
		}
		
		return imagePastille;
	}

	@Override
	public int rapportePoint() {
		return 1;
	}

}
