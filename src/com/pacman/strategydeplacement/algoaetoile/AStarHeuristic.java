package com.pacman.strategydeplacement.algoaetoile;

public interface AStarHeuristic {

	/**
	 * Get the additional heuristic cost of the given tile. This controls the
	 * order in which tiles are searched while attempting to find a path to the
	 * target location. The lower the cost the more likely the tile will be
	 * searched.
	 * 
	 * @param map
	 *            The map on which the path is being found
	 * @param mover
	 *            The entity that is moving along the path
	 * @param posX
	 *            The x coordinate of the tile being evaluated
	 * @param posY
	 *            The y coordinate of the tile being evaluated
	 * @param targetX
	 *            The x coordinate of the target location
	 * @param targetY
	 *            Teh y coordinate of the target location
	 * @return The cost associated with the given tile
	 */
	float getCost(TileBasedMap map, Mover mover, int posX, int posY, int targetX, int targetY);

}
