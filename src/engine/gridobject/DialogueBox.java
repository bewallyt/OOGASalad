package engine.gridobject;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

public class DialogueBox {
	private String myDialogue ="";
	String

	public DialogueBox(String image, String dialogue) {
		myDialogue = dialogue;
		Image img = scaleImage(,myHeight,file);
	}
	
	public void paint(Graphics2D g) {
		g.drawImage(myImage, 0, 12, null);
		g.drawString(myDialogue, myX, myY);
	}
	
	public void setDialogue(String str){
		myDialogue = str;
	}

}
