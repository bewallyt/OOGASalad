package engine.battle;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.IOException;
import java.io.InputStream;

import engine.dialogue.BattleExecutorNode;
import engine.dialogue.BattleSelectorNode;
import engine.dialogue.DialogueListeningState;
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
	BattleCalculator myBattleCalculate;
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
	private final static int FIRSTATTACKHAPPENED=2;
	private final static int SECONDATTACKHAPPENED=3;
	private final static int WEAPONSELECTED=4;
	private final static int RAN=5;
	public final static int ENEMYDEAD=6;
	private final static int BATTLEWON=7;
	private static int myCurrentState=0;
	private Person myCurrentAttacker;
	private Person myCurrentVictim;
	private static final int SYMBOL_RADIUS = 10;
	private static final String TEXT_DISPLAYED_ATTACK=" used ";
	private static final String TEXT_DISPLAYED_SECOND_ATTACK_COMPLETED="second attack damaged by: ";
	private static final String TEXT_DISPLAYED_WEAPON_SELECTED="weapon selected :: ";
	private static final String TEXT_DISPLAYED_RAN="Got away safely!";
	private static final String TEXT_DISPLAYED_ENEMY_DEAD = "You defeated ";
	private static final String TEXT_DISPLAYED_DROPPED_WEAPON = "Picked dropped weapon!";
	private static final String TEXT_DISPLAYED_PLAYER_DEAD="You have been defeated!";
	//DialogueListeningState PlayerDead = new DialogueListeningState("You have been defeated!",);
	
	private static final String TEXT_DISPLAYED_ITEM_USED = "Item used!";
	public static final int EXITWON = 8;
	private static final int PLAYERDEAD = 9;
	public static final int EXITLOST=11;
	private static final int BATTLELOST = 10;
	private final static int ITEMUSED=12;
	private String itemUsedName = "";
	private String textToBeDisplayed;
	private boolean ran=false;
	private boolean dropWeapon = false;

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
		setBagChildrenNodes(myBagSelector);
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
		for(BattleExecutable attack : myPlayer.getCurrentWeapon().getAttackList()){
			setNextChild(node, attack);
		}
	}
	private void setNextChild(BattleSelectorNode node, BattleExecutable attack) {
		BattleExecutorNode executorNode = new BattleExecutorNode(attack);
		node.setChild(executorNode);
	}

	private void setWeaponChildrenNodes(BattleSelectorNode node){
		for(BattleExecutable weapon : myPlayer.getWeaponList()){
			setNextChild(node, weapon);
		}
	}

	private void setBagChildrenNodes(BattleSelectorNode node){
		for(BattleExecutable item : myPlayer.getItems()){
			setNextChild(node, item);
		}
	}

	private void setRunChildrenNodes(BattleSelectorNode node){
		BattleExecutable run = new Run();
		setNextChild(node, run);

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
				if(qn!=null)g2d.drawString(qn.toString(), (int) (xCornerLoc + j*(xSize*5/10)), (int)(yCornerLoc + i*(height*3/10)));
			}
		}

		int[] selectedOptionLoc = myOptions.getSelectedNodeLocation();
		g2d.fillOval((int) (xCornerLoc-10 + selectedOptionLoc[0]*(xSize-25)*5/10) - SYMBOL_RADIUS, 
				(int) (yCornerLoc + selectedOptionLoc[1]*(height-15)*3/10) - SYMBOL_RADIUS, SYMBOL_RADIUS, SYMBOL_RADIUS);
	}
	@Override
	public void getNextText() {
		((InteractionMatrix2x2) myOptions).resetMatrixPosition();
		if(myCurrentState==RAN){
			ran=true;
		}
		else if(myCurrentState==PLAYERDEAD){
			setCurrentTextToBeDisplayed();
			myCurrentState=BATTLELOST;
		}
		else if (myCurrentState==ENEMYDEAD){
			setCurrentTextToBeDisplayed();
			myCurrentState=BATTLEWON;
		}
		else if(myCurrentState==BATTLEWON){
			myCurrentState=EXITWON;
		}
		else if(myCurrentState==BATTLELOST){
			
			myCurrentState=EXITLOST;
		}
		else if (myCurrentState==SECONDATTACKHAPPENED || myCurrentState==WEAPONSELECTED || myCurrentState==ITEMUSED){
			setOriginalNodes();
			initializeChildrenNodes();
			myCurrentState=TOPLEVEL;

		}
		else if(myCurrentState==FIRSTATTACKHAPPENED){
			Person tempAttacker=myCurrentAttacker;
			myCurrentAttacker=myCurrentVictim;
			myCurrentVictim=tempAttacker;
			setCurrentTextToBeDisplayed();
			myBattleCalculate.attack(myCurrentAttacker,myCurrentVictim,myCurrentAttacker.getCurrentWeapon(),myCurrentAttacker.getCurrentAttack());
			myCurrentState=SECONDATTACKHAPPENED;
			if(myBattleCalculate.enemyIsDead())
				myCurrentState=ENEMYDEAD;
			if(myBattleCalculate.playerIsDead()){
				myCurrentState=PLAYERDEAD;
			}
			

		}
		else if(myCurrentState==BOTTOMLEVEL){
			BattleExecutable executable = myCurrentBattleExecutorNode.getExecutor();
			if(executable instanceof Weapon){
				myPlayer.setCurrentWeapon((Weapon) executable);
				myCurrentState=WEAPONSELECTED;
				updateAttackList();
				setCurrentTextToBeDisplayed();
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
			textToBeDisplayed=TEXT_DISPLAYED_WEAPON_SELECTED + myPlayer.getCurrentWeapon().toString();
		}
		else if(myCurrentState==ITEMUSED){
			textToBeDisplayed=TEXT_DISPLAYED_ITEM_USED + itemUsedName;
		}
		else if (myCurrentState==PLAYERDEAD){
			textToBeDisplayed=TEXT_DISPLAYED_PLAYER_DEAD;
		}
		else if(myCurrentState==FIRSTATTACKHAPPENED || myCurrentState==SECONDATTACKHAPPENED){
			textToBeDisplayed=myCurrentAttacker.toString() + TEXT_DISPLAYED_ATTACK + myCurrentAttacker.getCurrentAttack().toString();
		}
		else if(myCurrentState==RAN){
			textToBeDisplayed=TEXT_DISPLAYED_RAN;
		}
		else if(myCurrentState==ENEMYDEAD){
			textToBeDisplayed=TEXT_DISPLAYED_ENEMY_DEAD + myEnemy.toString();
			if (dropWeapon) {
				textToBeDisplayed=TEXT_DISPLAYED_DROPPED_WEAPON;
			} 
				
			
		}
	}
	public boolean didRun(){
		return ran;
	}
	public int getCurrentState(){
		return myCurrentState;
	}
	public void setCurrentState(int state){
		myCurrentState=state;
	}
}
