package engine.menu.managers;


import java.awt.Graphics2D;


import engine.dialogue.InteractionBox;
import engine.gridobject.person.Player;

public class WeaponManager extends MenuManager implements InteractionBox {
	
	private Player myPlayer;
	
	public WeaponManager(Player p){
		super(p, null);
		myPlayer = p;
	}

	@Override
	public void paintDisplay(Graphics2D g2d, int xSize, int ySize, int width,
			int height) {
	
		paintMenu(g2d, height, width);
		
		
		
	}

	@Override
	public void getNextText() {
		// TODO Auto-generated method stub
		
	}

}
