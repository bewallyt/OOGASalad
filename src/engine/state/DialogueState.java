package engine.state;

import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;

import engine.Control;
import engine.dialogue.ConversationManager;
import engine.gridobject.GridObject;

public class DialogueState extends AbstractState {

	ConversationManager myConversationManager;
	Set<Integer> pressedKeys = new HashSet<Integer>();


	public DialogueState(ConversationManager cm) {
		super();
		myConversationManager = cm;
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
	//	if (pressedKeys.size() == 0) {
			if (e.getKeyCode() == Control.UP){
				myConversationManager.moveUp();
			}
			if (e.getKeyCode() == Control.DOWN){
				myConversationManager.moveDown();
			}
			if (e.getKeyCode() == Control.RIGHT){
				myConversationManager.moveRight();

			}
			if (e.getKeyCode() == Control.LEFT){
				myConversationManager.moveLeft();
			}		
			if (e.getKeyCode() == Control.A) {
				myConversationManager.getNextText();
				pressedKeys.add(e.getKeyCode());
			}
	//	}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == Control.A) {
			myConversationManager.getPlayer().setAClick(false);
		//	pressedKeys.remove(e.getKeyCode());
		}
		
	}

}
