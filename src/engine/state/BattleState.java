package engine.state;

import java.awt.event.KeyEvent;

import engine.Control;
import engine.battle.BattleManager;
import util.Constants;

public class BattleState extends AbstractState {
	BattleManager myBattleManager;
	private boolean a=false;

	public BattleState(BattleManager bm){
		super();
		myBattleManager=bm;
	}
	@Override
	public void keyTyped(KeyEvent e) {

	}


	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == Constants.UP){
			myBattleManager.moveUp();
		}
		if (e.getKeyCode() == Constants.DOWN){
			myBattleManager.moveDown();
		}
		if (e.getKeyCode() == Constants.RIGHT){
			myBattleManager.moveRight();

		}
		if (e.getKeyCode() == Constants.LEFT){
			myBattleManager.moveLeft();
		}		
		if (e.getKeyCode() == Constants.A) {
			if(a==false){
				a=true;
				myBattleManager.getNextText();
			}
		}	
	}

	@Override
	public void keyReleased(KeyEvent e) {
		a=false;
		if (e.getKeyCode() == Constants.A)
			myBattleManager.getPlayer().setAClick(false);
		if (e.getKeyCode() == Constants.ESC)
			myBattleManager.backToTopOfBattle();
	}


}
