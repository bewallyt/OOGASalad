package engine.dialogue;

/**
 * This is a class that takes MatrixNodes and organizes them in such a way that
 * a user can "select" different options. The idea is that this can be used for
 * any sort of backend system for a display that allows a user to choose from a
 * set of options.
 * 
 */

public class InteractionMatrix2x2 extends InteractionMatrix {

	public InteractionMatrix2x2() {
		selectedNodeX = 0;
		selectedNodeY = 0;
		myNodes = new MatrixNode[2][2];
		myXDimension = 2;
		myYDimension = 2;
	}

	/**
	 * Allows user to toggle up in the select box, selecting the option above
	 * the current option
	 */
	public void moveUp() {
		if (selectedNodeY != 0) {
			selectedNodeY--;
		}
	}

	/**
	 * Allows user to toggle down in the select box, selecting the option below
	 * the current option
	 */
	public void moveDown() {
		if (selectedNodeY != 1) {
			selectedNodeY++;
		}
	}

	/**
	 * Allows user to toggle left in the select box, selecting the option to the
	 * left of the current option
	 */
	public void moveLeft() {
		if (selectedNodeX != 0) {
			selectedNodeX--;
		}
	}

	/**
	 * Allows user to toggle right in the select box, selecting the option to
	 * the right of the current option
	 */
	public void moveRight() {
		if (selectedNodeX != 1) {
			selectedNodeX++;
		}
	}
}
