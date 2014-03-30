package engine;

import java.util.List;

public class RuleFollower extends GridObject {
	
	List<Statistic> myStats;
	List<Item> myItems;

	public RuleFollower(double x, double y, double dx, double dy) {
		super(x,y,dx,dy);
		myStats = null;
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
	
	
	

}
