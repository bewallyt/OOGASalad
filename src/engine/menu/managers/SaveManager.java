package engine.menu.managers;

import java.awt.Graphics2D;

import engine.dialogue.InteractionBox;

public class SaveManager implements InteractionBox {

	@Override
	public void paintDisplay(Graphics2D g, int xSize, int ySize, int xOffset,
			int yOffset) {
		g.drawString("Save dis shit", (int) xSize/10, ySize/2+120);
		System.out.println("Save dis shit");
		
	}

	@Override
	public void getNextText() {
		// TODO Auto-generated method stub
		
	}

}
