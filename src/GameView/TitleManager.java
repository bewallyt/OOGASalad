package GameView;

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
import engine.state.GameSelectState;

public class TitleManager implements InteractionBox {

	private int colorMod = 0;
	public static boolean startPressed = false;
	private String displayString;
	private Player myPlayer;
	private boolean isGameLoaded = false;

	public TitleManager(Player p) {
		myPlayer = p;
	}

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

		if (startPressed == false) {
			g2d.setColor(changeColor());
			g2d.drawString("Press Start", 178, 238);
		}

		else {

			Image boxImg = new ScaledImage(width, 150, "ImageFiles/textbox.png")
					.scaleImage();
			g2d.drawImage(boxImg, 0, height + 70, null);

			if (displayString != GameSelectState.LOAD_FINISHED) {
				g2d.drawString("Load from (w/o extension):", 20, height + 115);

				updateText();
				if (displayString != null) {
					g2d.drawString(displayString, 20, height + 150);
				}
			} else {
				g2d.drawString(displayString, 20, height + 115);
			}

		}
	}
	
	public void updateText() {
		displayString = myPlayer.getState().getDisplayString();
	}


	public Color changeColor() {
		colorMod = colorMod % 60;
		Color startColor;
		if (colorMod < 30) {
			startColor = Color.black;
		} else {
			startColor = Color.white;
		}
		colorMod++;
		return startColor;
	}

	public void toggleStartPressed() {
		if(startPressed){
			startPressed = false;
		}
		else{
			startPressed = true;
		}
	}
	
	
	public void toggleIsGameLoaded(){
		if(isGameLoaded){
			isGameLoaded = false;	
		}
		else{ 
			isGameLoaded = true;
		}
	}
	
	public boolean getIsGameLoaded(){
		return isGameLoaded;
	}
	


	@Override
	public void getNextText() {
		// TODO Auto-generated method stub

	}

}