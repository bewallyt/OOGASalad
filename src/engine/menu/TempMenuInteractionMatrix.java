package engine.menu;

import engine.dialogue.MatrixNode;

public class TempMenuInteractionMatrix {

	/**
	 * This is a class that takes MatrixNodes and organizes them in such a way
	 * that a user can "select" different options. The idea is that this can be
	 * used for any sort of backend system for a display that allows a user to
	 * choose from a set of options.
	 * 
	 */

	private int selectedNodeX;
	private int selectedNodeY;

	private MatrixNode[][] myNodes;
	private int myDimensionX = 2;
	private int myDimensionY = 2;

	public TempMenuInteractionMatrix() {
		selectedNodeX = 0;
		selectedNodeY = 0;
		myNodes = new MatrixNode[myDimensionX][myDimensionY];
	}

	public TempMenuInteractionMatrix(int DimensionX, int DimensionY) {
		selectedNodeX = 0;
		selectedNodeY = 0;
		myNodes = new MatrixNode[DIMENSION_X][DIMENSION_Y];
	}

	public void setNode(MatrixNode mNode, int x, int y) {
		myNodes[x][y] = mNode;
	}

	/**
	 * Allows access to the currently highlighted/selected option in the menu
	 * 
	 * @return currently selected node
	 */
	public MatrixNode getCurrentNode() {
		return myNodes[selectedNodeX][selectedNodeY];
	}

	public MatrixNode getNode(int i, int j) {
		return myNodes[i][j];
	}

	public int[] getSelectedNodeLocation() {
		int[] ret = { selectedNodeX, selectedNodeY };
		return ret;
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

	public int getDimensionX() {
		return DIMENSION_X;
	}

	public int getDimensionY() {
		return DIMENSION_Y;
	}

}
