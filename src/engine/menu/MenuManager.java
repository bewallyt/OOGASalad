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
import engine.gridobject.GridObject;
import engine.state.AbstractState;

public class MenuManager implements InteractionBox {

	private AbstractState myState;
	private boolean menuToggler = false;
	private InteractionMatrix mySelections;
	private static final int SYMBOL_RADIUS = 10;
	
	private int currentPos;
	private int prevPos;
	
	public MenuManager(){
		mySelections = new InteractionMatrix6x1();
		prevPos = 0;
		currentPos = 0;

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
		mySelections.moveUp();
		currentPos++;
		//System.out.println("Current Position" + currentPos);
		//System.out.println(mySelections.getSelectedNodeLocation()[1]);

	}
	

	public void moveCursorDown() {
		// TODO Auto-generated method stub
		mySelections.moveDown();
		currentPos--;
		//System.out.println("Current Position" + currentPos);
		//System.out.println(mySelections.getSelectedNodeLocation()[1]);

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


	public boolean getMenuToggler() {
		return menuToggler;
	}

	public void paintDisplay(Graphics2D g2d, int xSize, int ySize, int width, int height) {
		

		InputStream is = GridObject.class.getResourceAsStream("PokemonGB.ttf");
		Font font = null;

		//System.out.println("Current Position: " + currentPos);
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
		g2d.fill(new Rectangle(xSize -190, ySize/2 - 240, width/4 + 50 , height + 60));
		g2d.setColor(Color.black);
		
//		if(currentPos != prevPos){
//			System.out.println("dsfsd");
//			printResponses(g2d, xSize, ySize, width, height);
//			prevPos = currentPos;
//		}
		
		System.out.println("test" + currentPos);
	}
	
	public void createMenuNodes() {
		
		MenuNode pokedexNode = new MenuNode();
		MenuNode pokemonNode = new MenuNode();
		MenuNode bagNode     = new MenuNode();
		MenuNode saveNode    = new MenuNode();
		MenuNode optionsNode = new MenuNode();
		MenuNode exitNode    = new MenuNode();
		
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
	
	public void testIncrement(){
		System.out.println("test" + currentPos);
	}

	// Taken from Conversation Manager. Refactor going into the future.

	private void printResponses(Graphics2D g2d, int xSize, int ySize,
			int width, int height) {

		
		int xCornerLoc = xSize / 10;
		int yCornerLoc = ySize / 2 + 120;
		for (int i = 0; i < mySelections.getDimension()[0]; i++) {
			for (int j = 0; j < mySelections.getDimension()[1]; j++) {
				MenuNode qn = (MenuNode) mySelections.getNode(j, i);
//				g2d.drawString("blah", (int) (xCornerLoc + j
//						* (xSize * 5 / 10)), (int) (yCornerLoc + i
//						* (height * 3 / 10)));
			}
		}

		int[] selectedOptionLoc = mySelections.getSelectedNodeLocation();
//		System.out.println("selected node location" +  mySelections.getSelectedNodeLocation()[1]);
		g2d.fillOval((int) (xCornerLoc - 10 + mySelections.getSelectedNodeLocation()[0]
				* (xSize - 25) * 5 / 10)
				- SYMBOL_RADIUS, (int) (yCornerLoc + mySelections.getSelectedNodeLocation()[1]
				* (height - 15) * 3 / 10)
				- SYMBOL_RADIUS, SYMBOL_RADIUS, SYMBOL_RADIUS);
	}
}
