package engine.battle;
import java.awt.Image;

import engine.dialogue.AbstractManager;
import engine.dialogue.BattleExecutorNode;
import engine.dialogue.BattleSelectorNode;
import engine.dialogue.InteractionBox;
import engine.dialogue.InteractionMatrix2x2;
import engine.gridobject.person.Enemy;
import engine.gridobject.person.Person;
import engine.gridobject.person.Player;
import engine.item.Item;
import engine.item.Weapon;

public class BattleManager extends AbstractManager implements InteractionBox{
	BattleCalculator myBattleCalculate;
	private Player myPlayer;
	private Enemy myEnemy;
	private BattleAI myBattleAI;
	private BattleSelectorNode myAttackSelector;
	private BattleSelectorNode myBagSelector;
	private BattleSelectorNode myWeaponSelector;
	private BattleSelectorNode myRunSelector;
	private BattleSelectorNode myCurrentBattleSelector;
	private BattleExecutorNode myCurrentBattleExecutorNode;
	public final static int TOPLEVEL=0;
	public final static int BOTTOMLEVEL=1;
	public final static int FIRSTATTACKHAPPENED=2;
	public final static int SECONDATTACKHAPPENED=3;
	public final static int WEAPONSELECTED=4;
	public final static int RAN=5;
	public final static int ENEMYDEAD=6;
	public final static int BATTLEWON=7;
	public static final int EXITWON = 8;
	public static final int PLAYERDEAD = 9;
	public static final int EXITLOST=11;
	public static final int BATTLELOST = 10;
	public final static int ITEMUSED=12;
	public final static int CHOOSETOPICK=13;
	private static int myCurrentState=0;
	private Person myCurrentAttacker;
	private Person myCurrentVictim;
	private static final String TEXT_DISPLAYED_ATTACK=" used ";
	private static final String TEXT_DISPLAYED_WEAPON_SELECTED="weapon selected :: ";
	private static final String TEXT_DISPLAYED_RAN="Got away safely!";
	private static final String TEXT_DISPLAYED_ENEMY_DEAD = "You defeated ";
	private static final String TEXT_DISPLAYED_DROPPED_WEAPON = "Picked dropped weapon!";
	private static final String TEXT_DISPLAYED_PLAYER_DEAD="You have been defeated!";
	private Image myCurrentPlayerBattleImage;
	private Image myCurrentEnemyBattleImage;
	//DialogueListeningState PlayerDead = new DialogueListeningState("You have been defeated!",);

	private static final String TEXT_DISPLAYED_ITEM_USED = "Item used!";
	
	private String itemUsedName = "";
	private String textToBeDisplayed="";
	private boolean ran=false;

	public BattleManager(Player player, Enemy enemy){
		myPlayer = player;
		myEnemy=enemy;
		myBattleAI=new BattleAI(enemy);		
		setOriginalNodes();
		initializeChildrenNodes();
		myCurrentState=0;
		myCurrentPlayerBattleImage=myPlayer.getBattleImage();
		myCurrentEnemyBattleImage = myEnemy.getBattleImage();
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

		getMatrix().setNode(myAttackSelector, 0, 0);
		getMatrix().setNode(myBagSelector, 1, 0);
		getMatrix().setNode(myWeaponSelector, 0, 1);
		getMatrix().setNode(myRunSelector, 1, 1);
		myCurrentBattleSelector=(BattleSelectorNode) getMatrix().getCurrentNode();
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
	public void getNextText() {
		((InteractionMatrix2x2) getMatrix()).resetMatrixPosition();
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
			myCurrentPlayerBattleImage=myPlayer.getBattleImage();
			myCurrentEnemyBattleImage=myEnemy.getBattleImage();

		}
		else if(myCurrentState==FIRSTATTACKHAPPENED){
			Person tempAttacker=myCurrentAttacker;
			myCurrentAttacker=myCurrentVictim;
			myCurrentVictim=tempAttacker;
			setCurrentTextToBeDisplayed();
			myBattleCalculate.attack(myCurrentAttacker,myCurrentVictim,myCurrentAttacker.getCurrentWeapon(),myCurrentAttacker.getCurrentAttack());
			myCurrentState=SECONDATTACKHAPPENED;
			if(myCurrentAttacker instanceof Player)myCurrentPlayerBattleImage=myPlayer.getCurrentWeapon().getImage();
			else{myCurrentEnemyBattleImage=myEnemy.getCurrentWeapon().getImage();}
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
				if(myCurrentAttacker instanceof Player)myCurrentPlayerBattleImage=myPlayer.getCurrentWeapon().getImage();
				else{myCurrentEnemyBattleImage=myEnemy.getCurrentWeapon().getImage();}
				if(myBattleCalculate.enemyIsDead())
					myCurrentState=ENEMYDEAD;
				if(myBattleCalculate.playerIsDead())
					myCurrentState=PLAYERDEAD;

			}
			else if(executable instanceof Item){
				myPlayer.useItem(((Item) executable));
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
			if(myCurrentBattleSelector.getChildren().size()>0){
				for(int i=0; i<getMatrix().getDimension()[0]; i++){
					for(int j=0; j<getMatrix().getDimension()[1]; j++){
						if(myCurrentBattleSelector.getChildren().size()>count)
							getMatrix().setNode(myCurrentBattleSelector.getChildren().get(count), i, j);
						else{
							getMatrix().setNode(null, i, j);
						}
						count++;
					}
				}
				myCurrentBattleExecutorNode = (BattleExecutorNode) getMatrix().getCurrentNode();
				myCurrentState=BOTTOMLEVEL;
			}
		}
	}
	public void setCurrentNode() {
		if(myCurrentState==TOPLEVEL){
			myCurrentBattleSelector=(BattleSelectorNode) getMatrix().getCurrentNode();
		}
		else if(myCurrentState==BOTTOMLEVEL){
			myCurrentBattleExecutorNode=(BattleExecutorNode) getMatrix().getCurrentNode();
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
			textToBeDisplayed=myCurrentAttacker.getCurrentWeapon().toString() + TEXT_DISPLAYED_ATTACK + myCurrentAttacker.getCurrentAttack().toString()
					+ "\n" + myCurrentAttacker.getCurrentAttack().getEffectMessage();
			
		}
		else if(myCurrentState==RAN){
			textToBeDisplayed=TEXT_DISPLAYED_RAN;
		}
		else if(myCurrentState==ENEMYDEAD){
			textToBeDisplayed=TEXT_DISPLAYED_ENEMY_DEAD + myEnemy.toString();
			myPlayer.increaseExperience(myEnemy.getExperience());
			myPlayer.changeMoney(myEnemy.getMoney());
			if (myBattleCalculate.weaponDropped()) {
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
	@Override
	public boolean isResponding() {
		return (myCurrentState==TOPLEVEL || myCurrentState==BOTTOMLEVEL);

	}
	@Override
	public String getTextToBeDisplayed() {
		return textToBeDisplayed;
	}
	public Image getCurrentPlayerBattleImage(){
		return myCurrentPlayerBattleImage;
	}
	public Image getCurrentEnemyBattleImage(){
		return myCurrentEnemyBattleImage;
	}
}
