package engine.menu.managers;

import java.awt.Graphics2D;

import engine.dialogue.InteractionBox;
import engine.gridobject.person.Player;

public class PokemonManager implements InteractionBox {
	
	private Player myPlayer;
	
	public PokemonManager(Player p){
		myPlayer = p;
	}

	@Override
	public void paintDisplay(Graphics2D g, int xSize, int ySize, int xOffset,
			int yOffset) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getNextText() {
		// TODO Auto-generated method stub
		
	}

}
