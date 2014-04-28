package engine.battle;

import java.util.Random;

import engine.gridobject.person.Enemy;
import engine.item.Weapon;

public class BattleAI {
	public Enemy myEnemy;
	Random myRandom = new Random();
	public BattleAI(Enemy enemy){
		myEnemy=enemy;
	}
	
	/**
	 * Choose a random weapon.
	 *
	 * @return the weapon
	 */
	public Weapon chooseWeapon(){
		int weaponChoice = myRandom.nextInt(myEnemy.getWeaponList().size());
		return myEnemy.getWeaponList().get(weaponChoice);
	}
	
	/**
	 * Choose a random attack.
	 *
	 * @param weapon the weapon
	 * @return the attack
	 */
	public Attack chooseAttack(Weapon weapon){
		int attackChoice = myRandom.nextInt(weapon.getAttackList().size());
		return weapon.getAttackList().get(attackChoice);
	}
}
