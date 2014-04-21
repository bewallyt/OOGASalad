package engine.state;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public abstract class AbstractState implements KeyListener {

	public AbstractState() {
		System.out.println("new state");
	}

	@Override
	public abstract void keyTyped(KeyEvent e);

	@Override
	public abstract void keyPressed(KeyEvent e);

	@Override
	public abstract void keyReleased(KeyEvent e);

	public String getDisplayString() {
		// TODO Auto-generated method stub
		return null;
	}

}
