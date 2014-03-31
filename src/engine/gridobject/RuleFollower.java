package engine.gridobject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import engine.Statistic;
import engine.gridobject.item.Item;
import engine.gridobject.item.Weapon;

public class RuleFollower extends GridObject {

	protected List<Item> myItems;
	protected double mySpeed;
	protected double myDX=0;
	protected double myDY=0;
	protected List<String> myDialogue;
	protected Weapon myWeapon;
	
	public RuleFollower(int x, int y, String image, double speed) {
		super(x,y,image);
		mySpeed = speed;
		myDialogue=null;
		
		myWeapon = null;
	}
	
	@Override
	public void move() {
		myX+=myDX;
		myY+=myDY;
	}
	
	public void addWeapon(Weapon weapon){
		
	}

	public void addItem(Item it) {
		myItems.add(it);
	}
	
	public void removeItem(Item it) {
		for (Item current : myItems) {
			if (current.getName().equals(it.getName())) {
				myItems.remove(current);
			}
		}
	}
	
	public void addDialogue(String dialogue){
		myDialogue.add(dialogue);
	}
	public List<String> getDialogueList(){
		return myDialogue;
	}

}
