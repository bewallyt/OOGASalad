package engine.battle;

import engine.gridobject.person.Person;

public class Effect {
	private String myStatistic;
	private Person myPerson;
	private int myAmountToChange;
	private boolean myAffectsSelf;

	public Effect(String statistic, boolean affectsSelf, int amountToChange) {
		myStatistic = statistic;
		myAffectsSelf = affectsSelf;
		myAmountToChange = amountToChange;
	}

	public void doEffect(Person self, Person other) {
		if (myAffectsSelf)
			self.getStatsMap().get(myStatistic).changeValue(myAmountToChange);
		else {
			other.getStatsMap().get(myStatistic).changeValue(myAmountToChange);
		}
	}

	public String toString() {
		if (myAffectsSelf)
			return " Attacker's " + myStatistic + " changed by "
					+ myAmountToChange;
		else {
			return " Victim's " + myStatistic + " changed by "
					+ myAmountToChange;
		}
	}

	public String toStringData() {
		if (myAffectsSelf)
			return " Attacker's " + myStatistic + " changes by "
					+ myAmountToChange;
		else {
			return " Victim's " + myStatistic + " changes by "
					+ myAmountToChange;
		}

	}
}
