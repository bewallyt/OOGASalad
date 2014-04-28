package engine.menu.managers;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;

import util.PokemonFont;
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

	public MenuManager(Player p, String[] menuItems, MenuInteractionMatrix mim) {
		names = menuItems;
		mySelections = mim;
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
		drawSelector(g2d, xSize, 0, 170, 55, 48, mySelections);
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

	protected void drawSelector(Graphics2D g2d, int xPos, int yPos, int width,
			int height, int scale, MenuInteractionMatrix myMatrix) {

		int[] selectedOptionLoc = myMatrix.getSelectedNodeLocation();
		Image img = new ScaledImage(width, height, "ImageFiles/redrectangle.png")
				.scaleImage();
		g2d.drawImage(img, xPos - 170, yPos + scale * selectedOptionLoc[1], null);

	}

	protected void paintMenu(Graphics g2d, int height, int width) {
		
		PokemonFont.setFont(g2d, 16f);
		
		Image img = new ScaledImage(170, height, "ImageFiles/startmenu.png")
				.scaleImage();
		g2d.drawImage(img, width - 170, 0, null);

	}

}
