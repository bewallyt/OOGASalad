package GameView;

import engine.dialogue.MatrixNode;

public class GameChooserNode implements MatrixNode {
	
	String myGame;
	
	public GameChooserNode(String game){
		myGame = game;
	}

	
	public String getGame(){
		return myGame;
	}



}
