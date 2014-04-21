package engine.menu.nodes;

import engine.dialogue.MatrixNode;

public abstract class MenuNode implements MatrixNode {

	public MenuNode() {

	}

	public abstract void doAction();
	public abstract void changeWorld();
	public abstract void changeState();
	

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

}
