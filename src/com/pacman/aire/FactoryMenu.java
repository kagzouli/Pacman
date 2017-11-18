package com.pacman.aire;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public final class FactoryMenu
{
    
    private static final String FILE_ITEM = "Fichier";
    
    private static final String FILE_NEW = "Nouveau";
  
    private static final String FILE_EXIT = "Quitter";
    
    private static final char MNE_FILE_ITEM = 'F';
    
    private FactoryMenu(){
    	
    }

    
    /**
     * Methode permettant de construire le menu.<br/>
     * 
     */
    public static JMenuBar createMenu(){
        
        final JMenuBar menu = new JMenuBar();
        
        final JMenu fichier = new JMenu(FILE_ITEM);
        final JMenuItem nouveau = new JMenuItem(FILE_NEW);
        nouveau.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        fichier.add(nouveau);
        final JMenuItem quitter = new JMenuItem(FILE_EXIT);
        fichier.add(quitter);
        fichier.setMnemonic(MNE_FILE_ITEM);
        menu.add(fichier);
              
        return menu;
       
    }

}
