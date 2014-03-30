package engine;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public abstract class GridObject{

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
		System.out.println("pained");
		g.drawImage(myImage, myX, myY, null);
		
		

	}
	
	public void setCollisionHandler(CollisionHandler handler) {
		myCollisionHandler = handler;
	}
	
	public void move() {}; // default is to do nothing
	public void doCollision(GridObject o){};
	
}
