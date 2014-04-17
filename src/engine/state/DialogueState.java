package engine.state;

import java.awt.event.KeyEvent;

import engine.Control;
import engine.dialogue.ConversationManager;
import engine.gridobject.GridObject;

public class DialogueState extends AbstractState {

	ConversationManager myConversationManager;
	
	public DialogueState(ConversationManager cm) {
		super();
		myConversationManager = cm;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
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
		}	
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == Control.A)
			myConversationManager.getPlayer().setAClick(false);
	}

}
