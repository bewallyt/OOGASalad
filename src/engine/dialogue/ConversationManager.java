package engine.dialogue;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import engine.WalkAroundState;
import engine.gridobject.GridObject;
import engine.gridobject.person.NPC;
import engine.gridobject.person.Player;

public class ConversationManager implements InteractionBox {

	private NPCResponseNode currentResponseNode;
	private UserQueryNode currentUserQueryNode;
	private String textToBeDisplayed;
//	private UserQueryNode[][] myResponses;
	private InteractionMatrix myResponses;
	private int widthOfText;
	private Player myPlayer;
	private NPC myNPC;
	private static boolean RESPONDING = true;
	private int selectedOptionX;
	private int selectedOptionY;

	public ConversationManager(Player p, NPC n, NPCResponseNode nrNode) {
		currentResponseNode = nrNode;
		textToBeDisplayed = currentResponseNode.getDialogue();
		myPlayer = p;
		myNPC = n;
		System.out.println(textToBeDisplayed);
		//myResponses = new UserQueryNode[2][2];
		myResponses = new InteractionMatrix();
//		selectedOptionX = 0;
//		selectedOptionY = 0;
		RESPONDING = false;
	}

	@Override
	public void paintDisplay(Graphics2D g2d, int xSize, int ySize, int xOffset, int yOffset) {
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
		g2d.fill(new Rectangle((int) ((int) 0), ySize/2+60, xSize , ySize/4));
		g2d.setColor(Color.black);

		if (RESPONDING) {
			printResponses(g2d, myResponses);
		} else {
			g2d.drawString(textToBeDisplayed, (int) xSize/10, ySize/2+120);
		}
	}

	private void printResponses(Graphics2D g, InteractionMatrix myResponses) {
		
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
		if (currentResponseNode.getUserQueryNodes() == null) return false;
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
