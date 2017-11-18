package com.pacman.etre;

import java.awt.Image;

import com.pacman.element.AbstractElement;

/**
 * Gere uniquement les elements mobiles.<br/>
 * @author GZOULI
 *
 */
public abstract class AbstractElementMobile extends AbstractElement{

	public abstract Image getImageElement();

	public abstract boolean isAccessible();

	public abstract boolean tuePacman();

}
