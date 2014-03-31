package engine.gridobject;

import java.util.List;

import engine.gridobject.item.Item;
import engine.gridobject.item.Weapon;
import engine.world.Tile;

public class RuleFollower extends GridObject {

	protected List<Item> myItems;
	protected double mySpeed;
	protected double myDX=0;
	protected double myDY=0;
	protected List<String> myDialogue;
	protected Weapon myWeapon;
	
	public RuleFollower(String image, double speed) {
		super(image);
		mySpeed = speed;
		myDialogue=null;
		
		myWeapon = null;
	}
	
	@Override
	public void move() {
		if(myDX>0)facing="right";
		else if(myDX<0)facing="left";
		else if(myDY>0)facing="down";
		else if(myDY<0)facing="up";
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
