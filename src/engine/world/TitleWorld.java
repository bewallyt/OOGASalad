package engine.world;

import java.awt.Graphics2D;
import java.awt.Image;


import engine.gridobject.person.Player;
import engine.images.ScaledImage;

public class TitleWorld extends World {

	private int myWidth = 500;
	private int myHeight = 500;
	private Image myBackground;
	private String myGame;

	public TitleWorld(int playWidth, int playHeight, Player p, String g) {		
		super(playWidth, playHeight, p);
		myGame = g;
		myBackground = new ScaledImage(myWidth, myHeight, myGame).scaleImage();
		
	}

	
	public Image getBackground(){
		return myBackground;
	}
	
	
}
