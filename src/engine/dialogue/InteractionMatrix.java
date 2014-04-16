package engine.dialogue;

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
	
	public MatrixNode getCurrentNode() {
		return myNodes[selectedNodeX][selectedNodeY];
	}
	
	public void moveUp() {
		if (selectedNodeY != 0) {
			selectedNodeY--;
		}
		
	}
	
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
