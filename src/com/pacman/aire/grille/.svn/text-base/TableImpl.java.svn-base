package com.pacman.aire.grille;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.pacman.element.AbstractElement;
import com.pacman.elementpt.AbstractElementAManger;
import com.pacman.etre.Fantome1;
import com.pacman.etre.Fantome2;
import com.pacman.etre.Fantome3;
import com.pacman.etre.Pacman;
import com.pacman.strategydeplacement.algoaetoile.Mover;

public class TableImpl implements ITable {
	private transient final List<IRow> listRow;

//	private transient final boolean[][] visited;

	private transient final int nbLigne;

	private transient final int nbColonne;

	public TableImpl(final int nbLigne,final int nbColonne) {
		listRow = new ArrayList<IRow>();
	//	visited = new boolean[nbColonne][nbLigne];
		this.nbLigne = nbLigne;
		this.nbColonne = nbColonne;
	}

	/**
	 * 
	 * {@inheritDoc}
	 */
	public void addRow(final IRow row) {
		listRow.add(row);
	}

	/**
	 * * {@inheritDoc}
	 */
	public int getRowNumber() {
		return listRow.size();
	}

	public IRow getRow(final int index) {
		return (IRow) listRow.get(index);
	}

	public Iterator<IRow> iterator() {
		return (listRow != null) ? listRow.iterator() : null;
	}

	public void draw(final Graphics theGraphic) {
		if (this != null) {
			for (IRow row : this.listRow) {
				row.draw(theGraphic);
			}
		}
	}
	
	public void videGrille(){
		if (this != null){
			for (IRow row : this.listRow){
				final Iterator<ICell> itRow = row.iterator();
				while (itRow.hasNext()){
				    ICell cell = itRow.next();
					cell = null;
				}
				row = null;
			}
		}
	}

	/**
	 * 
	 * {@inheritDoc}
	 */
	public ICell getCell(final int theIndexLigne,final int theIndexColonne) {
		ICell cell = null;
		final IRow row = this.getRow(theIndexLigne);
		if (row != null) {
			cell = row.getCell(theIndexColonne);
		}
		return cell;
	}

	public void setCell(final int theIndexLigne,final int theIndexColonne,final ICell theCell) {
		final ICell cell = null;
		final IRow row = this.getRow(theIndexLigne);
		if (row != null) {
			row.setCell(theIndexColonne, cell);
		}
	}

	public ICell findPacman() {

		ICell cellPacman = null;
		final Iterator<IRow> itRow = this.iterator();
		while (itRow.hasNext()) {
			final IRow row = (IRow) itRow.next();
			final Iterator<ICell> itCell = row.iterator();
			while (itCell.hasNext()) {
				final ICell cell = (ICell) itCell.next();
				final AbstractElement element = cell.getElement();
				if (element instanceof Pacman) {
					cellPacman = cell;
					break;
				}
			}

		}
		return cellPacman;
	}

	public ICell findFantome2() {
		ICell cellFantome2 = null;
		for (IRow row : this.listRow) {
			final Iterator<ICell> itCell = row.iterator();
			while (itCell.hasNext()) {
				final ICell cell = itCell.next();
				final AbstractElement element = cell.getElement();
				if (element instanceof Fantome2) {
					cellFantome2 = cell;
					break;
				}
			}

		}
		return cellFantome2;

	}

	public ICell findFantome1() {
		ICell cellFantome1 = null;
		for (IRow row : this.listRow) {
			final Iterator<ICell> itCell = row.iterator();
			while (itCell.hasNext()) {
				final ICell cell = (ICell) itCell.next();
				final AbstractElement element = cell.getElement();
				if (element instanceof Fantome1) {
					cellFantome1 = cell;
					break;
				}
			}

		}
		return cellFantome1;
	}

	public ICell findFantome3() {
		ICell cellFantome3 = null;
		for (IRow row : this.listRow) {
			final Iterator<ICell> itCell = row.iterator();
			while (itCell.hasNext()) {
				final ICell cell = (ICell) itCell.next();
				final AbstractElement element = cell.getElement();
				if (element instanceof Fantome3) {
					cellFantome3 = cell;
					break;
				}
			}

		}
		return cellFantome3;
	}
	
	@Override
	public boolean verifieExistElementAManger() {
		boolean verifieElementAManger = false;
		for (IRow row : this.listRow) {
			final Iterator<ICell> itCell = row.iterator();
			while (itCell.hasNext()) {
				final ICell cell = (ICell) itCell.next();
				final AbstractElement element = cell.getElement();
				if (element instanceof AbstractElementAManger) {
					verifieElementAManger = true;
					break;
				}
			}

		}
		return verifieElementAManger;
	}


	public boolean blocked(final Mover mover,final int posX,final int posY) {
		final ICell cell = this.getCell(posY, posX);

		final AbstractElement element = (AbstractElement) cell.getElement();

		return (!element.isAccessible());
	}

	public float getCost(final Mover mover,final int startX,final int startY,final int targerX,final int targetY) {
		return 1;
	}

	public int getHeightInTiles() {
		return this.nbLigne;
	}

	public int getWidthInTiles() {
		return this.nbColonne;
	}

	public void pathFinderVisited(final int posX,final int posY) {
	//	this.visited[posX][posY] = true;

	}


}
