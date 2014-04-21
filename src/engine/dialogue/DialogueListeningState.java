package engine.dialogue;

public class DialogueListeningState implements DialogueState{
	private final String myText;
	private DialogueState myNextState;
	public DialogueListeningState(String textToBeDisplayed){
		myText=textToBeDisplayed;
		myNextState=nextState;
	}
	
	public String getTextToBeDisplayed(){
		return myText;
	}
	public DialogueState getNextState(){
		return myNextState;
	}
	
	public void setNextState(DialogueState nextState){
		myNextState=nextState;
	}
}
