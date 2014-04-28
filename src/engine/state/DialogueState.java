package engine.state;

import java.awt.event.KeyEvent;
import util.Constants;
import java.util.HashSet;
import java.util.Set;

import engine.Control;
import engine.dialogue.AbstractManager;
import engine.dialogue.TransparentDisplayer;

public class DialogueState extends AbstractState {

	AbstractManager myConversationManager;
	Set<Integer> pressedKeys = new HashSet<Integer>();


	public DialogueState(AbstractManager cm) {
		super();
		myConversationManager = cm;
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
	//	if (pressedKeys.size() == 0) {
			if (e.getKeyCode() == Constants.UP){
				myConversationManager.moveUp();
			}
			if (e.getKeyCode() == Constants.DOWN){
				myConversationManager.moveDown();
			}
			if (e.getKeyCode() == Constants.RIGHT){
				myConversationManager.moveRight();

			}
			if (e.getKeyCode() == Constants.LEFT){
				myConversationManager.moveLeft();
			}		
			if (e.getKeyCode() == Constants.A) {
				myConversationManager.getNextText();
				pressedKeys.add(e.getKeyCode());
			}
			
			if (e.getKeyCode() == Constants.ESC || e.getKeyCode() == Constants.B) {
				myConversationManager.getPlayer().setState(new WalkAroundState(myConversationManager.getPlayer()));
				myConversationManager.getPlayer().getDialogueDisplayControl().setInteractionBox(new TransparentDisplayer());
				//pressedKeys.add(e.getKeyCode());
			}
	//	}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == Constants.A) {
			myConversationManager.getPlayer().setAClick(false);
		//	pressedKeys.remove(e.getKeyCode());
		}
		
	}

}
