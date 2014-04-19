package engine.dialogue;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import engine.gridobject.GridObject;
import engine.gridobject.person.NPC;
import engine.gridobject.person.Player;
import engine.images.ScaledImage;
import engine.state.WalkAroundState;

public class ConversationManager implements InteractionBox {

	private NPCResponseNode currentResponseNode;
	private UserQueryNode currentUserQueryNode;
	private String textToBeDisplayed;
	private InteractionMatrix2x2 myResponses;
	private int widthOfText;
	private Player myPlayer;
	private NPC myNPC;
	private static boolean RESPONDING = true;
	private int selectedOptionX;
	private int selectedOptionY;
	private static final int X_OFFSET = 5/10;
	private static final int Y_OFFSET = 3/10;
	private static final int SYMBOL_RADIUS = 10;


	public ConversationManager(Player p, NPC n, NPCResponseNode nrNode) {
		currentResponseNode = nrNode;
		textToBeDisplayed = currentResponseNode.getDialogue();
		myPlayer = p;
		myNPC = n;
		System.out.println(textToBeDisplayed);
		myResponses = new InteractionMatrix2x2();
		RESPONDING = false;
	}

	@Override
	public void paintDisplay(Graphics2D g2d, int xSize, int ySize, int width, int height) {
		InputStream is = GridObject.class.getResourceAsStream("PokemonGB.ttf");
		Font font=null;

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
		Image img = new ScaledImage(width, 150,"textbox.png").scaleImage();
		g2d.drawImage(img, 0, height + 70, null);
		//g2d.fill(new Rectangle((int) ((int) 0), ySize/2+60, width , height));
		g2d.setColor(Color.black);

		if (RESPONDING) {
			printResponses(g2d, myResponses, xSize, ySize, width, height);
		} else {
			g2d.drawString(textToBeDisplayed, (int) xSize/10, ySize/2+120);
		}	
	}

	private void printResponses(Graphics2D g2d, InteractionMatrix myResponses2, int xSize, int ySize, 
								int width, int height) {
		int xCornerLoc = xSize/10;
		int yCornerLoc = ySize/2 + 120;

		for (int i = 0; i < myResponses.getDimension()[0]; i++) {
			for (int j = 0; j < myResponses.getDimension()[1]; j++) {
				UserQueryNode qn = (UserQueryNode) myResponses.getNode(j, i);
				g2d.drawString(qn.getString(), (int) (xCornerLoc + j*(xSize*5/10)), (int)(yCornerLoc + i*(height*3/10)));
			}
		}
		
		int[] selectedOptionLoc = myResponses.getSelectedNodeLocation();
		g2d.fillOval((int) (xCornerLoc-10 + selectedOptionLoc[0]*(xSize-25)*5/10) - SYMBOL_RADIUS, 
					(int) (yCornerLoc + selectedOptionLoc[1]*(height-15)*3/10) - SYMBOL_RADIUS, SYMBOL_RADIUS, SYMBOL_RADIUS);
	}


	@Override
	public void getNextText() {
		boolean itemNodePresent = false;
		boolean newNodes = false;
		if (RESPONDING) {
			currentResponseNode = currentUserQueryNode.getMyNPCResponseNode();
			textToBeDisplayed = currentResponseNode.getDialogue();
			System.out.println(textToBeDisplayed);
			RESPONDING = false;
			newNodes = true;
		} else if (hasItemNode()) {
			for (UserQueryNode node : currentResponseNode.getUserQueryNodes()) {
				if (node != null && node.hasItem()) {
					currentResponseNode = node.getMyNPCResponseNode();
					textToBeDisplayed = currentResponseNode.getDialogue();
					System.out.println(textToBeDisplayed);
					itemNodePresent = true;
					newNodes = true;
					break;
				}
			}
		} else {
			newNodes = createAvailableResponses(newNodes);
		}

		// we exit the conversation here
		if (!newNodes) {
			myPlayer.setState(new WalkAroundState(myPlayer));
			myNPC.getDialogueDisplayControl().setInteractionBox(new TransparentDisplayer());
			System.out.println("Walk Around World");
		}
	}

	private boolean createAvailableResponses(boolean nNodes) {
		int count = 0;
		if (currentResponseNode.getUserQueryNodes().isEmpty()) return false;
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				myResponses.setNode(currentResponseNode.getUserQueryNodes().get(count), j, i);
				count++;
			}
		}	
		currentUserQueryNode = (UserQueryNode) myResponses.getCurrentNode();
		RESPONDING = true;
		return true;
	}

	private boolean hasItemNode() {
		for (UserQueryNode node : currentResponseNode.getUserQueryNodes()) {
			if (node != null && node.hasItem()) {
				return true;
			}
		}
		return false;
	}

	public Player getPlayer() {
		return myPlayer;
	}

	public void moveUp() {
		if (RESPONDING) {
			myResponses.moveUp();
			currentUserQueryNode = (UserQueryNode) myResponses.getCurrentNode();
		}

	}

	public void moveDown() {
		if (RESPONDING) {
			myResponses.moveDown();
			currentUserQueryNode = (UserQueryNode) myResponses.getCurrentNode();
		}
	}

	public void moveLeft() {
		if (RESPONDING) {
			myResponses.moveLeft(); 
			currentUserQueryNode = (UserQueryNode) myResponses.getCurrentNode();
		}
	}

	public void moveRight() {
		if (RESPONDING) {
			myResponses.moveRight();
			currentUserQueryNode = (UserQueryNode) myResponses.getCurrentNode();
		}
	}


}
