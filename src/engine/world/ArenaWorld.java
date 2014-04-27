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
	
	public ArenaWorld(String backgroundImage, int playWidth, int playHeight, Player p, Enemy enemy, WalkAroundWorld prevWorld) {
		super(playWidth, playHeight, p);
		myEnemy = enemy;
		myBackground = new ScaledImage(Constants.CANVASWIDTH,(int) (Constants.CANVASHEIGHT*.65),backgroundImage);
		myPrevWorld=prevWorld;
		
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

}
