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
			
		//	updateAttackList();
			manager.setCurrentTextToBeDisplayed("CurrentWeapon: " + executable.toString());
			manager.setCurrentState("BackToTop");
		}
		else if(executable instanceof Attack){
			BattleCalculator battleCalculate=new BattleCalculator(manager.getPlayer(), manager.getEnemy());
			BattleAI battleAI = new BattleAI(manager.getEnemy());
			Weapon enemyWeapon = battleAI.chooseWeapon();
			manager.getEnemy().setCurrentWeapon(enemyWeapon);
			manager.getEnemy().setCurrentAttack(battleAI.chooseAttack(enemyWeapon));
			manager.getPlayer().setCurrentAttack((Attack) executable);

			Person currentAttacker = battleCalculate.attackFirst(manager.getPlayer(), manager.getPlayer().getCurrentWeapon(), 
					(Attack) executable, manager.getEnemy(), enemyWeapon, manager.getEnemy().getCurrentAttack())[0];
			Person currentVictim = battleCalculate.attackFirst(manager.getPlayer(), manager.getPlayer().getCurrentWeapon(), 
					(Attack) executable, manager.getEnemy(), enemyWeapon, manager.getEnemy().getCurrentAttack())[1];
			
			manager.setCurrentState("FirstAttackHappened");
			manager.setCurrentTextToBeDisplayed(currentAttacker.getCurrentWeapon().toString() + " used " + currentAttacker.getCurrentAttack().toString()
					+ "\n" + currentAttacker.getCurrentAttack().getEffectMessage());
			battleCalculate.attack(currentAttacker,currentVictim,currentAttacker.getCurrentWeapon(),currentAttacker.getCurrentAttack());
			if(currentAttacker instanceof Player)manager.setPlayerBattleImage(manager.getPlayer().getCurrentWeapon().getImage());
			else{manager.setEnemyBattleImage(manager.getEnemy().getCurrentWeapon().getImage());}
			if(battleCalculate.enemyIsDead())
				manager.setCurrentState("EnemyDead");
			if(battleCalculate.playerIsDead())
				manager.setCurrentState("PlayerDead");
			
		}
		else if(executable instanceof Item){
			((Item) executable).useItem();
			manager.setCurrentState("BackToTop");
			
			manager.setCurrentTextToBeDisplayed("Used " + ((Item) executable).toString());
		}
		else if(executable instanceof Run){
			//ran=true;
			manager.setCurrentState("Ran");
			manager.setCurrentTextToBeDisplayed("Got Away Safely");
		}
	}
	
	

}
