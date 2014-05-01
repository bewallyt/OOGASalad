package engine.dialogue;

public class RespondingState implements ConversationState{

	public RespondingState() {

	}

	public void doState(ConversationManager cm) {
		NPCResponseNode prevNode = cm.getCurrentNPCResponseNode();
		cm.setCurrentNPCResponseNode(cm.getCurrentUserQueryNode().getMyNPCResponseNode());
		if(cm.getCurrentNPCResponseNode().getItem()!=null){
			if(cm.getPlayer().getMoney()>=cm.getCurrentNPCResponseNode().getItem().getPrice()){
				cm.getPlayer().addItem(cm.getCurrentNPCResponseNode().getItem());
			}

			else{
				NPCResponseNode node= new NPCResponseNode("Sorry, you don't have enough money!", null);
//				node.(cm.getCurrentUserQueryNode());
				cm.setCurrentNPCResponseNode(node);

				int count=0;
				for(int i=0; i<cm.getMatrix().getDimension()[0]; i++){
					for(int j=0; j<cm.getMatrix().getDimension()[1]; j++){
						if(prevNode.getUserQueryNodes().size()>count)
							cm.getMatrix().setNode(prevNode.getUserQueryNodes().get(count), i, j);
						else{
							cm.getMatrix().setNode(null, i, j);
						}
						count++;
					}
				}
			}
		}
		cm.setTextToBeDisplayed(cm.getCurrentNPCResponseNode().getDialogue());
		cm.setCurrentState(new ListeningState());
		cm.setResponding(false);
	}

}
