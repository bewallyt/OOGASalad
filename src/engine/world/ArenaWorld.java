package engine.world;

import java.awt.Image;

import util.Constants;
import engine.gridobject.person.Enemy;
import engine.gridobject.person.Player;
import engine.images.ScaledImage;
import engine.item.Weapon;

public class ArenaWorld extends World {
	private ScaledImage myBackground;
	private Enemy myEnemy;
	private WalkAroundWorld myPrevWorld;
	private boolean randomEncounter=false;
	private Image myPlayerImage;
	private Image myEnemyImage;
	private String[] myLabels;
	
	/**
	 * Instantiates a new arena world.
	 *
	 * @param backgroundImage the background image
	 * @param playWidth the play width
	 * @param playHeight the play height
	 * @param p the player
	 * @param enemy the enemy
	 * @param prevWorld the previous world to go back to
	 * @param labels the labels used for battling. 0=attack 1=weapon 2=bag 3=run
	 */
	public ArenaWorld(String backgroundImage, int playWidth, int playHeight, Player p, Enemy enemy, WalkAroundWorld prevWorld, String[] labels) {
		super(playWidth, playHeight, p);
		myEnemy = enemy;
		myBackground = new ScaledImage(Constants.CANVASWIDTH,(int) (Constants.CANVASHEIGHT*.65),backgroundImage);
		myPrevWorld=prevWorld;
		myLabels=labels;
	}
	
	public Enemy getEnemy(){
		return myEnemy;
	}
	
	public ScaledImage getBackground(){
		return myBackground;
	}
	
	public void setRandomEncounter(){
		randomEncounter=true;
	}
	
	public Weapon getDroppedWeapon(){
		return myEnemy.getWeaponList().get(0);
	}
	
	public WalkAroundWorld getPrevWorld(){
		return myPrevWorld;
	}

	public void setPlayerImage(Image currentPlayerBattleImage) {
		myPlayerImage=currentPlayerBattleImage;	
	}

	public void setEnemyImage(Image currentEnemyBattleImage) {
		myEnemyImage=currentEnemyBattleImage;	
	}
	public Image getPlayerImage(){
		return myPlayerImage;
	}
	public Image getEnemyImage(){
		return myEnemyImage;
	}

	public String[] getLabels() {
		return myLabels;
	}
	

}
