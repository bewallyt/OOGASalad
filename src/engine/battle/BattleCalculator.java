package engine.battle;

import java.util.Random;

import util.Constants;
import engine.gridobject.person.Enemy;
import engine.gridobject.person.Person;
import engine.gridobject.person.Player;
import engine.item.Weapon;

public class BattleCalculator {
	private Player myPlayer;
	private Enemy myEnemy;
	private BattleAI myBattleAI;
	private boolean dropWeapon = false;

	/**
	 * Instantiates a new battle calculator.
	 *
	 * @param player the player
	 * @param enemy the enemy
	 */
	public BattleCalculator(Player player, Enemy enemy){
		myPlayer=player;
		myBattleAI=new BattleAI(enemy);
		myEnemy = enemy;
	}

	/**
	 * Gets the attackers in order. 
	 *
	 * @param attack the attack
	 * @return the attackers in order. 0=first attacker 1=second attacker
	 */
	public Person[] getAttackersInOrder(Attack attack){
		BattleAI battleAI = new BattleAI(myEnemy);
		Weapon enemyWeapon = battleAI.chooseWeapon();
		myEnemy.setCurrentWeapon(enemyWeapon);
		myEnemy.setCurrentAttack(battleAI.chooseAttack(enemyWeapon));
		myPlayer.setCurrentAttack(attack);

		Person currentAttacker = attackFirst(myPlayer, myPlayer.getCurrentWeapon(), 
				attack, myEnemy, enemyWeapon, myEnemy.getCurrentAttack())[0];
		Person currentVictim = attackFirst(myPlayer, myPlayer.getCurrentWeapon(), 
				attack, myEnemy, enemyWeapon, myEnemy.getCurrentAttack())[1];
		return new Person[] {currentAttacker, currentVictim};
	}
	private Person[] attackFirst(Person person1, Weapon weapon1, Attack attack1, Person person2, Weapon weapon2, Attack attack2){
		if(calcSpeed(person1,weapon1,attack1)>=calcSpeed(person2,weapon2,attack2))
			return new Person[] {person1,person2};
		return new Person[] {person2,person1};
	}

	private int calcSpeed(Person person, Weapon weapon, Attack attack){
		return (person.getStatsMap().get(Constants.SPEED).getValue()+weapon.getSpeed().getValue()+attack.getSpeed().getValue());
	}

	/**
	 * Attack.
	 *
	 * @param attacker the attacker
	 * @param victim the victim
	 * @param weapon the weapon
	 * @param attack the attack
	 */
	public void attack(Person attacker, Person victim, Weapon weapon, Attack attack){
		int level = attacker.getStatsMap().get(Constants.LEVEL).getValue();
		int playerDamage = attacker.getStatsMap().get(Constants.DAMAGE).getValue();
		int weaponDamage = weapon.getDamage().getValue();
		int attackDamage = attack.getDamage().getValue();
		int defense = victim.getStatsMap().get(Constants.DEFENSE).getValue();
		int random = 30 + (int)(Math.random() * ((25 - 30) + 1));
		int total = (((((2*level+2)*playerDamage*(weaponDamage+attackDamage)/defense))+2)/random);
		if(attackDamage!=0)
			victim.getStatsMap().get(Constants.HEALTH).changeValue(-total);
		if(attack.getEffect()!=null){
			attack.getEffect().doEffect(attacker,victim);	
		}
		System.out.println(myPlayer.getStatsMap().get(Constants.HEALTH).getValue());

	}
	private boolean checkDropWeaponStatus(){
		boolean dropWeapon = false;
		Random rand = new Random();

		int randValue = rand.nextInt(10);
		if (randValue < 2) {

			dropWeapon = true;
		}
		return dropWeapon;
	}

	/**
	 * Enemy is dead.
	 *
	 * @return true, if enemy is dead
	 */
	public boolean enemyIsDead(){
		if(myEnemy.getStatsMap().get(Constants.HEALTH).getValue()<=0){

			dropWeapon = checkDropWeaponStatus();
			if (dropWeapon && myEnemy.isRandom()) {
				Weapon enemyWeapon = myEnemy.getWeaponList().get(0);
				enemyWeapon.pickUp(myPlayer);
			}
			return true;
		}
		return false;
	}
	
	/**
	 * Player is dead.
	 *
	 * @return true, if player is dead
	 */
	public boolean playerIsDead(){
		if(myPlayer.getStatsMap().get(Constants.HEALTH).getValue()<=0){
			return true;
		}
		return false;
	}
	
	/**
	 * Weapon dropped.
	 *
	 * @return true, if the weapon was dropped
	 */
	public boolean weaponDropped(){
		if(dropWeapon){
			return true;
		}
		return false;
	}
}
