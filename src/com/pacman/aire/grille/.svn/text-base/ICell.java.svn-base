package com.pacman.aire.grille;


import java.awt.Graphics;

import com.pacman.element.AbstractElement;


public interface ICell
{
    
       
    
    /**
     * Methode permettant de recuperer la largeur de la cellule.<br/>
     * @return Retourne la largeur de la cellule.<br/>
     */
    int getLargeur();
  

    /**
     * Methode permettant de fixer la largeur de la cellule.<br/>
     * @param theLargeur La largeur.<br/>
     */
    void setLargeur(int theLargeur);
    

    /**
     * Methode permettant de recuperer la longueur de la cellule.<br/>
     * @return La longueur.<br/>
     */
    int getLongueur();
    

    /**
     * Methode permettant de fixer la longueur de la cellule.<br/>
     * @param theLongueur La longueur.<br/>
     */
    void setLongueur(int theLongueur);
   

    /**
     * Methode permettant de recuperer la positionX(En haut a gauche de la cellule).<br/>
     * @return Retourne la positionX(En haut a gauche de la cellule).<br/>
     */
    int getPosX();
  

    /**
     * Methode permettant de fixer la positionX(En haut a gauche de la cellule).<br/>
     * @param thePosX La positionX.<br/>
     */
    void setPosX(int thePosX);
    
    /**
     * Methode permettant de recuperer la positionY(En haut a gauche de la cellule).<br/>
     * @return Retourne la positionY(En haut a gauche de la cellule).<br/>
     */
    int getPosY();

    /**
     * Methode permettant de fixer la positionY(En haut a gauche de la cellule).<br/>
     * @param thePosY La positionY.<br/>
     */
    void setPosY(int thePosY);
    
    /**
     * Methode permettant de savoir que la cellule contient un element.<br/>
     * @return
     */
    AbstractElement getElement();
   
    /**
     * Methode permettant de fixer un element.<br/>
     * @param element
     */
    void setElement(AbstractElement element);
    
    /**
     * Methode permettant de dessiner la cellule.<br/>
     * @param graphic Graphics.<br/>
     * @param posIniX position initiale X.<br/>
     * @param posIniY position initiale Y.<br/>
     * @param longueur longueur Longueur.<br/>
     * @param largeur Largeur.<br/>
     */
    void draw(Graphics graphic,int posIniX,int posIniY,int longueur,int largeur);
     
}
