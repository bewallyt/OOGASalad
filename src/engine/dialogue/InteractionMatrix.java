package engine.dialogue;



/**
 * This is a class that takes MatrixNodes and organizes them in such a way that
 * a user can "select" different options. The idea is that this can be used for any sort of
 * backend system for a display that allows a user to choose from a set of options.
 * 
 */

public class InteractionMatrix {


	
	private int selectedNodeX;
	private int selectedNodeY;
	
	private MatrixNode[][] myNodes;
	private static final int DIMENSION = 2;
	
	public InteractionMatrix() {
		selectedNodeX = 0;
		selectedNodeY = 0;
		myNodes = new MatrixNode[DIMENSION][DIMENSION];
	}
	
	public void setNode(MatrixNode m, int x, int y) {
		myNodes[x][y] = m;
	}
	
	/**
	 * Allows access to the currently highlighted/selected option in the menu
	 * @return currently selected node
	 */
	public MatrixNode getCurrentNode() {
		return myNodes[selectedNodeX][selectedNodeY];
	}
	
	/**
	 * Allows user to toggle up in the select box, selecting the option above the current option
	 */
	public void moveUp() {
		if (selectedNodeY != 0) {
			selectedNodeY--;
		}
		
	}
	
	/**
	 * Allows user to toggle down in the select box, selecting the option below the current option
	 */
	public void moveDown() {
		if (selectedNodeY != 1) {
			selectedNodeY++;
		}
	}
	
	public void moveLeft() {
		if (selectedNodeX != 0) {
			selectedNodeX--;
		}
	}
	
	public void moveRight() {
		if (selectedNodeX != 1) {
			selectedNodeX++;
		}
	}
	
	
}
