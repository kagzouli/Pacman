package com.pacman.aire;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.pacman.aire.grille.CellImpl;
import com.pacman.aire.grille.ICell;
import com.pacman.aire.grille.IRow;
import com.pacman.aire.grille.ITable;
import com.pacman.aire.grille.RowImpl;
import com.pacman.aire.grille.TableImpl;
import com.pacman.element.AbstractElement;
import com.pacman.element.ElementVide;
import com.pacman.element.Mur;
import com.pacman.elementpt.Pastille;
import com.pacman.etre.Fantome1;
import com.pacman.etre.Fantome2;
import com.pacman.etre.Fantome3;
import com.pacman.etre.Pacman;
import com.pacman.exception.TechnicalException;

public final class FactoryTable {

	private static final Log LOG = LogFactory.getLog(FactoryTable.class);

	private static final int TAILLE = 28;
	
	private FactoryTable(){
		
	}

	/**
	 * 
	 * {@inheritDoc}
	 * @throws Exception 
	 */
	public static ITable creerTable(final int posIniX,final int posInitY,final int nbrLignes,final int nbrCellules) throws TechnicalException {

		// Lecture du fichier CSV
		ITable table = null;
		BufferedReader ficGrillePacman = null;
		try {
		//	final String repCourantGrille = System.getProperty("user.dir");
		//	LOG.info("Repertoire courant grille : " + repCourantGrille);
		//  String ficGrillePacman = new StringBuffer().append(repCourantGrille).append("/grillepacman.csv").toString();
			ficGrillePacman = new BufferedReader(new InputStreamReader(FactoryTable.class.getResourceAsStream("/grillepacman.csv")));
			
			List<String> listLigne = new ArrayList<String>();
			String chaine = "";
			while ((chaine = ficGrillePacman.readLine()) != null){
				listLigne.add(chaine);
			}
			
			if ((listLigne == null) && (listLigne.isEmpty())) {
				throw new TechnicalException("Fichier vide - grillepacman.csv");
			}

			table = new TableImpl(nbrLignes,nbrCellules);

			final int posX = posIniX;
			int posY = posInitY;
			final Iterator<String> itLigne = listLigne.iterator();
			int indexLigne = 0;
			for (int indxCol = 0; indxCol < nbrLignes; indxCol++) {
				final String ligne = itLigne.next();
				final IRow row = createRow(posX, posY, nbrCellules, ligne,indexLigne);
				table.addRow(row);
				posY = posY + TAILLE;
				indexLigne++;
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new TechnicalException(e);
		}finally{
			if (ficGrillePacman != null){
				IOUtils.closeQuietly(ficGrillePacman);
			}
		}

		return table;
	}

	/**
	 * Factory permettant de creer les lignes.<br/>
	 * 
	 * @param posInitX
	 *            Position initialeX
	 * @param posInitY
	 *            position initialeY.
	 * @param nbrLignes
	 *            Nbr cellules pour la ligne.<br/>
	 * @param ligne
	 *            ligne du fichier a creer.<br/>
	 * @param indexLigne Index de la ligne a creer.<br/>
	 * @return Retourne la ligne ainsi cree.<br/>
	 */
	private static IRow createRow(final int posInitX,final int posInitY,final int nbrCellules,final String ligne,final int indexLigne) {
	    final IRow row = new RowImpl();

		int posX = posInitX;
		final int posY = posInitY;
		final String[] elements = ligne.split(";");
		int indexCellule = 0;
		for (int indxCell = 0; indxCell < nbrCellules; indxCell++) {
			final ICell cell = new CellImpl();

			// La cellule est vide.
			// cell.fixeLibre();

			// Position de la cellule.
			cell.setPosX(posX);
			cell.setPosY(posY);
			cell.setLongueur(TAILLE);
			cell.setLargeur(TAILLE);

			cell.setElement(creerElement(indexLigne,indexCellule,Integer.valueOf(elements[indxCell]).intValue()));

			row.addCell(cell);
			posX = posX + TAILLE;
			indexCellule++;
		}

		return row;
	}

	/**
	 * Methode permettant de creer un element.<br/>
	 * @param posX position X.<br/>
	 * @param posY position Y.<br/>
	 * @param numero
	 *            Numero element.<br/>
	 * @return Retourne element;
	 */
	private static AbstractElement creerElement(final int indexNumLigne,final int indexNumCellule,final int numero) {
		AbstractElement element = null;
		switch (numero) {
		
		case 0: 
			element = new ElementVide(indexNumLigne,indexNumCellule);
			break;
		
		case 1:
			element = new Mur(indexNumLigne,indexNumCellule);
			break;
			
		case 2:
			element = new Pastille(indexNumLigne,indexNumCellule);
			break;
			
		case 3: 
			element = new Pacman(indexNumLigne,indexNumCellule);	
			break;
		
		case 4:
			element = new Fantome1(indexNumLigne,indexNumCellule);	
			break;
		case 5:
			element = new Fantome2(indexNumLigne,indexNumCellule);
			break;
		case 6 :
			element = new Fantome3(indexNumLigne,indexNumCellule);
			break;
		default :
			break;
		}
		

		return element;

	}
}
