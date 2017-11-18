package com.pacman.aire.grille;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class RowImpl implements IRow
{
    private transient final List<ICell> listCell;

    public RowImpl()
    {
        listCell = new ArrayList<ICell>();
    }

    /**
     * 
     * {@inheritDoc}
     */
    public void addCell(final ICell cell)
    {
        listCell.add(cell);
    }

    /**
     * 
     * {@inheritDoc}
     */
    public int getCellNumber()
    {
        return listCell.size();
    }

    /**
     * 
     * {@inheritDoc}
     */
    public ICell getCell(final int index)
    {
        return (ICell) listCell.get(index);
    }

    /**
     * 
     * {@inheritDoc}
     */
    public Iterator <ICell>iterator()
    {
        return (listCell != null) ? listCell.iterator() : null;
    }

    /**
     * 
    * {@inheritDoc}
     */
    public void draw(final Graphics theGraphic)
    {
        if (this != null)
        {
            final Iterator<ICell> itCell = this.iterator();

            while (itCell.hasNext())
            {
                final ICell cell = (ICell) itCell.next();
                cell.draw(theGraphic, cell.getPosX(), cell.getPosY(), cell.getLongueur(), cell.getLargeur());
            }
        }
    }

  
    public void setCell(final int theIndex,final  ICell theCell)
    {
        listCell.set(theIndex,theCell);
        
    }

}
