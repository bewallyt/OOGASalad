package engine.main;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import util.Constants;
import util.Music;
import Data.FileStorer;
import authoring.WorldData;
import engine.Statistic;
import engine.battle.Attack;
import engine.collision.BattleCollision;
import engine.collision.EnterCollision;
import engine.dialogue.NPCResponseNode;
import engine.dialogue.UserQueryNode;
import engine.gridobject.Barrier;
import engine.gridobject.Door;
import engine.gridobject.GridObject;
import engine.gridobject.person.Enemy;
import engine.gridobject.person.Healer;
import engine.gridobject.person.NPC;
import engine.gridobject.person.Player;
import engine.item.Item;
import engine.item.KeyItem;
import engine.item.StatBuffer;
import engine.item.Weapon;
import engine.world.ArenaWorld;
import engine.world.WalkAroundWorld;

public class Main extends RPGEngine {

//	private Player myPlayer;
	private WorldData myWorldData;
	// private DataManager myData;
	private FileStorer myData;
	private Player myPlayer;


	public static void main(String[] args) {
		Main engine = new Main();
		engine.initializeGame();
		try {
			engine.doGameLoop();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void makeOutsideWorld(){
		List<GridObject> gridObjectList = new ArrayList<GridObject>();
		List<GridObject> gridObjectList2 = new ArrayList<GridObject>();

		String[] anim = new String[]{
				"ImageFiles/PlayerDown1.png", 
				"ImageFiles/PlayerDown2.png", 
				"ImageFiles/PlayerLeft1.png", 
				"ImageFiles/PlayerLeft2.png",
				"ImageFiles/PlayerRight1.png", 
				"ImageFiles/PlayerRight2.png",
				"ImageFiles/PlayerUp1.png", 
				"ImageFiles/PlayerUp2.png"};

		Player player = new Player(anim, "Player", 2, new String[1], new String[1]);
		Attack attack = new Attack("flamethrower");
		attack.setSpeed(10, 100);
		attack.setDamage(10, 100);
		attack.setEffect("health", player, 10);
		List<Attack> attackList = new ArrayList<Attack>();
		attackList.add(attack);

		Weapon w2 = new Weapon("ImageFiles/zeldasword.png", "sword", attackList);
		Weapon weapon = new Weapon("ImageFiles/Charmander.png", "charmander", attackList);
		weapon.setDamage(10, 100);
		weapon.setSpeed(10, 100);
		player.addWeapon(weapon);
		
		w2.setDamage(20, 100);
		w2.setSpeed(20, 100);
		player.addWeapon(w2);

		NPC bafm = new NPC(new String[] {"ImageFiles/rival.png","ImageFiles/rival.png","ImageFiles/rival.png","ImageFiles/rival.png"},"npc"
								,1,1,1, 3, player);
		

		NPCResponseNode n = new NPCResponseNode(bafm, "I can't seem to find my sword :(");
		NPCResponseNode n0 = new NPCResponseNode(bafm, "Let me know when you find it!");
		NPCResponseNode n1 = new NPCResponseNode(bafm, "Yeah it sucks");
		NPCResponseNode n2 = new NPCResponseNode(bafm, "Thats not nice...");
		NPCResponseNode n3 = new NPCResponseNode(bafm, "You found it! Thanks");
		UserQueryNode q0 = new UserQueryNode(player, null, "I'll help!", n0);
		UserQueryNode q1 = new UserQueryNode(player, null, "Sorry to hear...", n1);
		UserQueryNode q2 = new UserQueryNode(player, null, "Nice", n2);
		UserQueryNode q3 = new UserQueryNode(player, "sword", null, n3);

		n.addResponseNode(q0);
		n.addResponseNode(q1);
		n.addResponseNode(q2);
		n.addResponseNode(q3);


		bafm.setResponseNode(n);

		Door door = new Door("ImageFiles/cabinets.jpg", 1, 1);

		Door door2 = new Door("ImageFiles/cabinets.jpg", 1, 1);
		Door tallGrass = new Door("ImageFiles/grassback.jpg",1,1);
		Enemy enemy = new Enemy(anim,"enemy",2,1,1,1, player);
		NPCResponseNode ne = new NPCResponseNode(enemy, "Let's Battle!!!!(");
		enemy.setResponseNode(ne);
		Enemy grassEnemy = new Enemy(anim,"grassenemy",2,1,1,1,player);
		enemy.doBattleOnSight();

		//enemy.setResponseNode(n);
		Attack attack2 = new Attack("enemyattack");
		attack2.setSpeed(10, 100);
		attack2.setDamage(10, 100);
		List<Attack> attackList2 = new ArrayList<Attack>();
		attackList2.add(attack2);

		Weapon weapon2 = new Weapon("ImageFiles/cabinets.jpg", "wp", attackList2);
		weapon2.setDamage(10, 100);
		weapon2.setSpeed(10, 100);
		enemy.addWeapon(weapon2);
		grassEnemy.addWeapon(weapon2);

		gridObjectList.add(player);
		gridObjectList.add(new Barrier("ImageFiles/pokecenter.png",4, 4));
		gridObjectList.add(door);
		gridObjectList.add(bafm);
		gridObjectList.add(enemy);
		gridObjectList.add(tallGrass);
		Barrier cab = new Barrier("ImageFiles/zeldasword.png",1,1);
	//	cab.setPickupable(new Weapon("ImageFiles/grassback.jpg", "weapon", null));
		cab.setPickupable(new KeyItem("ImageFiles/zeldasword.png", "sword"));

		gridObjectList.add(cab);

		gridObjectList2.add(player);
		gridObjectList2.add(new Barrier("ImageFiles/pokecenter.png",4, 4));
		gridObjectList2.add(door2);
		gridObjectList2.add(new Healer(new String[] {"ImageFiles/rival.png","ImageFiles/rival.png","ImageFiles/rival.png","ImageFiles/rival.png"},"healer"
								,1,1,1, 3, player));




		WalkAroundWorld outsideWorld = new WalkAroundWorld("outsideWorld", 1000, 1000, player, 40, gridObjectList);
		setWorld(outsideWorld); // this is only called for the initial world
		outsideWorld.addRandomEncounter(grassEnemy);
		enemy.setWorld(new ArenaWorld("ImageFiles/battlebackground.png", 800, 800, player,enemy,outsideWorld));

		WalkAroundWorld buildingWorld = new WalkAroundWorld("buildingWorld",1000, 1000, player, 40, gridObjectList2);
		door.setWorld(buildingWorld);
		door2.setWorld(outsideWorld);

		tallGrass.setWorld(new ArenaWorld("ImageFiles/battlebackground.png",800,800,player,outsideWorld
								.getRandomEncounter(),outsideWorld));

		outsideWorld.setMusic("/music/pokeTest.wav");

		outsideWorld.setTileObject(gridObjectList.get(0), 1, 6);
		outsideWorld.setTileObject(gridObjectList.get(1), 2, 2);
		outsideWorld.setTileObject(gridObjectList.get(2), 4, 5);
		outsideWorld.setTileObject(gridObjectList.get(3), 8, 13);
		outsideWorld.setTileObject(gridObjectList.get(4), 10, 8);
		outsideWorld.setTileObject(gridObjectList.get(5), 12, 12);
		outsideWorld.setTileObject(gridObjectList.get(6), 1, 12);
		outsideWorld.paintFullBackround("ImageFiles/grassSmall.png");
		outsideWorld.setCollisionHandler(new EnterCollision(gridObjectList.get(0), 
															gridObjectList.get(2)),0,2);

		outsideWorld.setCollisionHandler(new BattleCollision(gridObjectList.get(0),gridObjectList.get(5)), 0, 5);
		buildingWorld.setTileObject(gridObjectList2.get(0), 4, 13);
		buildingWorld.setTileObject(gridObjectList2.get(1), 2, 2);
		buildingWorld.setTileObject(gridObjectList2.get(2), 4, 14);
		buildingWorld.setTileObject(gridObjectList2.get(3), 12, 2);

		buildingWorld.paintFullBackround("ImageFiles/pokecenterfloor.png");
		buildingWorld.setCollisionHandler(new EnterCollision(gridObjectList2.get(0), 
				gridObjectList2.get(2)),0,2);
		
		player.setBattleImage("ImageFiles/PlayerRight0.png");
		player.addStatistic(new Statistic("health",100,100));
		player.addStatistic(new Statistic("damage",90,100));
		player.addStatistic(new Statistic("speed",10,100));
		player.addStatistic(new Statistic("level",10,100));
		player.addStatistic(new Statistic("defense",10,100));
		enemy.setBattleImage("ImageFiles/rival.png");
		enemy.addStatistic(new Statistic("health",50,100));
		enemy.addStatistic(new Statistic("damage",10,100));
		enemy.addStatistic(new Statistic("speed",10,100));
		enemy.addStatistic(new Statistic("level",10,100));
		enemy.addStatistic(new Statistic("defense",10,100));

		enemy.getWorld().setMusic("/music/pokeBattle.wav");

		buildingWorld.setMusic("/music/pokeCenter.wav");


		grassEnemy.setBattleImage("ImageFiles/player.png");
		grassEnemy.addStatistic(new Statistic("health",50,100));
		grassEnemy.addStatistic(new Statistic("damage",10,100));
		grassEnemy.addStatistic(new Statistic("speed",10,100));
		grassEnemy.addStatistic(new Statistic("level",10,100));
		grassEnemy.addStatistic(new Statistic("defense",10,100));
	}



	private void makeOutsideWorld2(){
		List<GridObject> gridObjectList = new ArrayList<GridObject>();

//		String[] anim = new String[]{"PlayerUp0.png", "PlayerUp1.png", "PlayerUp2.png", 
//				"PlayerRight0.png", "PlayerRight1.png", "PlayerRight2.png",
//				"PlayerDown0.png", "PlayerDown1.png", "PlayerDown2.png", "PlayerLeft0.png", 
//				"PlayerLeft1.png", "PlayerLeft2.png"};
		String[] anim = new String[]{
				Constants.PLAYERASHPATH+"PlayerUp0.png",
				Constants.PLAYERASHPATH+"PlayerUp1.png",
				Constants.PLAYERASHPATH+"PlayerUp2.png",
				Constants.PLAYERASHPATH+"PlayerRight0.png",
				Constants.PLAYERASHPATH+"PlayerRight1.png",
				Constants.PLAYERASHPATH+"PlayerRight2.png",
				Constants.PLAYERASHPATH+"PlayerDown0.png",
				Constants.PLAYERASHPATH+"PlayerDown1.png",
				Constants.PLAYERASHPATH+"PlayerDown2.png",
				Constants.PLAYERASHPATH+"PlayerLeft0.png",
				Constants.PLAYERASHPATH+"PlayerLeft1.png",
				Constants.PLAYERASHPATH+"PlayerLeft2.png",
		};
		Player player = new Player(anim, "player",2, new String[1], new String[1]);


		gridObjectList.add(player);

		WalkAroundWorld outsideWorld = new WalkAroundWorld("outsideWorld",1000, 1000, player, 40, gridObjectList);
		setWorld(outsideWorld); // this is only called for the initial world

		outsideWorld.setTileObject(gridObjectList.get(0), 1, 6);
	}


	@Override
	public void initializeGame() {
		setInit(true);
		//initMusicTest();
		initializeCanvas(500, 500);
		makeOutsideWorld();
	}
}