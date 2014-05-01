package engine.dialogue;

import java.awt.Graphics2D;

/**
 * Whatever you want to be displayed on the canvas (referring to menues, dialogue boxes, etc.), it must 
 * implement this interface. Whatever implements this interface can be placed into the <code>TextDisplayer</code>
 * that the canvas has.
 *
 */

public interface InteractionBox {

	/**
	 * Does the actual painting of the InteractionBox.
	 * @param g
	 * @param xSize
	 * @param ySize
	 * @param xOffset
	 * @param yOffset
	 */
	public void paintDisplay(Graphics2D g, int xSize, int ySize, int xOffset, int yOffset);
	
	/**
	 * This method is called whenever the user presses 'A', or continues a conversation or action in 
	 * some way.
	 */
	public void getNextText();


}
