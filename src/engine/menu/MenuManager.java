package engine.menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;

import util.Reflection;
import engine.dialogue.InteractionBox;
import engine.gridobject.GridObject;
import engine.gridobject.person.Player;
import engine.images.ScaledImage;

public class MenuManager implements InteractionBox {

	private InteractionMatrix7x1 mySelections;
	private String[] names;
	private Player myPlayer;

	public MenuManager(Player p) {
		mySelections = new InteractionMatrix7x1();
		myPlayer = p;
	}

	public void moveCursorUp() {
		mySelections.moveUp();
	}

	public void moveCursorDown() {
		mySelections.moveDown();
	}

	public void select() {
		((MenuNode) mySelections.getCurrentNode()).doAction();
	}
	
	public void setNames() {
		names = new String[] { "Pokedex", "Pokemon", "Bag",
				"Name", "Save", "Options", "Exit" };
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

		g2d.setColor(Color.white);
		Image img = new ScaledImage(200, height + 50, "startmenu.png")
				.scaleImage();
		g2d.drawImage(img, width - 200, 0, null);
		g2d.setColor(Color.black);

		drawSelector(g2d, xSize, ySize, width, height);
	}

	public void createMenuNodes() {
		// Creates Menu Nodes via Reflection
		setNames();

		for (int i = 0; i < names.length; i++) {
			mySelections.setNode(
					(MenuNode) Reflection.createInstance("engine.menu."+ names[i] + "Node", myPlayer, this), 0, i);
		}
	}
	
	public void createManagers() {

		// Creates Menu Nodes via Reflection
		setNames();

		for (int i = 0; i < names.length; i++) {
			mySelections.setManager(
					(InteractionBox) Reflection.createInstance("engine.menu."+ names[i] +"Manager"), i);
		}
	}
	
	

	@Override
	public void getNextText() {

	}

	private void drawSelector(Graphics2D g2d, int xSize, int ySize, int width,
			int height) {

		int[] selectedOptionLoc = mySelections.getSelectedNodeLocation();
		Image img = new ScaledImage(200, 45, "redrectangle.png").scaleImage();
		g2d.drawImage(img, width - 200, 7 + 40 * selectedOptionLoc[1], null);

	}
}
