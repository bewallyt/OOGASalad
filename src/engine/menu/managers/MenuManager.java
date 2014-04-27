package engine.menu.managers;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;

import util.Reflection;
import engine.dialogue.InteractionBox;
import engine.gridobject.GridObject;
import engine.gridobject.person.Player;
import engine.images.ScaledImage;
import engine.menu.MenuInteractionMatrix;
import engine.menu.nodes.MenuNode;

public class MenuManager implements InteractionBox {

	private MenuInteractionMatrix mySelections;
	private String[] names;
	private Player myPlayer;

	public MenuManager(Player p, String[] menuItems) {
		names = menuItems;
		mySelections = new MenuInteractionMatrix(1, 5);
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

	public void paintDisplay(Graphics2D g2d, int xSize, int ySize, int width,
			int height) {

		paintMenu(g2d, height, width);

		drawSelector(g2d, xSize, ySize, width, height, 48);
	}

	public void createMenuNodes() {
		// Creates Menu Nodes via Reflection
		if (!(this instanceof WeaponManager)) {
			for (int i = 0; i < names.length; i++) {
				mySelections.setNode((MenuNode) Reflection.createInstance(
						"engine.menu.nodes." + names[i] + "Node", myPlayer,
						this), 0, i);
			}
		}
	}

	@Override
	public void getNextText() {

	}

	protected void drawSelector(Graphics2D g2d, int xSize, int ySize,
			int width, int height, int scale) {

		int[] selectedOptionLoc = mySelections.getSelectedNodeLocation();
		Image img = new ScaledImage(170, 55, "ImageFiles/redrectangle.png")
				.scaleImage();
		g2d.drawImage(img, width - 170, scale * selectedOptionLoc[1], null);

	}

	protected void paintMenu(Graphics g2d, int height, int width) {
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
		Image img = new ScaledImage(170, height, "ImageFiles/startmenu.png")
				.scaleImage();
		g2d.drawImage(img, width - 170, 0, null);
		g2d.setColor(Color.black);

	}

}
