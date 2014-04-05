package engine;

import java.awt.Graphics2D;

public class GameObject {

	private String myImageName;
	public GameObject(String image){
		myImageName=image;
	}
	
	public void paint(Graphics2D g) {
		g.drawImage(myImage, myX, myY, null);
	}
}
