package engine.main;

import java.util.ArrayList;
import java.util.List;

import engine.Statistic;
import engine.battle.Attack;
import engine.battle.Weapon;
import engine.collision.BattleCollision;
import engine.collision.EnterCollision;
import engine.dialogue.NPCResponseNode;
import engine.dialogue.UserQueryNode;
import engine.gridobject.Barrier;
import engine.gridobject.Door;
import engine.gridobject.GridObject;
import engine.gridobject.person.Enemy;
import engine.gridobject.person.NPC;
import engine.gridobject.person.Player;
import engine.item.Item;
import engine.world.ArenaWorld;
import engine.world.WalkAroundWorld;

public class Main extends RPGEngine {

//	private Player myPlayer;
	private NPC myNPC;


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

		String[] anim = new String[]{"PlayerUp0.png", "PlayerUp1.png", "PlayerUp2.png", 
				"PlayerRight0.png", "PlayerRight1.png", "PlayerRight2.png",
				"PlayerDown0.png", "PlayerDown1.png", "PlayerDown2.png", "PlayerLeft0.png", 
				"PlayerLeft1.png", "PlayerLeft2.png"};
		Player player = new Player(anim, 2, 1, 1);
		Attack attack = new Attack("atck");
		attack.setSpeed(10, 100);
		attack.setDamage(10, 100);
		List<Attack> attackList = new ArrayList<Attack>();
		attackList.add(attack);
		
		Weapon weapon = new Weapon("cabinets.jpg", "wep", attackList);
		player.addWeapon(weapon);
		
		NPC bafm = new NPC(new String[] {"rival.png","rival.png","rival.png","rival.png"}
								,1,1,1, 3, player);
		NPCResponseNode n = new NPCResponseNode(bafm, "Hello there! How are you?");
		NPCResponseNode n0 = new NPCResponseNode(bafm, "You chose choice 0");
		NPCResponseNode n1 = new NPCResponseNode(bafm, "You chose choice 1");
		NPCResponseNode n2 = new NPCResponseNode(bafm, "You chose choice 2");
		NPCResponseNode n3 = new NPCResponseNode(bafm, "You chose choice 3");
		UserQueryNode q0 = new UserQueryNode(player, null, "I feel good", n0);
		UserQueryNode q1 = new UserQueryNode(player, null, "I feel bad", n1);
		UserQueryNode q2 = new UserQueryNode(player, null, "I feel great", n2);
		UserQueryNode q3 = new UserQueryNode(player, null, "I feel meh...", n3);
		n.addResponseNode(q0);
		n.addResponseNode(q1);
		n.addResponseNode(null);
		n.addResponseNode(q3);


		bafm.setResponseNode(n);

		Door door = new Door("cabinets.jpg", 1, 1);

		Door door2 = new Door("cabinets.jpg", 1, 1);
		Door tallGrass = new Door("grassback.jpg",1,1);
		Enemy enemy = new Enemy(anim,2,1,1,1, player);
		enemy.doBattleOnSight();

	

		//enemy.setResponseNode(n);
		Attack attack2 = new Attack("ck");
		attack.setSpeed(10, 100);
		attack.setDamage(10, 100);
		List<Attack> attackList2 = new ArrayList<Attack>();
		attackList2.add(attack2);
		
		Weapon weapon2 = new Weapon("cabinets.jpg", "wp", attackList2);
		enemy.addWeapon(weapon2);
		
		gridObjectList.add(player);
		gridObjectList.add(new Barrier("pokecenter.png",4, 4));
		gridObjectList.add(door);
		gridObjectList.add(bafm);
		gridObjectList.add(enemy);
		gridObjectList.add(tallGrass);
		Barrier cab = new Barrier("cabinets.jpg",1,1);
		cab.setPickupable(new Weapon("grassback.jpg", "weapon", null));
		gridObjectList.add(cab);
		
		gridObjectList2.add(player);
		gridObjectList2.add(new Barrier("pokecenter.png",4, 4));
		gridObjectList2.add(door2);
	
		
		
		
		WalkAroundWorld outsideWorld = new WalkAroundWorld(1000, 1000, player, 40, gridObjectList);
		setWorld(outsideWorld); // this is only called for the initial world
		outsideWorld.addRandomEncounter(enemy);
		enemy.setWorld(new ArenaWorld("battlebackground.png", 800, 800, player,enemy,outsideWorld));
		
		WalkAroundWorld buildingWorld = new WalkAroundWorld(1000, 1000, player, 40, gridObjectList2);
		door.setWorld(buildingWorld);
		door2.setWorld(outsideWorld);
		tallGrass.setWorld(new ArenaWorld("battlebackground.png",800,800,player,outsideWorld.getRandomEncounter(),outsideWorld));
		
		outsideWorld.setTileObject(gridObjectList.get(0), 1, 6);
		outsideWorld.setTileObject(gridObjectList.get(1), 2, 2);
		outsideWorld.setTileObject(gridObjectList.get(2), 4, 5);
		outsideWorld.setTileObject(gridObjectList.get(3), 8, 13);
		outsideWorld.setTileObject(gridObjectList.get(4), 10, 8);
		outsideWorld.setTileObject(gridObjectList.get(5), 12, 12);
		outsideWorld.setTileObject(gridObjectList.get(6), 1, 2);
		outsideWorld.paintFullBackround("grassSmall.png");
		outsideWorld.setCollisionHandler(new EnterCollision(gridObjectList.get(0), 
															gridObjectList.get(2)),0,2);
		
		outsideWorld.setCollisionHandler(new BattleCollision(gridObjectList.get(0),gridObjectList.get(5)), 0, 5);
		buildingWorld.setTileObject(gridObjectList2.get(0), 4, 13);
		buildingWorld.setTileObject(gridObjectList2.get(1), 2, 2);
		buildingWorld.setTileObject(gridObjectList2.get(2), 4, 14);

		buildingWorld.paintFullBackround("pokecenterfloor.png");
		buildingWorld.setCollisionHandler(new EnterCollision(gridObjectList2.get(0), 
				gridObjectList2.get(2)),0,2);
		
		player.setBattleImage("PlayerRight0.png");
		player.addStatistic(new Statistic("health",100,100));
		enemy.setBattleImage("rival.png");
		enemy.addStatistic(new Statistic("health",50,100));
	}

	@Override
	public void initializeGame() {
		initializeCanvas(400, 400);
		makeOutsideWorld();
	}

}