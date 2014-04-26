package engine.state;

import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;

import engine.Control;
import engine.dialogue.AbstractManager;

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
