package engine;

import java.awt.Image;

public abstract class GridObject {

	protected double myX;
	protected double myY;
	protected CollisionHandler myCollisionHandler;
	protected Image myImage;
	
	public GridObject(double x, double y) {
		myX = x;
		myY = y;
		
		myCollisionHandler = null;
	}
	
	public void setImage(Image i) {
		myImage = i;
	}
	
	public void setCollisionHandler(CollisionHandler handler) {
		myCollisionHandler = handler;
	}
	
	public void move() {}; // default is to do nothing
	public void doCollision(GridObject o){};
	
}
