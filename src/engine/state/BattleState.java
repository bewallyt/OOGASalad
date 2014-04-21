package engine.state;

import java.awt.event.KeyEvent;

import engine.Control;
import engine.battle.BattleManager;

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
		if (e.getKeyCode() == Control.UP){
			myBattleManager.moveUp();
		}
		if (e.getKeyCode() == Control.DOWN){
			myBattleManager.moveDown();
		}
		if (e.getKeyCode() == Control.RIGHT){
			myBattleManager.moveRight();

		}
		if (e.getKeyCode() == Control.LEFT){
			myBattleManager.moveLeft();
		}		
		if (e.getKeyCode() == Control.A) {
			if(a==false){
				a=true;
				myBattleManager.getNextText();
			}
		}	
	}

	@Override
	public void keyReleased(KeyEvent e) {
		a=false;
		if (e.getKeyCode() == Control.A)
			myBattleManager.getPlayer().setAClick(false);
	}


}
