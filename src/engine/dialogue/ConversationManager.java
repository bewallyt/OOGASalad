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

/**
 * A class that handles the flow of a conversation, dealing with who is responding and what to do in the
 * backend in order to provide the correct information to print to the screen.
 *
 */
public class ConversationManager extends AbstractManager implements InteractionBox {

	private NPCResponseNode currentResponseNode;
	private UserQueryNode currentUserQueryNode;
	private String textToBeDisplayed;
	private int widthOfText;
	private Player myPlayer;
	private GridObject myNPC;
	private static boolean responding = true;
	private int selectedOptionX;
	private int selectedOptionY;
	private static final int X_OFFSET = 5/10;
	private static final int Y_OFFSET = 3/10;
	private static final int SYMBOL_RADIUS = 10;
	private ConversationState myCurrentState;

	public ConversationManager(Player p, GridObject n, NPCResponseNode nrNode) {
		super();
		currentResponseNode = nrNode;
		textToBeDisplayed = currentResponseNode.getDialogue();
		myPlayer = p;
		myPlayer.addItem(currentResponseNode.getItem());
		myNPC = n;
		responding = false;
		myCurrentState = new ListeningState();
	}

	@Override
	public void getNextText() {
		myCurrentState.doState(this);

	}


	public Player getPlayer() {
		return myPlayer;
	}

	@Override
	public void setCurrentNode() {
		currentUserQueryNode = (UserQueryNode) getMatrix().getCurrentNode();
	}
	
	public void setCurrentNPCResponseNode(NPCResponseNode n) {
		currentResponseNode = n;
	}
	
	public NPCResponseNode getCurrentNPCResponseNode() {
		return currentResponseNode;
	}
	
	public UserQueryNode getCurrentUserQueryNode() {
		return currentUserQueryNode;
	}

	@Override
	public boolean isResponding() {
		return responding;
	}
	
	public void setResponding(boolean b) {
		responding = b;
	}

	@Override
	public String getTextToBeDisplayed() {
		return textToBeDisplayed;
	}
	
	public void setTextToBeDisplayed(String text) {
		textToBeDisplayed = text;
	}

	public void setCurrentState(ConversationState state) {
		myCurrentState = state;
	}
	
	public void setCurrentUserQueryNode(UserQueryNode currentNode) {
		currentUserQueryNode =  currentNode;
	}


}
