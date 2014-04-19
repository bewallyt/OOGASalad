package engine.battle;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.IOException;
import java.io.InputStream;

import engine.dialogue.AttackExecutorNode;
import engine.dialogue.BattleExecutorNode;
import engine.dialogue.BattleSelectorNode;
import engine.dialogue.InteractionBox;
import engine.dialogue.InteractionMatrix;
import engine.dialogue.InteractionMatrix2x2;
import engine.dialogue.MatrixNode;
import engine.gridobject.GridObject;
import engine.gridobject.person.Enemy;
import engine.gridobject.person.Person;
import engine.gridobject.person.Player;
import engine.item.Item;

public class BattleManager implements InteractionBox{
	private Player myPlayer;
	private Enemy myEnemy;
	private BattleAI myBattleAI;
	private InteractionMatrix myOptions;
	private BattleSelectorNode myAttackSelector;
	private BattleSelectorNode myBagSelector;
	private BattleSelectorNode myWeaponSelector;
	private BattleSelectorNode myRunSelector;
	private BattleSelectorNode myCurrentBattleSelector;
	private BattleExecutorNode myCurrentBattleExecutorNode;
	private final static int TOPLEVEL=0;
	private final static int BOTTOMLEVEL=1;
	private final static int ATTACKHAPPENED=2;
	private final static int WEAPONSELECTED=3;
	private static int myCurrentState=0;
	private Person myCurrentAttacker;
	private Person myCurrentVictim;
	private static final int SYMBOL_RADIUS = 10;
	private static final String TEXT_DISPLAYED_EVENT_COMPLETED="event was completed! Wooooo";
	private static final String TEXT_DISPLAYED_WEAPON_SELECTED="weapon selected :: ";
//	private String textToBeDisplayed="event was completed! Wooooo";
	private String textToBeDisplayed=TEXT_DISPLAYED_EVENT_COMPLETED;

	public BattleManager(Player player, Enemy enemy){
		myPlayer = player;
		myEnemy=enemy;
		myBattleAI=new BattleAI(enemy);
		myOptions = new InteractionMatrix2x2();
		setOriginalNodes();
		initializeChildrenNodes();
	}
	private void initializeChildrenNodes() {
		setAttackChildrenNodes(myAttackSelector);
		setWeaponChildrenNodes(myWeaponSelector);
//		setBagChildrenNodes(myBagSelector);
		setRunChildrenNodes(myRunSelector);
	}
	private void updateAttackList(){
		setAttackChildrenNodes(myAttackSelector);
	}
	private void setOriginalNodes(){
		myAttackSelector = new BattleSelectorNode("Attack");
		myBagSelector = new BattleSelectorNode("Bag");
		myWeaponSelector = new BattleSelectorNode("Weapon");
		myRunSelector = new BattleSelectorNode("Run");

		myOptions.setNode(myAttackSelector, 0, 0);
		myOptions.setNode(myBagSelector, 1, 0);
		myOptions.setNode(myWeaponSelector, 0, 1);
		myOptions.setNode(myRunSelector, 1, 1);
		myCurrentBattleSelector=(BattleSelectorNode) myOptions.getCurrentNode();
	}

	private void setAttackChildrenNodes(BattleSelectorNode node){
		for(Attack attack : myPlayer.getCurrentWeapon().getAttackList()){
			BattleExecutorNode executorNode = new AttackExecutorNode(attack);
			node.setChild(executorNode);
		}
	}

	private void setWeaponChildrenNodes(BattleSelectorNode node){
		for(Weapon weapon : myPlayer.getWeaponList()){
			BattleExecutorNode executorNode = new WeaponExecutorNode(weapon);
			node.setChild(executorNode);
		}
	}

	private void setBagChildrenNodes(BattleSelectorNode node){
		for(Item item : myPlayer.getItems()){
			BattleExecutorNode executorNode = new BagExecutorNode(item);
			node.setChild(executorNode);
		}
	}

	private void setRunChildrenNodes(BattleSelectorNode node){
		Run run = new Run();
		BattleExecutorNode executorNode = new RunExecutorNode(run);
		node.setChild(executorNode);

	}



	public void attack(Person attacker, Person victim, Weapon weapon, Attack attack){
		int level = attacker.getStatsMap().get("level").getValue();
		int playerDamage = attacker.getStatsMap().get("damage").getValue();
		int weaponDamage = weapon.getDamage().getValue();
		int attackDamage = attack.getDamage().getValue();
		int defense = victim.getStatsMap().get("defense").getValue();
		int random = 30 + (int)(Math.random() * ((25 - 30) + 1));
		int total = (((((2*level+2)*playerDamage*(weaponDamage+attackDamage)/defense))+2)/random);
		System.out.println("pd " + playerDamage);
		System.out.println("ad " + attackDamage);
		System.out.println("wd " + weaponDamage);
		System.out.println("level " + level);
		System.out.println("random " + random);
		System.out.println("defense " + defense);
		System.out.println("total " + total);
		if(attackDamage!=0)
			victim.getStatsMap().get("health").changeValue(-total);
		if(attack.getEffect()!=null){
			attack.getEffect().doEffect();
		}
		System.out.println(myEnemy.getStatsMap().get("health").getValue());
	}

	public Person[] attackFirst(Person person1, Weapon weapon1, Attack attack1, Person person2, Weapon weapon2, Attack attack2){
		if(calcSpeed(person1,weapon1,attack1)>=calcSpeed(person2,weapon2,attack2))
			return new Person[] {person1,person2};
		return new Person[] {person2,person1};
	}

	public int calcSpeed(Person person, Weapon weapon, Attack attack){
		return (person.getStatsMap().get("speed").getValue()+weapon.getSpeed().getValue()+attack.getSpeed().getValue());
	}

	public void chooseEnemyMove(){
		Weapon weapon = myBattleAI.chooseWeapon();
		Attack attack = myBattleAI.chooseAttack(weapon);
	}



	@Override
	public void paintDisplay(Graphics2D g2d, int xSize, int ySize, int width,int height) {
		InputStream is = GridObject.class.getResourceAsStream("PokemonGB.ttf");
		Font font=null;
		try {
			try {
				font = Font.createFont(Font.TRUETYPE_FONT, is);
			} catch (FontFormatException e) {
				e.printStackTrace();
			}
			Font sizedFont = font.deriveFont(12f);
			g2d.setFont(sizedFont);
		} catch (IOException e) {
			e.printStackTrace();
		}

		g2d.setColor(Color.white);
		g2d.fill(new Rectangle((int) ((int) 0), ySize/2+60, width , height));
		g2d.setColor(Color.black);
		if(myCurrentState==TOPLEVEL || myCurrentState==BOTTOMLEVEL){
			printResponses(g2d, myOptions, xSize, ySize, width, height);
		}
		else {
			g2d.drawString(textToBeDisplayed, (int) xSize/10, ySize/2+120);
		} 
			

	}

	private void printResponses(Graphics2D g2d, InteractionMatrix myResponses2, int xSize, int ySize, 
			int width, int height) {
		int xCornerLoc = xSize/10;
		int yCornerLoc = ySize/2 + 120;
		for (int i = 0; i < myOptions.getDimension()[0]; i++) {
			for (int j = 0; j < myOptions.getDimension()[1]; j++) {
				MatrixNode qn = (MatrixNode) myOptions.getNode(j, i);
				if(qn!=null)g2d.drawString(qn.getString(), (int) (xCornerLoc + j*(xSize*5/10)), (int)(yCornerLoc + i*(height*3/10)));
			}
		}

		int[] selectedOptionLoc = myOptions.getSelectedNodeLocation();
		g2d.fillOval((int) (xCornerLoc-10 + selectedOptionLoc[0]*(xSize-25)*5/10) - SYMBOL_RADIUS, 
				(int) (yCornerLoc + selectedOptionLoc[1]*(height-15)*3/10) - SYMBOL_RADIUS, SYMBOL_RADIUS, SYMBOL_RADIUS);
	}
	@Override
	public void getNextText() {
		if (myCurrentState==ATTACKHAPPENED || myCurrentState==WEAPONSELECTED){
			setOriginalNodes();
			initializeChildrenNodes();
			myCurrentState=TOPLEVEL;
			
		}
		else if(myCurrentState==BOTTOMLEVEL){
			BattleExecutable executable = myCurrentBattleExecutorNode.getExecutor();
			if(executable instanceof Weapon){
				myPlayer.setCurrentWeapon((Weapon) executable);
				myCurrentState=WEAPONSELECTED;
				setCurrentTextToBeDisplayed();
			}
			else if(executable instanceof Attack){
				Weapon enemyWeapon = myBattleAI.chooseWeapon();
				myEnemy.setCurrentWeapon(enemyWeapon);
				myEnemy.setCurrentAttack(myBattleAI.chooseAttack(enemyWeapon));
				myPlayer.setCurrentAttack((Attack) executable);

				myCurrentAttacker = attackFirst(myPlayer, myPlayer.getCurrentWeapon(), 
						(Attack) executable, myEnemy, enemyWeapon, myEnemy.getCurrentAttack())[0];
				myCurrentVictim = attackFirst(myPlayer, myPlayer.getCurrentWeapon(), 
						(Attack) executable, myEnemy, enemyWeapon, myEnemy.getCurrentAttack())[1];
				attack(myCurrentAttacker,myCurrentVictim,myCurrentAttacker.getCurrentWeapon(),myCurrentAttacker.getCurrentAttack());
				attack(myCurrentVictim,myCurrentAttacker,myCurrentVictim.getCurrentWeapon(),myCurrentVictim.getCurrentAttack());
				myCurrentState=ATTACKHAPPENED;
				setCurrentTextToBeDisplayed();
			}
			else if(executable instanceof Item)
				((Item) executable).useItem();
			else if(executable instanceof Run){
				((Run) executable).getRunMessage();
				//go to walkaround world!
			}
	//		myCurrentState=ATTACKHAPPENED;

		}
		else if(myCurrentState==TOPLEVEL){
			int count=0;
			myCurrentBattleSelector.getChildren().size();
			for(int i=0; i<myOptions.getDimension()[0]; i++){
				for(int j=0; j<myOptions.getDimension()[1]; j++){
					if(myCurrentBattleSelector.getChildren().size()>count)
						myOptions.setNode(myCurrentBattleSelector.getChildren().get(count), i, j);
					else{
						myOptions.setNode(null, i, j);
					}
					count++;
				}
			}
			myCurrentBattleExecutorNode = (BattleExecutorNode) myOptions.getCurrentNode();
			myCurrentState=BOTTOMLEVEL;
		}
		
		


	}
	public void moveUp() {
		myOptions.moveUp();
		setCurrentNode();
	}

	public void moveDown() {
		myOptions.moveDown();
		setCurrentNode();
	}

	public void moveLeft() {
		myOptions.moveLeft();
		setCurrentNode();
	}

	public void moveRight() {
		myOptions.moveRight();
		setCurrentNode();
	}
	private void setCurrentNode() {
		if(myCurrentState==TOPLEVEL){
			myCurrentBattleSelector=(BattleSelectorNode) myOptions.getCurrentNode();
		}
		else if(myCurrentState==BOTTOMLEVEL){
			myCurrentBattleExecutorNode=(BattleExecutorNode) myOptions.getCurrentNode();
		}
	}
	public Player getPlayer() {
		return myPlayer;
	}
	private void setCurrentTextToBeDisplayed() {
		if(myCurrentState==WEAPONSELECTED){
			textToBeDisplayed=TEXT_DISPLAYED_WEAPON_SELECTED + myPlayer.getCurrentWeapon().getString();
		}
		else if(myCurrentState==ATTACKHAPPENED){
			textToBeDisplayed=TEXT_DISPLAYED_EVENT_COMPLETED;
		}
	}


}
