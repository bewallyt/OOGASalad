package authoring;

import javax.swing.*;

public class ScrollButton extends JButton {
	
	private int myXChange;
	private int myYChange;
	
	public ScrollButton(String s, int xchange, int ychange){
		super(s);
		myXChange = xchange;
		myYChange = ychange;
	}
	
	public int getXChange(){
		return myXChange;
	}
	
	public int getYChange(){
		return myYChange;
	}
}
