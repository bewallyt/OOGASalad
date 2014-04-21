package engine.menu;

import engine.dialogue.MatrixNode;

public abstract class MenuNode implements MatrixNode {

	public MenuNode() {

	}

	abstract void doAction();
	abstract void changeWorld();
	abstract void changeState();
	

	@Override
	public String getString() {
		// TODO Auto-generated method stub
		return null;
	}

}
