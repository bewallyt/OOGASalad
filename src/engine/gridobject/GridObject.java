package engine.gridobject;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public abstract class GridObject{

	private static final int WIDTH = 6;
	private static final int HEIGHT = 5;
	protected int myX;
	protected int myY;
	protected CollisionHandler myCollisionHandler;
	protected Image myImage;
	
	public GridObject(int x, int y) {
		myX = x;
		myY = y;
		
		myCollisionHandler = null;
	}
	
	public void setImage(String file) {
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File(file));
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		myImage = img;
		
	}
	
	public void paint(Graphics2D g) {
		System.out.println("paitned");
		g.drawImage(myImage, myX, myY, null);
	}
	
	public void setCollisionHandler(CollisionHandler handler) {
		myCollisionHandler = handler;
	}
	
	public Rectangle getBounds() {
		return new Rectangle(myX, myY-HEIGHT, WIDTH, HEIGHT);	
	}
	
	public void move() {}; // default is to do nothing
	public void doCollision(GridObject o){};
	
}
