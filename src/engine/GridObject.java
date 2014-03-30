package engine;

import java.awt.Image;

public abstract class GridObject {

	protected double myX;
	protected double myY;
	protected double myDX;
	protected double myDY;
	protected CollisionHandler myCollisionHandler;
	protected Image myImage;
	
	public GridObject(double x, double y, double dx, double dy) {
		myX = x;
		myY = y;
		myDX = dx;
		myDY = dy;
		myCollisionHandler = null;
	}
	
	public void setImage(Image i) {
		myImage = i;
	}
	
	public void setCollisionHandler(CollisionHandler handler) {
		myCollisionHandler = handler;
	}
	
	public void move() {};
	public void doCollision(GridObject o){};
	
}
