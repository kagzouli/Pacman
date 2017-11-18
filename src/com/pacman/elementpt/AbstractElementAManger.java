package com.pacman.elementpt;



import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.pacman.element.AbstractElement;

public abstract class AbstractElementAManger extends AbstractElement {
	
	private static final Log LOG = LogFactory.getLog(AbstractElementAManger.class);
	
	@Override
	public boolean isAccessible() {
		if (LOG.isDebugEnabled()){
			LOG.debug(new StringBuilder().append("Appel de la methode isAccessible de la classe ").append(AbstractElementAManger.class).toString());
		}
		return true;
	}

	@Override
	public boolean tuePacman() {
		if (LOG.isDebugEnabled()){
			LOG.debug(new StringBuilder().append("Appel de la methode tuePacman de la classe ").append(AbstractElementAManger.class).toString());	
		}
		return false;
	}
	
	/**
	 * Methode permettant de savoir combien rapporte un element mangeable.<br/>
	 * @return
	 */
	public abstract int rapportePoint();

}
