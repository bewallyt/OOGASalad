package engine;

import java.awt.Graphics2D;
import java.awt.Image;

import engine.gridobject.GridObject;
import engine.images.ScaledImage;

public class Dialogue{
	private String myDialogue ="";
	private Image myImage;
	
	public Dialogue(String image, String dialogue) {

		myImage = new ScaledImage(700,200,image).scaleImage();
		myDialogue = dialogue;
	}
	
	public void setDialogue(String str){
		myDialogue = str;
	}
	public Image getImage(){
		return myImage;
	}
	public String getDialogue(){
		return myDialogue;
	}

}
