package com.pacman.aire.grille;

import java.awt.Color;
import java.awt.Graphics;

import com.pacman.aire.AireJeu;
import com.pacman.element.AbstractElement;
import com.pacman.element.ElementVide;

/**
 * 
 * (posX,posY) ------------ | | | | largeur | | |----------| longueur
 * 
 * @author x587368
 * 
 */
public class CellImpl implements ICell {
	private int posX;

	private int posY;

	private int longueur;

	private int largeur;

	private AbstractElement element;

	/**
	 * @return the element
	 */
	public AbstractElement getElement() {
		return element;
	}

	/**
	 * @param element
	 *            the element to set
	 */
	public void setElement(final AbstractElement element) {
		this.element = element;
	}

	public int getLargeur() {
		return largeur;
	}

	public void setLargeur(final int theLargeur) {
		largeur = theLargeur;
	}

	public int getLongueur() {
		return longueur;
	}

	public void setLongueur(final int theLongueur) {
		longueur = theLongueur;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(final int thePosX) {
		posX = thePosX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(final int thePosY) {
		posY = thePosY;
	}

	/**
	 * 
	 * {@inheritDoc}
	 */
	public void draw(final Graphics graphic,final int thePosIniX,final int thePosIniY,final int theLongueur,final int theLargeur) {
		if (graphic != null) {

			// Fixe la couleur
			final AbstractElement element = this.element;
			if (element instanceof ElementVide) {
				graphic.setColor(Color.BLACK);
				graphic.fillRect(thePosIniX, thePosIniY, theLongueur, theLargeur);
			} else {
				final int datax = (int) (((float) element.getImageElement().getWidth(null)/(float)(theLongueur)) * (float)(theLongueur - 5));
				final int datay = (int) (((float) element.getImageElement().getHeight(null)/(float)(theLargeur)) * (float)(theLargeur - 5));
				
				graphic.drawImage(this.element.getImageElement(), thePosIniX, thePosIniY,datax,datay, AireJeu.getInstance());
			}

		}

	}

}
