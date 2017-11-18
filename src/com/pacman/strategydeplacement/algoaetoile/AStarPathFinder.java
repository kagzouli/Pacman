package com.pacman.strategydeplacement.algoaetoile;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * A path finder implementation that uses the AStar heuristic based algorithm
 * to determine a path. 
 * 
 * @author Kevin Glass
 */
public class AStarPathFinder implements PathFinder {
	/** The set of nodes that have been searched through */
	private transient final List<Node> closed = new ArrayList<Node>();
	/** The set of nodes that we do not yet consider fully searched */
	private transient final SortedList open = new SortedList();
	
	/** The map being searched */
	private transient TileBasedMap map;
	/** The maximum depth of search we're willing to accept before giving up */
	private transient int maxSearchDistance;
	
	/** The complete set of nodes across the map */
	private transient Node[][] nodes;
	/** True if we allow diaganol movement */
	private transient boolean allowDiagMovement;
	/** The heuristic we're applying to determine which nodes to search first */
	private transient AStarHeuristic heuristic;
	
	/**
	 * Create a path finder with the default heuristic - closest to target.
	 * 
	 * @param map The map to be searched
	 * @param maxSearchDistance The maximum depth we'll search before giving up
	 * @param allowDiagMovement True if the search should try diaganol movement
	 */
	public AStarPathFinder(final TileBasedMap map,final int maxSearchDistance,final boolean allowDiagMovement) {
		this(map, maxSearchDistance, allowDiagMovement, new ClosestHeuristic());
	}

	/**
	 * Create a path finder 
	 * 
	 * @param heuristic The heuristic used to determine the search order of the map
	 * @param map The map to be searched
	 * @param maxSearchDistance The maximum depth we'll search before giving up
	 * @param allowDiagMovement True if the search should try diaganol movement
	 */
	public AStarPathFinder(final TileBasedMap map,final int maxSearchDistance, 
						   final boolean allowDiagMovement, final AStarHeuristic heuristic) {
		this.heuristic = heuristic;
		this.map = map;
		this.maxSearchDistance = maxSearchDistance;
		this.allowDiagMovement = allowDiagMovement;
		
		nodes = new Node[map.getWidthInTiles()][map.getHeightInTiles()];
		for (int x=0;x<map.getWidthInTiles();x++) {
			for (int y=0;y<map.getHeightInTiles();y++) {
				nodes[x][y] = new Node(x,y);
			}
		}
	}
	
	/**
	 * @see PathFinder#findPath(Mover, int, int, int, int)
	 */
	public Path findPath(final Mover mover,final int startX,final int startY,final int targetX,final int targetY) {
		// easy first check, if the destination is blocked, we can't get there
		if (map.blocked(mover, targetX, targetY)) {
			return null;
		}
		
		// initial state for A*. The closed group is empty. Only the starting
		// tile is in the open list and it'e're already there
		nodes[startX][startY].cost = 0;
		nodes[startX][startY].depth = 0;
		closed.clear();
		open.clear();
		open.add(nodes[startX][startY]);
		
		nodes[targetX][targetY].parent = null;
		
		// while we haven'n't exceeded our max search depth
		int maxDepth = 0;
		while ((maxDepth < maxSearchDistance) && (open.size() != 0)) {
			// pull out the first node in our open list, this is determined to 
			// be the most likely to be the next step based on our heuristic
			final Node current = getFirstInOpen();
			if (current == nodes[targetX][targetY]) {
				break;
			}
			
			removeFromOpen(current);
			addToClosed(current);
			
			// search through all the neighbours of the current node evaluating
			// them as next steps
			for (int x=-1;x<2;x++) {
				for (int y=-1;y<2;y++) {
					// not a neighbour, its the current tile
					if ((x == 0) && (y == 0)) {
						continue;
					}
					
					// if we're not allowing diaganol movement then only 
					// one of x or y can be set
					if (!allowDiagMovement) {
						if ((x != 0) && (y != 0)) {
							continue;
						}
					}
					
					// determine the location of the neighbour and evaluate it
					final int xp = x + current.posX;
					final int yp = y + current.posY;
					
					if (isValidLocation(mover,startX,startY,xp,yp)) {
						// the cost to get to this node is cost the current plus the movement
						// cost to reach this node. Note that the heursitic value is only used
						// in the sorted open list
						final float nextStepCost = current.cost + getMovementCost(mover, current.posX, current.posY, xp, yp);
						final Node neighbour = nodes[xp][yp];
						map.pathFinderVisited(xp, yp);
						
						// if the new cost we've determined for this node is lower than 
						// it has been previously makes sure the node hasn'e've
						// determined that there might have been a better path to get to
						// this node so it needs to be re-evaluated
						if (nextStepCost < neighbour.cost) {
							if (inOpenList(neighbour)) {
								removeFromOpen(neighbour);
							}
							if (inClosedList(neighbour)) {
								removeFromClosed(neighbour);
							}
						}
						
						// if the node hasn't already been processed and discarded then
						// reset it's cost to our current cost and add it as a next possible
						// step (i.e. to the open list)
						if (!inOpenList(neighbour) && !(inClosedList(neighbour))) {
							neighbour.cost = nextStepCost;
							neighbour.heuristic = getHeuristicCost(mover, xp, yp, targetX, targetY);
							maxDepth = Math.max(maxDepth, neighbour.setParent(current));
							addToOpen(neighbour);
						}
					}
				}
			}
		}

		// since we'e've run out of search 
		// there was no path. Just return null
		if (nodes[targetX][targetY].parent == null) {
			return null;
		}
		
		// At this point we've definitely found a path so we can uses the parent
		// references of the nodes to find out way from the target location back
		// to the start recording the nodes on the way.
		final Path path = new Path();
		Node target = nodes[targetX][targetY];
		while (target != nodes[startX][startY]) {
			path.prependStep(target.posX, target.posY);
			target = target.parent;
		}
		path.prependStep(startX,startY);
		
		// thats it, we have our path 
		return path;
	}

	/**
	 * Get the first element from the open list. This is the next
	 * one to be searched.
	 * 
	 * @return The first element in the open list
	 */
	protected Node getFirstInOpen() {
		return (Node) open.first();
	}
	
	/**
	 * Add a node to the open list
	 * 
	 * @param node The node to be added to the open list
	 */
	protected void addToOpen(final Node node) {
		open.add(node);
	}
	
	/**
	 * Check if a node is in the open list
	 * 
	 * @param node The node to check for
	 * @return True if the node given is in the open list
	 */
	protected boolean inOpenList(final Node node) {
		return open.contains(node);
	}
	
	/**
	 * Remove a node from the open list
	 * 
	 * @param node The node to remove from the open list
	 */
	protected void removeFromOpen(final Node node) {
		open.remove(node);
	}
	
	/**
	 * Add a node to the closed list
	 * 
	 * @param node The node to add to the closed list
	 */
	protected void addToClosed(final Node node) {
		closed.add(node);
	}
	
	/**
	 * Check if the node supplied is in the closed list
	 * 
	 * @param node The node to search for
	 * @return True if the node specified is in the closed list
	 */
	protected boolean inClosedList(final Node node) {
		return closed.contains(node);
	}
	
	/**
	 * Remove a node from the closed list
	 * 
	 * @param node The node to remove from the closed list
	 */
	protected void removeFromClosed(final Node node) {
		closed.remove(node);
	}
	
	/**
	 * Check if a given location is valid for the supplied mover
	 * 
	 * @param mover The mover that would hold a given location
	 * @param startX The starting x coordinate
	 * @param startY The starting y coordinate
	 * @param posX The x coordinate of the location to check
	 * @param y The y coordinate of the location to check
	 * @return True if the location is valid for the given mover
	 */
	protected boolean isValidLocation(final Mover mover,final int startX,final int startY,final int posX,final int posY) {
		boolean invalid = (posX < 0) || (posY < 0) || (posX >= map.getWidthInTiles()) || (posY >= map.getHeightInTiles());
		
		if ((!invalid) && ((startX != posX) || (startY != posY))) {
			invalid = map.blocked(mover, posX, posY);
		}
		
		return !invalid;
	}
	
	/**
	 * Get the cost to move through a given location
	 * 
	 * @param mover The entity that is being moved
	 * @param startX The x coordinate of the tile whose cost is being determined
	 * @param startY The y coordiante of the tile whose cost is being determined
	 * @param targetX The x coordinate of the target location
	 * @param targetY The y coordinate of the target location
	 * @return The cost of movement through the given tile
	 */
	public float getMovementCost(final Mover mover,final int startX,final int startY,final int targetX,final int targetY) {
		return map.getCost(mover, startX, startY, targetX, targetY);
	}

	/**
	 * Get the heuristic cost for the given location. This determines in which 
	 * order the locations are processed.
	 * 
	 * @param mover The entity that is being moved
	 * @param posX The x coordinate of the tile whose cost is being determined
	 * @param posY The y coordiante of the tile whose cost is being determined
	 * @param targetX The x coordinate of the target location
	 * @param targetY The y coordinate of the target location
	 * @return The heuristic cost assigned to the tile
	 */
	public float getHeuristicCost(final Mover mover,final int posX, final int posY, final int targetX,final int targetY) {
		return heuristic.getCost(map, mover, posX, posY, targetX, targetY);
	}
	
	/**
	 * A simple sorted list
	 *
	 * @author kevin
	 */
	private class SortedList {
		/** The list of elements */
		private List list = new ArrayList();
		
		/**
		 * Retrieve the first element from the list
		 *  
		 * @return The first element from the list
		 */
		public Object first() {
			return list.get(0);
		}
		
		/**
		 * Empty the list
		 */
		public void clear() {
			list.clear();
		}
		
		/**
		 * Add an element to the list - causes sorting
		 * 
		 * @param o The element to add
		 */
		public void add(final Object object) {
			list.add(object);
			Collections.sort(list);
		}
		
		/**
		 * Remove an element from the list
		 * 
		 * @param o The element to remove
		 */
		public void remove(final Object object) {
			list.remove(object);
		}
	
		/**
		 * Get the number of elements in the list
		 * 
		 * @return The number of element in the list
 		 */
		public int size() {
			return list.size();
		}
		
		/**
		 * Check if an element is in the list
		 * 
		 * @param o The element to search for
		 * @return True if the element is in the list
		 */
		public boolean contains(final Object object) {
			return list.contains(object);
		}
	}
	
	/**
	 * A single node in the search graph
	 */
	private class Node implements Comparable {
		/** The x coordinate of the node */
		private transient final int posX;
		/** The y coordinate of the node */
		private transient final int posY;
		/** The path cost for this node */
		private transient float cost;
		/** The parent of this node, how we reached it in the search */
		private transient Node parent;
		/** The heuristic cost of this node */
		private transient float heuristic;
		/** The search depth of this node */
		private transient int depth;
		
		/**
		 * Create a new node
		 * 
		 * @param posX The x coordinate of the node
		 * @param posY The y coordinate of the node
		 */
		public Node(final int posX,final int posY) {
			this.posX = posX;
			this.posY = posY;
		}
		
		/**
		 * Set the parent of this node
		 * 
		 * @param parent The parent node which lead us to this node
		 * @return The depth we have no reached in searching
		 */
		public int setParent(final Node parent) {
			depth = parent.depth + 1;
			this.parent = parent;
			
			return depth;
		}
		
		/**
		 * @see Comparable#compareTo(Object)
		 */
		public int compareTo(final Object other) {
			final Node nodeOther = (Node) other;
			
			final float floa = heuristic + cost;
			final float otherfloa = nodeOther.heuristic + nodeOther.cost;
			
			int valeurRetour = 0;
			if (floa < otherfloa) {
				valeurRetour = -1;
			} else if (floa > otherfloa) {
				valeurRetour =  1;
			} else {
				valeurRetour =  0;
			}
			return valeurRetour;
			
		}
	}
}


 
