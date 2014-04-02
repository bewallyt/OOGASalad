package engine.gridobject.item;

import engine.Statistic;
import engine.world.Tile;

public class StatBuffer extends Item {

	private Statistic myStatistic;
	private int myAmountToChange;

	public StatBuffer(String image, String name, Statistic statistic,
			int amountToChange, int numTiles) {
		super(image, name, numTiles);
		myStatistic = statistic;
		myAmountToChange = amountToChange;
	}

	@Override
	public void useItem() {
		changeStatistic(myStatistic, myAmountToChange);
	}

}
