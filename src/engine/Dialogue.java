package engine;

import java.awt.Graphics2D;
import java.awt.Image;

import engine.gridobject.GridObject;
import engine.images.ScaledImage;

public class Dialogue{
	private String myDialogue ="";
	private String myImageName;
	private Image myImage;
	
	public Dialogue(String image, String dialogue) {

		myImageName = image;
		myDialogue = dialogue;
	}
	
	public Image getImage(){
		return myImage;
	}
	public void setDialogue(String str){
		myDialogue = str;
	}
	
	public String getDialogue(){
		return myDialogue;
	}
	public void setSize(int width, int height){
		myImage = new ScaledImage(width,height,myImageName).scaleImage();
	}

}
