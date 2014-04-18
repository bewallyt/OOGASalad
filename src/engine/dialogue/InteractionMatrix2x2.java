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
