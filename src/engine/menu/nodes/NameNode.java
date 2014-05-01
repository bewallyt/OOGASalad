package engine.menu.nodes;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import util.Constants;
import engine.gridobject.person.Player;
import engine.images.ScaledImage;
import engine.menu.managers.MenuManager;
import engine.menu.managers.NameManager;
import engine.state.NameState;

public class NameNode extends MenuNode {

	private Player myPlayer;
	private MenuManager myMenuManager;
	private NameManager myNameManager;
	private List<Integer> myPlayerStats;
	private String myPlayerName;
	private Image myPlayerImage;

	public NameNode(Player p, MenuManager mm) {
		myPlayer = p;
		myMenuManager = mm;

	}

	@Override
	public void doAction() {
		getPlayerData();
		changeWorld();
		changeState();

	}

	@Override
	public void changeWorld() {
		myNameManager = new NameManager(myPlayer);
		myNameManager.extractPlayerStats(myPlayerStats, myPlayerName, myPlayerImage);
		myPlayer.setInteractionBox(myNameManager);

	}

	@Override
	public void changeState() {
		myPlayer.setState(new NameState(myPlayer, myMenuManager));

	}

	private void getPlayerData() {
		
		myPlayerStats = new ArrayList<Integer>();
		
		
		// Image
		myPlayerImage = new ScaledImage(70, 70, "./src/" + Constants.IMAGEPATH + "PokemonTrainer.png").scaleImage();

		// (1) Name
		myPlayerName = myPlayer.getName();

		
		// (2) Level/Experience
		int level      = myPlayer.getStatsMap().get(Constants.LEVEL).getValue();
		int experience = myPlayer.getExperience();
		
		// (3) Damage/Speed/Defense
		int damage  = myPlayer.getStatsMap().get(Constants.DAMAGE).getValue();
		int speed   = myPlayer.getStatsMap().get(Constants.SPEED).getValue();
		int defense = myPlayer.getStatsMap().get(Constants.DEFENSE).getValue();
		
		// (4) Money
		int money = myPlayer.getMoney();
		
		myPlayerStats.add(level);
		myPlayerStats.add(experience);
		myPlayerStats.add(damage);
		myPlayerStats.add(speed);
		myPlayerStats.add(defense);
		myPlayerStats.add(money);

	}

	@Override
	public String getGame() {
		// TODO Auto-generated method stub
		return null;
	}

}
