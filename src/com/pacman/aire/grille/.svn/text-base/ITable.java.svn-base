
package com.pacman.aire.grille;

import java.awt.Graphics;
import java.util.Iterator;

import com.pacman.strategydeplacement.algoaetoile.TileBasedMap;


public interface ITable extends TileBasedMap
{
    
    /**
     * Methode permettant de creer une ligne dans le tableau.<br/>
     * @param row La ligne a creer.<br/>
     */
    void addRow(IRow row);
    
    /**
     * Retourne le nombre de lignes du tableau.<br/>
     * @return Retourne le nombre de lignes du tableau.<br/>
     */
    int getRowNumber();
    
    /**
     * Methode permettant de recuperer la ligne numero index
     * @param index Index.<br/>
     * @return Retourne la ligne numero index.<br/>
     */
    IRow getRow(int index);
    
    /**
     * Methode permettant de recuperer l'iterateur.<br/>
     * @return Iterateur.<br/>
     */
    Iterator<IRow> iterator();
    
    /**
     * Methode permettant de tracer le tableau.<br/>
     * @param gc Graphics.<br/>
     */
    void draw(Graphics graphic);
    
    /**
     * Methode permettant de recuperer la cellule a l'index indexLigne et a la colonne indexColonne.<br/>
     * @param indexLigne Index ligne.<br/>
     * @param indexColonne Index colonne.<br/>
     * @return Retourne la cellule correspondant.<br/>
     */
    ICell getCell(int indexLigne,int indexColonne);
    
    /**
     * Methode permettant de fixer la cellule a l'index indexLigne et a la colonne indexColonne.<br/>
     * @param indexLigne Index ligne.<br/>
     * @param indexColonne Index colonne.<br/>
     @ param cell La cellule.<br/>
     */
    void setCell(int indexLigne,int indexColonne,ICell cell);
    
    /**
     * Methode permettant de retrouver pacman dans la grille.<br/>
     * @return
     */
    ICell findPacman();
    
    /**
     * Methode permettant de retrouer le fantome1 dans la grille.<br/>
     * @return
     */
    ICell findFantome1();
    
    /**
     * Methode permettant de retrouer le fantome2 dans la grille.<br/>
     * @return
     */
    ICell findFantome2();
    
    /**
     * Methode permettant de retrouer le fantome3 dans la grille.<br/>
     * @return
     */
    ICell findFantome3();
    
    /**
     * Methode permettant de vider la grille.<br/>
     */
    void videGrille();
    
    /**
     * Methode permettant de verifier qu'il existe encore de la nourriture a manger.<br/>
     * 
     * @return
     */
    boolean verifieExistElementAManger();

}
