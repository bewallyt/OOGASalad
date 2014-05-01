package engine.menu.managers;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import util.PokemonFont;
import engine.dialogue.InteractionBox;
import engine.gridobject.person.Player;

public class NameManager extends WeaponInfoManager implements InteractionBox {

	private Player myPlayer;
	private List<String> myPlayerStats;
	private Image myPlayerImage;
	

	public NameManager(Player p) {
		super(null, null, null, new ArrayList<Integer>());
		myPlayer = p;
		myPlayerStats = new ArrayList<String>();
	}

	@Override
	public void paintDisplay(Graphics2D g2d, int xSize, int ySize, int xOffset,
			int yOffset) {
		paintInfoBox(g2d, "ImageFiles/PokemonBox.png", -50, -50, 600, 600);
		paintFeatureImage(g2d, myPlayerImage, 30, 30);
		displayFeatureName(g2d, myPlayerStats.get(0), 150, 55);
		displayFeatureStats(g2d, 155, 95);

	}
	
	private void displayFeatureStats(Graphics g2d,int x, int y) {
		PokemonFont.setFont(g2d, 16f);
		g2d.drawString("LVL: " + myPlayerStats.get(1), x, y);
		g2d.drawString("EXP: " + myPlayerStats.get(2), x, y + 30);
		g2d.drawString("Money:  $" + myPlayerStats.get(6), x - 125, y + 120);
		g2d.drawString("Damage:  " + myPlayerStats.get(3), x - 125, y + 150);
		g2d.drawString("Speed:   " + myPlayerStats.get(4), x - 125, y + 180);
		g2d.drawString("Defense: " + myPlayerStats.get(5), x - 125, y + 210);
	}


	@Override
	public void getNextText() {
		// TODO Auto-generated method stub

	}

	public void extractPlayerStats(List<Integer> ps, String pn, Image pi) {
		
		myPlayerImage = pi;
		myPlayerStats.add(pn); // name
		myPlayerStats.add(Integer.toString(ps.get(0))); // level 
		myPlayerStats.add(Integer.toString(ps.get(1))); // experience
		myPlayerStats.add(Integer.toString(ps.get(2))); // damage 
		myPlayerStats.add(Integer.toString(ps.get(3))); // speed
		myPlayerStats.add(Integer.toString(ps.get(4))); // defense
		myPlayerStats.add( Integer.toString(ps.get(5))); // money 
	}
	
	

}
