package engine.dialogue;

public class InteractionMatrix2x2 extends InteractionMatrix {

	public InteractionMatrix2x2() {
		selectedNodeX = 0;
		selectedNodeY = 0;
		myNodes = new MatrixNode[2][2];
		myXDimension = 2;
		myYDimension = 2;
	}
	
	public void moveUp() {
		if (selectedNodeY != 0 && myNodes[selectedNodeX][selectedNodeY-1] != null &&
				myNodes[selectedNodeX][selectedNodeY-1].toString() != null) {
			selectedNodeY--;
		}
		
	}

	public void moveDown() {
		if (selectedNodeY != 1 && myNodes[selectedNodeX][selectedNodeY+1] != null &&
				myNodes[selectedNodeX][selectedNodeY+1].toString() != null) {
			selectedNodeY++;
		}
	}

	public void moveLeft() {
		if (selectedNodeX != 0 && myNodes[selectedNodeX-1][selectedNodeY] != null &&
				myNodes[selectedNodeX-1][selectedNodeY].toString() != null) {
			selectedNodeX--;
		}
	}

	public void moveRight() {
		if (selectedNodeX != 1 && myNodes[selectedNodeX+1][selectedNodeY] != null 
				&& myNodes[selectedNodeX+1][selectedNodeY].toString() != null) {
			selectedNodeX++;
		}
	}
	public void resetMatrixPosition(){
		selectedNodeX=0; selectedNodeY=0;
	}
}
