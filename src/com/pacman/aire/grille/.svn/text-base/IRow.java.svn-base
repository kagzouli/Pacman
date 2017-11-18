package com.pacman.aire.grille;

import java.awt.Graphics;
import java.util.Iterator;

public interface IRow 
{
    /**
     * Methode permettant de creer une cellule dans la ligne.<br/>
     * @param cell La cellule a creer.<br/>
     */
    void addCell(ICell cell);
    
    /**
     * Retourne le nombre de cellules du tableau.<br/>
     * @return Retourne le nombre de cellules du tableau.<br/>
     */
    int getCellNumber();
    
    /**
     * Methode permettant de recuperer la cellule numero index
     * @param index Index.<br/>
     * @return Retourne la cellule numero index.<br/>
     */
    ICell getCell(int index);
    
    /**
     * Methode permettant de fixer une cellule a l'index index.<br/>
     * @param index Index.<br/>
     * @param cell La cellule.<br/>
     */
    void setCell(int index,ICell cell);
    
    /**
     * Methode permettant de recuperer l'iterateur.<br/>
     * @return Iterateur.<br/>
     */
    Iterator<ICell> iterator();
    
    /**
     * Methode permettant de tracer une ligne.<br/>
     * @param gc Graphics.<br/>
     */
    void draw(Graphics graphic);
 
}
