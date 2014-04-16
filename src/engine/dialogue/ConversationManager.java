package engine.dialogue;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
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
	private UserQueryNode[][] myResponses;
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
		myResponses = new UserQueryNode[2][2];
		selectedOptionX = 0;
		selectedOptionY = 0;
		RESPONDING = false;
	}

	@Override
	public void paintDisplay(Graphics2D g, int xSize, int ySize, int xOffset, int yOffset) {
		InputStream is = GridObject.class.getResourceAsStream("PokemonGB.ttf");
		Font font=null;
		try {
			try {
				font = Font.createFont(Font.TRUETYPE_FONT, is);
			} catch (FontFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Font sizedFont = font.deriveFont(16f);
			g.setFont(sizedFont);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (RESPONDING) {
			printResponses(g, myResponses);
		} else {
			g.drawString(textToBeDisplayed, (int) (xSize*.1), (int) (ySize-ySize/4));
		}
	}

	private void printResponses(Graphics2D g, UserQueryNode[][] myResponses) {
		for (int i = 0; i < myResponses.length; i++) {
			for (int j = 0; j < myResponses[i].length; j++) {
				System.out.println(myResponses[i][j].getString());
			}
		}
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

		
		if (!newNodes) {
			myPlayer.setState(new WalkAroundState(myPlayer));
			System.out.println("Walk Around World");
		}
	}
	
	private boolean createAvailableResponses(boolean nNodes) {
		int count = 0;
		if (currentResponseNode.getUserQueryNodes() == null) return false;
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				myResponses[i][j] = currentResponseNode.getUserQueryNodes().get(count);
				count++;
			}
		}	
		currentUserQueryNode = myResponses[0][0];
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




}
