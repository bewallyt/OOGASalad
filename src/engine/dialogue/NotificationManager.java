package engine.dialogue;

import engine.gridobject.GridObject;
import engine.gridobject.person.Player;
import engine.state.WalkAroundState;

/**
 * A manager that deals with just one pop up instance. Very similar to the ConversationManager, but the
 * <code>getNextText()</code> method immediately terminates the alert and returns the Player's state back
 * to <code>WalkAroundState</code>
 *
 */
public class NotificationManager extends AbstractManager implements InteractionBox {
	
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

	public NotificationManager(Player p, GridObject n, NPCResponseNode nrNode) {
		currentResponseNode = nrNode;
		textToBeDisplayed = currentResponseNode.getDialogue();
		myPlayer = p;
		myNPC = n;
		RESPONDING = false;
	}

	@Override
	public void getNextText() {
		myPlayer.setState(new WalkAroundState(myPlayer));
		myNPC.getDialogueDisplayControl().setInteractionBox(new TransparentDisplayer());
	}

	@Override
	public void setCurrentNode() {

	}

	@Override
	public boolean isResponding() {
		return false;
	}

	@Override
	public String getTextToBeDisplayed() {
		return textToBeDisplayed;
	}

	@Override
	public Player getPlayer() {
		return myPlayer;
	}

}
