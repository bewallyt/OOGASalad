package engine.menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.InputStream;

import engine.dialogue.InteractionBox;
import engine.dialogue.InteractionMatrix;
import engine.dialogue.InteractionMatrix2x2;
import engine.gridobject.GridObject;
import engine.images.ScaledImage;
import engine.state.AbstractState;

public class MenuManager implements InteractionBox {

	private AbstractState myState;
	private boolean menuToggler = false;
	private InteractionMatrix6x1 mySelections;
	private int mySelectedNodeY = 0;


	public MenuManager() {
		mySelections = new InteractionMatrix6x1();
	}

	public void setState(AbstractState state) {
		myState = state;
	}

	public void keyPressed(KeyEvent e) {
		myState.keyPressed(e);
	}

	public void keyReleased(KeyEvent e) {
		myState.keyReleased(e);
	}

	public void moveCursorUp() {
		mySelections.moveUp();
		mySelectedNodeY = mySelections.getSelectedNodeLocation()[1];
		System.out.println("mySelectedNodeY: " + mySelectedNodeY);
		System.out.println(mySelections.getSelectedNodeLocation()[1]);
	}

	public void moveCursorDown() {
		mySelections.moveDown();
		mySelectedNodeY = mySelections.getSelectedNodeLocation()[1];
		System.out.println(mySelections.getSelectedNodeLocation()[1]);
	}

	public void select() {

	}

	public void toggleMenu() {
		menuToggler = !menuToggler;
	}

	public boolean getMenuToggler() {
		return menuToggler;
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

		MenuNode pokedexNode = new MenuNode();
		MenuNode pokemonNode = new MenuNode();
		MenuNode bagNode = new MenuNode();
		MenuNode saveNode = new MenuNode();
		MenuNode optionsNode = new MenuNode();
		MenuNode exitNode = new MenuNode();

		mySelections.setNode(pokedexNode, 0, 0);
		mySelections.setNode(pokemonNode, 1, 0);
		mySelections.setNode(bagNode, 2, 0);
		mySelections.setNode(saveNode, 3, 0);
		mySelections.setNode(optionsNode, 4, 0);
		mySelections.setNode(exitNode, 5, 0);

	}

	@Override
	public void getNextText() {
		// TODO Auto-generated method stub

	}

	private void drawSelector(Graphics2D g2d, int xSize, int ySize, int width,
			int height) {

		int[] selectedOptionLoc = mySelections.getSelectedNodeLocation();
		Image img = new ScaledImage(200, 45, "redrectangle.png").scaleImage();
		g2d.drawImage(img, width - 200, 7 + 40 * selectedOptionLoc[1], null);

	}
}
