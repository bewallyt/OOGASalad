package engine.world;

import java.awt.Graphics2D;
import java.awt.Image;

import engine.gridobject.person.Player;
import engine.images.ScaledImage;

public class TitleWorld extends World {

	private int myWidth;
	private int myHeight;
	private Image myBackground;

	public TitleWorld(int playWidth, int playHeight, Player p) {		
		super(playWidth, playHeight, p);
		myWidth = playWidth;
		myHeight = playHeight;
	}


	public void setBackground(String imageFile) {
		myBackground = new ScaledImage(myWidth, myHeight, imageFile).scaleImage();
	}
	
	public void paint(Graphics2D g) {
		g.drawImage(myBackground, 0, 0, null);
	}
	
	public Image getBackground(){
		return myBackground;
	}
}
