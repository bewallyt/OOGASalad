package engine.battle;

import java.awt.Graphics2D;

import engine.dialogue.InteractionBox;
import engine.dialogue.UserQueryNode;
import engine.gridobject.person.Enemy;
import engine.gridobject.person.Person;
import engine.gridobject.person.Player;

public class BattleManager implements InteractionBox{
	Player myPlayer;
	Enemy myEnemy;
	BattleAI myBattleAI;
	public BattleManager(Player player, Enemy enemy){
		myPlayer = player;
		myEnemy=enemy;
		myBattleAI=new BattleAI(enemy);
	}

	public void attack(Person attacker, Person victim, Weapon weapon, Attack attack){
		int level = attacker.getStatsMap().get("level").getValue();
		int playerDamage = attacker.getStatsMap().get("damage").getValue();
		int weaponDamage = weapon.getDamage().getValue();
		int attackDamage = attack.getDamage().getValue();
		int defense = victim.getStatsMap().get("defense").getValue();
		int random = 217 + (int)(Math.random() * ((255 - 217) + 1));
		int total = (((((2*level/5+2)*playerDamage*(weaponDamage+attackDamage)/defense)/50)+2)/random);
		if(attackDamage!=0)
			victim.getStatsMap().get("health").changeValue(-total);
		if(attack.getEffect()!=null){
			attack.getEffect().doEffect();
		}
	}
	
	public Person attackFirst(Person person1, Weapon weapon1, Attack attack1, Person person2, Weapon weapon2, Attack attack2){
		if(calcSpeed(person1,weapon1,attack1)>=calcSpeed(person2,weapon2,attack2))
			return person1;
		return person2;
	}
	
	public int calcSpeed(Person person, Weapon weapon, Attack attack){
		return (person.getStatsMap().get("speed").getValue()+weapon.getSpeed().getValue()+attack.getSpeed().getValue());
	}
	
	public void chooseEnemyMove(){
		Weapon weapon = myBattleAI.chooseWeapon();
		Attack attack = myBattleAI.chooseAttack(weapon);
	}
	
	
	
	@Override
	public void paintDisplay(Graphics2D g, int xSize, int ySize, int xOffset,
			int yOffset) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void getNextText() {
		// TODO Auto-generated method stub
		
	}
	public void moveUp() {
		if (RESPONDING) {
			myResponses.moveUp();
			currentUserQueryNode = (UserQueryNode) myResponses.getCurrentNode();
		}
		
	}
	
	public void moveDown() {
		if (RESPONDING) {
			myResponses.moveDown();
			currentUserQueryNode = (UserQueryNode) myResponses.getCurrentNode();
		}
	}
	
	public void moveLeft() {
		if (RESPONDING) {
			myResponses.moveLeft();
			currentUserQueryNode = (UserQueryNode) myResponses.getCurrentNode();
		}
	}
	
	public void moveRight() {
		if (RESPONDING) {
			myResponses.moveRight();
			currentUserQueryNode = (UserQueryNode) myResponses.getCurrentNode();
		}
	}
	
	
}
