package engine.battle.states;

import engine.battle.Attack;
import engine.battle.BattleAI;
import engine.battle.BattleCalculator;
import engine.battle.BattleExecutable;
import engine.battle.BattleManager;
import engine.gridobject.person.Person;
import engine.gridobject.person.Player;
import engine.item.Weapon;

public class FirstAttackHappened implements BattleState{

	@Override
	public void doState(BattleManager manager) {
		BattleExecutable executable = manager.getCurrentBattleExecutorNode().getExecutor();
		BattleCalculator battleCalculate=new BattleCalculator(manager.getPlayer(), manager.getEnemy());
		BattleAI battleAI = new BattleAI(manager.getEnemy());
		Weapon enemyWeapon = battleAI.chooseWeapon();
		manager.getEnemy().setCurrentWeapon(enemyWeapon);
		manager.getEnemy().setCurrentAttack(battleAI.chooseAttack(enemyWeapon));
		manager.getPlayer().setCurrentAttack((Attack) executable);

		Person currentAttacker = battleCalculate.attackFirst(manager.getPlayer(), manager.getPlayer().getCurrentWeapon(), 
				(Attack) executable, manager.getEnemy(), enemyWeapon, manager.getEnemy().getCurrentAttack())[1];
		Person currentVictim = battleCalculate.attackFirst(manager.getPlayer(), manager.getPlayer().getCurrentWeapon(), 
				(Attack) executable, manager.getEnemy(), enemyWeapon, manager.getEnemy().getCurrentAttack())[0];
		manager.setCurrentState("BackToTop");
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

}
