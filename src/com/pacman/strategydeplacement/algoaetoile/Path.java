package com.pacman.strategydeplacement.algoaetoile;


import java.util.ArrayList;
import java.util.List;

/**
 * A path determined by some path finding algorithm. A series of steps from
 * the starting location to the target location. This includes a step for the
 * initial location.
 * 
 * @author Kevin Glass
 */
public class Path {
	/** The list of steps building up this path */
	private transient final List<Step> steps = new ArrayList<Step>();
	
	/**
	 * Get the length of the path, i.e. the number of steps
	 * 
	 * @return The number of steps in this path
	 */
	public int getLength() {
		return steps.size();
	}
	
	/**
	 * Get the step at a given index in the path
	 * 
	 * @param index The index of the step to retrieve. Note this should
	 * be >= 0 and < getLength();
	 * @return The step information, the position on the map.
	 */
	public Step getStep(final int index) {
		return (Step) steps.get(index);
	}
	
	/**
	 * Get the x coordinate for the step at the given index
	 * 
	 * @param index The index of the step whose x coordinate should be retrieved
	 * @return The x coordinate at the step
	 */
	public int getX(final int index) {
		return getStep(index).posX;
	}

	/**
	 * Get the y coordinate for the step at the given index
	 * 
	 * @param index The index of the step whose y coordinate should be retrieved
	 * @return The y coordinate at the step
	 */
	public int getY(final int index) {
		return getStep(index).posY;
	}
	
	/**
	 * Append a step to the path.  
	 * 
	 * @param posX The x coordinate of the new step
	 * @param posY The y coordinate of the new step
	 */
	public void appendStep(final int posX,final int posY) {
		steps.add(new Step(posX,posY));
	}

	/**
	 * Prepend a step to the path.  
	 * 
	 * @param posX The x coordinate of the new step
	 * @param posY The y coordinate of the new step
	 */
	public void prependStep(final int posX,final int posY) {
		steps.add(0, new Step(posX, posY));
	}
	
	/**
	 * Check if this path contains the given step
	 * 
	 * @param posX The x coordinate of the step to check for
	 * @param posY The y coordinate of the step to check for
	 * @return True if the path contains the given step
	 */
	public boolean contains(final int posX,final int posY) {
		return steps.contains(new Step(posX,posY));
	}
	
	/**
	 * A single step within the path
	 * 
	 * @author Kevin Glass
	 */
	public class Step {
		/** The x coordinate at the given step */
		private final transient int posX;
		/** The y coordinate at the given step */
		private final transient int posY;
		
		/**
		 * Create a new step
		 * 
		 * @param posX The x coordinate of the new step
		 * @param posY The y coordinate of the new step
		 */
		public Step(final int posX,final int posY) {
			this.posX = posX;
			this.posY = posY;
		}
		
		/**
		 * Get the x coordinate of the new step
		 * 
		 * @return The x coodindate of the new step
		 */
		public int getPosX() {
			return posX;
		}

		/**
		 * Get the y coordinate of the new step
		 * 
		 * @return The y coodindate of the new step
		 */
		public int getPosY() {
			return posY;
		}
		
		/**
		 * @see Object#hashCode()
		 */
		public int hashCode() {
			return posX*posY;
		}

		/**
		 * @see Object#equals(Object)
		 */
		public boolean equals(final Object other) {
			boolean isEquals = false;
			if (other instanceof Step) {
				final Step stepOther = (Step) other;
				
				isEquals =  (stepOther.posX == posX) && (stepOther.posY == posY);
			}
			
			return isEquals;
		}
	}
}
