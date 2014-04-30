package engine.world;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import util.Music;
import engine.collision.CollisionHandler;
import engine.collision.CollisionMatrix;
import engine.dialogue.InteractionBox;
import engine.dialogue.TextDisplayer;
import engine.dialogue.TransparentDisplayer;
import engine.gridobject.GridObject;
import engine.gridobject.person.Player;
import engine.main.Main;

public abstract class World {


	private Player myPlayer;
	private int myPlayWidth;
	private int myPlayHeight;
	private int[] mySavedPlayerPosition;
	private TextDisplayer myTextDisplayer;
	private Music myMusic;

	/**
	 * Instantiates a new World.
	 *
	 * @param numTileWidth the num tile width
	 * @param numTileHeight the num tile height
	 * @param tileSize the tile size
	 */
	public World(int playWidth, int playHeight, Player p) {
		myPlayer = p;
		myPlayWidth=playWidth;
		myPlayHeight=playHeight;
		myTextDisplayer = new TextDisplayer(new TransparentDisplayer());
	}

	public int getPlayWidth() {
		return myPlayWidth;
	}


	public int getPlayHeight() {
		return myPlayHeight;

	}
	
	public Player getPlayer(){
		return myPlayer;
	}
	public void savePlayerPosition(){
		mySavedPlayerPosition = new int[] {getPlayer().getX(),getPlayer().getY(),reverseFacing()};
	}
	
	public int[] getSavedPlayerPosition(){
		return mySavedPlayerPosition;
	}
	private int reverseFacing(){
		if(getPlayer().getFacing()==1)return 3;
		else{
			return Math.abs(getPlayer().getFacing()-2);
		}
	}
	/**
	 * This method will place an InteractionBox into the TextDisplayer (container for the goods).
	 * This TextDisplayer will then be painted in every cycle.
	 * @param b InteractionBox to put into the TextDisplayer
	 */
	public void setTextDisplayer(InteractionBox b) {
		myTextDisplayer.setInteractionBox(b);
	}
	public TextDisplayer getTextDisplayer() {
		return myTextDisplayer;
	}
	public void setMusic(String musicFile){
		URL mainURL = Main.class.getResource(musicFile);
		myMusic= new Music(mainURL);
	}
	public Music getMusic(){
		return myMusic;
	}

}
