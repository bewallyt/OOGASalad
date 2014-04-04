package engine.gridobject.person;

import java.util.ArrayList;
import java.util.List;

import engine.Dialogue;

public class NPC extends RuleFollower {
	protected List<String> myDialogue;

	public NPC(String image, double speed, int numTilesWidth, int numTilesHeight) {
		super(image, speed, numTilesWidth, numTilesHeight);
		myDialogue=new ArrayList<String>();

	}

	public void addDialogue(String dialogue){
		myDialogue.add(dialogue);
	}
	public List<String> getDialogueList(){
		return myDialogue;
	}

	public void doNextDialogue(){
		System.out.println("hi");
		if(myDialogue.size()>0){
			new Dialogue("Dialogue.png", myDialogue.get(0));
			myDialogue.remove(0);
		}
	}





}


