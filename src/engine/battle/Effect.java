package engine.battle;

import engine.gridobject.person.Person;

public class Effect {
	String myStatistic;
	Person myPerson;
	int myAmountToChange;
	public Effect(String statistic, Person person, int amountToChange){
		myStatistic=statistic;
		myPerson=person;
		myAmountToChange=amountToChange;
	}
	
	public void doEffect(){
		myPerson.getStatsMap().get(myStatistic).changeValue(myAmountToChange);
	}
	public String toString(){
		return " " + myPerson.toString() + "'s " + myStatistic + " changed by " + myAmountToChange; 
	}
}
