package engine.menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.InputStream;

import engine.dialogue.InteractionBox;
import engine.gridobject.GridObject;
import engine.state.AbstractState;

public class MenuManager implements InteractionBox {

	private AbstractState myState;
	private boolean menuToggler = false;

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
		// TODO Auto-generated method stub

	}

	public void select() {
		// TODO Auto-generated method stub

	}

	public void toggleMenu() {
		if (menuToggler == false) {
			menuToggler = true;
		} else {
			menuToggler = false;
		}

	}

	public void moveCursorLeft() {
		// TODO Auto-generated method stub

	}

	public void moveCursorRight() {
		// TODO Auto-generated method stub

	}

	public void moveCursorDown() {
		// TODO Auto-generated method stub

	}

	public boolean getMenuToggler() {
		return menuToggler;
	}

	@Override
	public void paintDisplay(Graphics2D g2d, int xSize, int ySize, int xOffset,
			int yOffset) {

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

		g2d.setColor(Color.green);
		g2d.fill(new Rectangle((int) ((int) 0), ySize / 2 + 60, xSize,
				ySize / 4));
		g2d.setColor(Color.black);
	}

	@Override
	public void getNextText() {
		// TODO Auto-generated method stub

	}
}
