package com.pacman.strategydeplacement.algoaetoile;


/**
 * A heuristic that uses the tile that is closest to the target
 * as the next best tile.
 * 
 * @author Kevin Glass
 */
public class ClosestHeuristic implements AStarHeuristic {
	/**
	 * @see AStarHeuristic#getCost(TileBasedMap, Mover, int, int, int, int)
	 */
	public float getCost(final TileBasedMap map,final Mover mover,final int startX,final int startY,final int targetX,final int targetY) {		
		final float distanceX = targetX - startX;
		final float distanceY = targetY - startY;
		
		final float result = (float) (Math.sqrt((distanceX*distanceX)+(distanceY*distanceY)));
		
		return result;
	}

}

 
