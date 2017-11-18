package com.pacman.elementpt;

import java.awt.Image;
import java.awt.Toolkit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Banane extends AbstractElementAManger{
	
	private static final Log LOG = LogFactory.getLog(Banane.class);
	
	private transient static Image imageBanane;

	@Override
	public Image getImageElement() {
		if (imageBanane == null){
			imageBanane = Toolkit.getDefaultToolkit().getImage(Banane.class.getResource("/image/banane.gif").toString());			
			LOG.info("imageBanane :" + imageBanane);
		}
		
		return imageBanane;
	}

	@Override
	public int rapportePoint() {
		return 3;
	}

}
