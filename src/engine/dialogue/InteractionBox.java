package engine.dialogue;

import java.awt.Graphics2D;

public interface InteractionBox {

	public void paintDisplay(Graphics2D g, int xSize, int ySize, int xOffset, int yOffset);
	public void getNextText();


}
