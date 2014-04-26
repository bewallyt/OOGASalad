package engine.menu.managers;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;

import engine.dialogue.InteractionBox;
import engine.gridobject.GridObject;
import engine.gridobject.person.Player;
import engine.images.ScaledImage;
import engine.state.LoadState;

public class OptionsManager implements InteractionBox {
	
	private Player myPlayer;
	private String displayString;
	
	public OptionsManager(Player p){
		myPlayer = p;
	}

	@Override
	public void paintDisplay(Graphics2D g2d, int xSize, int ySize, int width,
			int height) {

		InputStream is = GridObject.class.getResourceAsStream("PokemonGB.ttf");
		Font font = null;

		try {
			try {
				font = Font.createFont(Font.TRUETYPE_FONT, is);
			} catch (FontFormatException e) {
				e.printStackTrace();
			}
			Font sizedFont = font.deriveFont(16f);
			g2d.setFont(sizedFont);
		} catch (IOException e) {
			e.printStackTrace();
		}

		g2d.setColor(Color.white);
		Image menuImg = new ScaledImage(200, height + 50, "ImageFiles/startmenu.png")
				.scaleImage();
		g2d.drawImage(menuImg, width - 200, 0, null);
		g2d.setColor(Color.black);

		drawSelector(g2d, xSize, ySize, width, height);
		Image boxImg = new ScaledImage(width, 150, "ImageFiles/textbox.png").scaleImage();
		g2d.drawImage(boxImg, 0, height + 70, null);

		if (displayString != LoadState.LOAD_FINISHED) {
			g2d.drawString("Load from (w/o extension):", 20, height + 115);

			updateText();
			if (displayString != null) {
				g2d.drawString(displayString, 20, height + 150);
			}
		} else {
			g2d.drawString(displayString, 20, height + 115);
		}
	}
	
	private void drawSelector(Graphics2D g2d, int xSize, int ySize, int width,
			int height) {

		Image img = new ScaledImage(200, 45, "ImageFiles/redrectangle.png").scaleImage();
		g2d.drawImage(img, width - 200, 7 + 40 * 5, null);

	}

	public void updateText() {
		displayString = myPlayer.getState().getDisplayString();
	}

	@Override
	public void getNextText() {
		// TODO Auto-generated method stub
		
	}

}
