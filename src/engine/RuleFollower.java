package engine;

import java.util.List;

public class RuleFollower extends GridObject {
	
	List<Statistic> myStats;

	public RuleFollower(double x, double y, double dx, double dy) {
		super(x,y,dx,dy);
		myStats = null;
	}
	
	public void addStatistic(Statistic stat) {
		myStats.add(stat);
	}
	
	
	

}
