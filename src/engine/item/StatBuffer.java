package engine.item;

import engine.Statistic;
import engine.world.Tile;

public class StatBuffer extends Item {

	private Statistic myStatistic;
	private int myAmountToChange;

	public StatBuffer(String image, String name, Statistic statistic,int amountToChange) {
		super(image, name);
		myStatistic = statistic;
		myAmountToChange = amountToChange;
	}

	@Override
	public void useItem() {
		changeStatistic(myAmountToChange);
		
	}
	public void changeStatistic(int amountToChange){
		myStatistic.changeValue(amountToChange);
		
	}
}
