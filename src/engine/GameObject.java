package engine;

import java.awt.Graphics2D;
import java.awt.Image;

import engine.images.ScaledImage;

public class GameObject{

	private Image myImage;
	private int myX;
	private int myY;
	private int myWidth;
	private int myHeight;
	public GameObject(String image, int x, int y){
		myX=x;
		myY=y;
	}
	
	public void paint(Graphics2D g) {
		g.drawImage(myImage, myX, myY, null);
	}
	public void setImage(String file) {
		Image img = new ScaledImage(myWidth,myHeight,file).scaleImage();
		myImage = img;	
	}
}
