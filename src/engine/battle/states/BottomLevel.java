package engine.battle.states;

import engine.battle.Attack;
import engine.battle.BattleAI;
import engine.battle.BattleCalculator;
import engine.battle.BattleExecutable;
import engine.battle.BattleManager;
import engine.battle.Run;
import engine.gridobject.person.Person;
import engine.gridobject.person.Player;
import engine.item.Item;
import engine.item.Weapon;

public class BottomLevel implements BattleState {

//	private BattleExecutable myExecutable;
//	private Player myPlayer;
//	private BattleCalculator myBattleCalculate;
//	private BattleAI myBattleAI;
//	public BottomLevel(BattleExecutable executable, Player player,BattleCalculator battleCalculate){
//		
//		myExecutable = executable;
//		myPlayer=player;
//	}
	@Override
	public void doState(BattleManager manager) {
		BattleExecutable executable = manager.getCurrentBattleExecutorNode().getExecutor();
		if(executable instanceof Weapon){
			manager.getPlayer().setCurrentWeapon((Weapon) executable);
			manager.setCurrentTextToBeDisplayed("CurrentWeapon: " + executable.toString());
			manager.setCurrentState("BackToTop");
		}
		else if(executable instanceof Attack){
			BattleCalculator battleCalculate=new BattleCalculator(manager.getPlayer(), manager.getEnemy());
			Person currentAttacker=battleCalculate.getAttackersInOrder((Attack) executable)[0];
			Person currentVictim=battleCalculate.getAttackersInOrder((Attack) executable)[1];
			manager.setCurrentState("FirstAttackHappened");
			manager.setCurrentTextToBeDisplayed(currentAttacker.getCurrentWeapon().toString() + " used " + currentAttacker.getCurrentAttack().toString()
					+ "\n" + currentAttacker.getCurrentAttack().getEffectMessage());
			battleCalculate.attack(currentAttacker,currentVictim,currentAttacker.getCurrentWeapon(),currentAttacker.getCurrentAttack());
			if(currentAttacker instanceof Player)manager.setPlayerBattleImage(manager.getPlayer().getCurrentWeapon().getImage());
			else{manager.setEnemyBattleImage(manager.getEnemy().getCurrentWeapon().getImage());}
			checkIfDead(manager, battleCalculate);
			
		}
		else if(executable instanceof Item){
			((Item) executable).useItem();
			manager.setCurrentState("BackToTop");
			manager.setCurrentTextToBeDisplayed("Used " + ((Item) executable).toString());
		}
		else if(executable instanceof Run){
			manager.setCurrentState("Ran");
			manager.setCurrentTextToBeDisplayed("Got Away Safely");
		}
	}

private void checkIfDead(BattleManager manager, BattleCalculator battleCalculate) {
	if(battleCalculate.enemyIsDead())
		manager.setCurrentState("EnemyDead");
	if(battleCalculate.playerIsDead())
		manager.setCurrentState("PlayerDead");
}
	
	

}
