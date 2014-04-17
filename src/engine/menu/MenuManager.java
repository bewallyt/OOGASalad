package engine.menu;

import java.awt.event.KeyEvent;

import engine.state.AbstractState;

public class MenuManager {
	
	private AbstractState myState;
	
	public void setState(AbstractState state) {
		myState = state;
	}
	
	public void keyPressed(KeyEvent e) {
		myState.keyPressed(e);
	}

	public void keyReleased(KeyEvent e) {
		myState.keyReleased(e);
	}

	public void moveCursorUp() {
		// TODO Auto-generated method stub
		
	}

	public void select() {
		// TODO Auto-generated method stub
		
	}

	public void toggleMenu() {
		// TODO Auto-generated method stub
		
	}

	public void moveCursorLeft() {
		// TODO Auto-generated method stub
		
	}

	public void moveCursorRight() {
		// TODO Auto-generated method stub
		
	}

	public void moveCursorDown() {
		// TODO Auto-generated method stub
		
	}
}
