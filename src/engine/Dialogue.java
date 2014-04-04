package engine;

import java.awt.Graphics2D;
import java.awt.Image;

import engine.images.ScaledImage;

public class Dialogue {
	private String myDialogue ="";
	private Image myImage;

	public Dialogue(String image, String dialogue) {
		myDialogue = dialogue;
		myImage = new ScaledImage(80,20, image).scaleImage();
	}
	
	public void paint(Graphics2D g) {
		g.drawImage(myImage, 0, 12, null);
		System.out.println("drawn");
	//	g.drawString(myDialogue, myX, myY);
	}
	
	public void setDialogue(String str){
		myDialogue = str;
	}

}
