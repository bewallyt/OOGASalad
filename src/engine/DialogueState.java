package engine;

import java.awt.event.KeyEvent;

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
			
		}
		if (e.getKeyCode() == Control.DOWN){
			
		}
		if (e.getKeyCode() == Control.RIGHT){
			
		}
		if (e.getKeyCode() == Control.LEFT){
			
		}		
		if (e.getKeyCode() == Control.A) {
			myConversationManager.getNextText();
		}	
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

}
