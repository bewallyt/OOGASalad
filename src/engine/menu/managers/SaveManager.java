package engine.menu.managers;

import java.awt.Graphics2D;
import java.awt.Image;
import engine.dialogue.InteractionBox;
import engine.gridobject.person.Player;
import engine.images.ScaledImage;

public class SaveManager extends MenuManager implements InteractionBox {

	private Player myPlayer;
	private String displayString;
	private final static String SAVE_FINISHED = "Save Complete!";

	public SaveManager(Player p) {
		super(p, null);
		myPlayer = p;
	}

	@Override
	public void paintDisplay(Graphics2D g2d, int xSize, int ySize, int width,
			int height) {

		paintMenu(g2d, height, width);
		
		drawSelector(g2d, xSize, ySize, width, height);
		Image boxImg = new ScaledImage(width, 150, "ImageFiles/textbox.png").scaleImage();
		g2d.drawImage(boxImg, 0, height + 70, null);

		if (displayString != SAVE_FINISHED) {
			g2d.drawString("Save as (w/o extension):", 20, height + 115);

			updateText();
			if (displayString != null) {
				g2d.drawString(displayString, 20, height + 150);
			}
		} else {
			g2d.drawString(displayString, 20, height + 115);
		}
	}

	@Override
	public void getNextText() {
		// TODO Auto-generated method stub

	}

	private void drawSelector(Graphics2D g2d, int xSize, int ySize, int width,
			int height) {
		
		Image img = new ScaledImage(170, 55, "ImageFiles/redrectangle.png").scaleImage();
		g2d.drawImage(img, width - 170, 48 * 3, null);

	}

	private void updateText() {
		displayString = myPlayer.getState().getDisplayString();
	}

}
