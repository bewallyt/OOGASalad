package engine.battle.states;

import engine.battle.Attack;
import engine.battle.BattleAI;
import engine.battle.BattleCalculator;
import engine.battle.BattleExecutable;
import engine.battle.BattleManager;
import engine.battle.Run;
import engine.battle.Weapon;
import engine.gridobject.person.Player;
import engine.item.Item;

public class BottomLevel implements BattleState {

	private BattleExecutable myExecutable;
	private Player myPlayer;
	private BattleCalculator myBattleCalculate;
	private BattleAI myBattleAI;
	public BottomLevel(BattleExecutable executable, Player player,BattleCalculator battleCalculate){
		
		myExecutable = executable;
		myPlayer=player;
	}
	@Override
	public int doState() {
		if(myExecutable instanceof Weapon){
			myPlayer.setCurrentWeapon((Weapon) myExecutable);
			
		//	updateAttackList();
			//setCurrentTextToBeDisplayed();
			return BattleManager.WEAPONSELECTED;
		}
		else if(executable instanceof Attack){
			myBattleCalculate=new BattleCalculator(myPlayer, myEnemy);
			Weapon enemyWeapon = myBattleAI.chooseWeapon();
			myEnemy.setCurrentWeapon(enemyWeapon);
			myEnemy.setCurrentAttack(myBattleAI.chooseAttack(enemyWeapon));
			myPlayer.setCurrentAttack((Attack) executable);

			myCurrentAttacker = myBattleCalculate.attackFirst(myPlayer, myPlayer.getCurrentWeapon(), 
					(Attack) executable, myEnemy, enemyWeapon, myEnemy.getCurrentAttack())[0];
			myCurrentVictim = myBattleCalculate.attackFirst(myPlayer, myPlayer.getCurrentWeapon(), 
					(Attack) executable, myEnemy, enemyWeapon, myEnemy.getCurrentAttack())[1];
			
			myCurrentState=FIRSTATTACKHAPPENED;
			setCurrentTextToBeDisplayed();
			myBattleCalculate.attack(myCurrentAttacker,myCurrentVictim,myCurrentAttacker.getCurrentWeapon(),myCurrentAttacker.getCurrentAttack());
			if(myBattleCalculate.enemyIsDead())
				myCurrentState=ENEMYDEAD;
			if(myBattleCalculate.playerIsDead())
				myCurrentState=PLAYERDEAD;
			
		}
		else if(executable instanceof Item){
			((Item) executable).useItem();
			myCurrentState=ITEMUSED;
			itemUsedName=((Item) executable).toString();
			setCurrentTextToBeDisplayed();
		}
		else if(executable instanceof Run){
			//ran=true;
			myCurrentState=RAN;
			setCurrentTextToBeDisplayed();
		}
	}
	
	

}
