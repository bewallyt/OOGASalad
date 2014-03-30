package engine.gridobject;

import java.util.List;

import engine.Statistic;

public class RuleFollower extends GridObject {
	
	List<Statistic> myStats;
	List<Item> myItems;
	double mySpeed;
	double myDX=0;
	double myDY=0;
	List<String> myDialogue;
	List<Statistic> statList;
	public RuleFollower(int x, int y, double speed) {
		super(x,y);
		myStats = null;
		mySpeed = speed;
		myDialogue=null;
	}
	
	@Override
	public void move() {
		myX+=myDX;
		myY+=myDY;
	}
	
	
	public void addStatistic(Statistic stat) {
		myStats.add(stat);
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
	
	public void addStat(String name, int value, int maxValue){
		myStats.add(new Statistic(name,value,maxValue));
	}
	
	

}
