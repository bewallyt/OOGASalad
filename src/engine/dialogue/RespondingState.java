package engine.dialogue;

public class RespondingState implements ConversationState{
	
	public RespondingState() {
		
	}
	
	public void doState(ConversationManager cm) {
		cm.setCurrentNPCResponseNode(cm.getCurrentUserQueryNode().getMyNPCResponseNode());
		cm.getPlayer().addItem(cm.getCurrentNPCResponseNode().getItem());
		cm.setTextToBeDisplayed(cm.getCurrentNPCResponseNode().getDialogue());
		cm.setCurrentState(new ListeningState());
		cm.setResponding(false);
	}

}
