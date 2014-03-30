package engine;

public class Item extends GridObject {

	private String myName;
	
	public Item(double x, double y, String name) {
		super(x, y);
		myName = name;
	}

	public String getName() {
		return myName;
	}
	
	public void changeStatistic(Statistic statistic, int amountToChange){
		statistic.changeValue(amountToChange);
	}
	
}
