package engine.battle;

import java.awt.Image;

public class Run implements BattleExecutable{

	public String toString(){
		return "Got away safely!";
	}

	@Override
	public Image getImage() {
		//none
		return null
	}
}
