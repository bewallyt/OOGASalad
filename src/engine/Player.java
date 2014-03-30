package engine;

import java.util.*;

public class Player extends RuleFollower {
	
	List<Item> myItems;
	
	public Player(double x, double y, double dx, double dy) {
		super(x,y,dx,dy);
		myItems = null;
	}
	
	@Override 
	public void move() {
		
	}

}
