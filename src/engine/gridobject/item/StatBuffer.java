package engine.gridobject.item;

import java.util.List;

import engine.Statistic;

public class StatBuffer extends Item {

	private Statistic myStatistic;
	private int myAmountToChange;

	public StatBuffer(int x, int y, String name, Statistic statistic,
			int amountToChange) {
		super(x, y, name);
		myStatistic = statistic;
		myAmountToChange = amountToChange;
	}

	@Override
	public void useItem() {
		changeStatistic(myStatistic, myAmountToChange);
	}

}
