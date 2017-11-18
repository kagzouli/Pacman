package com.pacman.strategydeplacement.algoaetoile;

/**
 * The description for the data we're pathfinding over. This provides the contract between the data being searched (i.e. the in game map) and the path finding generic tools
 * 
 * @author Kevin Glass
 */
public interface TileBasedMap {
	/**
	 * Get the width of the tile map. The slightly odd name is used to distiguish this method from commonly used names in game maps.
	 * 
	 * @return The number of tiles across the map
	 */
	int getWidthInTiles();

	/**
	 * Get the height of the tile map. The slightly odd name is used to distiguish this method from commonly used names in game maps.
	 * 
	 * @return The number of tiles down the map
	 */
	int getHeightInTiles();

	/**
	 * Notification that the path finder visited a given tile. This is used for debugging new heuristics.
	 * 
	 * @param posX The x coordinate of the tile that was visited
	 * @param posY The y coordinate of the tile that was visited
	 */
	void pathFinderVisited(int posX, int posY);

	/**
	 * Check if the given location is blocked, i.e. blocks movement of the supplied mover.
	 * 
	 * @param mover The mover that is potentially moving through the specified tile.
	 * @param posX The x coordinate of the tile to check
	 * @param posY The y coordinate of the tile to check
	 * @return True if the location is blocked
	 */
	boolean blocked(Mover mover, int posX, int posY);

	/**
	 * Get the cost of moving through the given tile. This can be used to make certain areas more desirable. A simple and valid implementation of this method would be to return 1 in all cases.
	 * 
	 * @param mover The mover that is trying to move across the tile
	 * @param startX The x coordinate of the tile we're moving from
	 * @param startY The y coordinate of the tile we're moving from
	 * @param toX The x coordinate of the tile we're moving to
	 * @param toY The y coordinate of the tile we're moving to
	 * @return The relative cost of moving across the given tile
	 */
	float getCost(Mover mover, int startX, int startY, int toX, int toY);
}
