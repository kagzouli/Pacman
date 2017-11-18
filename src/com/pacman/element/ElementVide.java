package com.pacman.element;

import java.awt.Image;

public class ElementVide extends AbstractElement{
	
	public ElementVide(final int indexNumLigne,final int indexNumColonne){
		super();
		this.indexNumLigne = indexNumLigne;
		this.indexNumColonne = indexNumColonne;
	}

	public Image getImageElement() {
		return null;
	}

	@Override
	public boolean isAccessible() {
		return true;
	}

	@Override
	public boolean tuePacman() {
		return false;
	}

}
