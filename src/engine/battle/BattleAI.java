package engine.battle;

import java.util.Random;

import engine.gridobject.person.Enemy;

public class BattleAI {
	public Enemy myEnemy;
	Random myRandom = new Random();
	public BattleAI(Enemy enemy){
		myEnemy=enemy;
	}
	public Weapon chooseWeapon(){
		int weaponChoice = myRandom.nextInt(myEnemy.getWeaponList().size());
		return myEnemy.getWeaponList().get(weaponChoice);
	}
	
	public Attack chooseAttack(Weapon weapon){
		int attackChoice = myRandom.nextInt(weapon.getAttackList().size());
		return weapon.getAttackList().get(attackChoice);
	}
}
