package engine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

public class Player extends RuleFollower{



	
	public Player(double x, double y, double dx, double dy) {
		super(x,y,dx,dy);
		myItems = null;
		

	}

	@Override 
	public void move() {

	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP)
			myY +=myDY;
		if (e.getKeyCode() == KeyEvent.VK_DOWN)
			myY -= myDY;
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
			myX += myDX;
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
			myX -= myDX;
		
		move();

	}


}
