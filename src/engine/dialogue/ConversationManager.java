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
import engine.gridobject.person.Person;
import engine.gridobject.person.Player;
import engine.images.ScaledImage;
import engine.state.WalkAroundState;

public class ConversationManager extends AbstractManager implements InteractionBox {

	private NPCResponseNode currentResponseNode;
	private UserQueryNode currentUserQueryNode;
	private String textToBeDisplayed;
	private int widthOfText;
	private Player myPlayer;
	private GridObject myNPC;
	private static boolean RESPONDING = true;
	private int selectedOptionX;
	private int selectedOptionY;
	private static final int X_OFFSET = 5/10;
	private static final int Y_OFFSET = 3/10;
	private static final int SYMBOL_RADIUS = 10;
	//private DialogueListeingState Listening = new DialogueListeningState();

	public ConversationManager(Player p, GridObject n, NPCResponseNode nrNode) {
		currentResponseNode = nrNode;
		textToBeDisplayed = currentResponseNode.getDialogue();
		myPlayer = p;
		myNPC = n;
		System.out.println(textToBeDisplayed);
		RESPONDING = false;
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
		}
	}

	private boolean createAvailableResponses(boolean nNodes) {
		int count = 0;
		if (currentResponseNode.getUserQueryNodes().isEmpty()) return false;
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				getMatrix().setNode(currentResponseNode.getUserQueryNodes().get(count), j, i);
				count++;
			}
		}	
		currentUserQueryNode = (UserQueryNode) getMatrix().getCurrentNode();
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

	@Override
	public void setCurrentNode() {
		currentUserQueryNode = (UserQueryNode) getMatrix().getCurrentNode();
	}

	@Override
	public boolean isResponding() {
		return RESPONDING;
	}

	@Override
	public String getTextToBeDisplayed() {
		return textToBeDisplayed;
	}




}
