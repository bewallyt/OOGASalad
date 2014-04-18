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
import engine.dialogue.InteractionMatrix;
import engine.dialogue.InteractionMatrix2x2;
import engine.dialogue.UserQueryNode;
import engine.gridobject.GridObject;
import engine.state.AbstractState;

public class MenuManager implements InteractionBox {

	private AbstractState myState;
	private boolean menuToggler = false;
	private InteractionMatrix mySelections;
	private static final int SYMBOL_RADIUS = 10;
	
	public MenuManager(){
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

		g2d.setColor(Color.white);
		g2d.fill(new Rectangle((int) ((int) 0), ySize / 2 + 60, xSize,
				ySize / 4));
		g2d.setColor(Color.black);
	}

	@Override
	public void getNextText() {
		// TODO Auto-generated method stub

	}

	// Taken from Conversation Manager. Refactor going into the future.

	private void printResponses(Graphics2D g2d, int xSize, int ySize,
			int width, int height) {
		int xCornerLoc = xSize / 10;
		int yCornerLoc = ySize / 2 + 120;
		for (int i = 0; i < mySelections.getDimension()[0]; i++) {
			for (int j = 0; j < mySelections.getDimension()[1]; j++) {
				UserQueryNode qn = (UserQueryNode) mySelections.getNode(j, i);
				g2d.drawString(qn.getString(), (int) (xCornerLoc + j
						* (xSize * 5 / 10)), (int) (yCornerLoc + i
						* (height * 3 / 10)));
			}
		}

		int[] selectedOptionLoc = mySelections.getSelectedNodeLocation();
		g2d.fillOval((int) (xCornerLoc - 10 + selectedOptionLoc[0]
				* (xSize - 25) * 5 / 10)
				- SYMBOL_RADIUS, (int) (yCornerLoc + selectedOptionLoc[1]
				* (height - 15) * 3 / 10)
				- SYMBOL_RADIUS, SYMBOL_RADIUS, SYMBOL_RADIUS);
	}
}
