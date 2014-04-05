package engine;

public class Statistic implements Listable {

	String myName;
	int myValue;
	int myMaxValue;

	public Statistic(String name, int maxValue, int value) {
		myName = name;
		myValue = value;
		myMaxValue = maxValue;
	}

	public void changeValue(int amountToChange) {
		myValue += amountToChange;
	}

	public int getValue() {
		return myValue;
	}

	public String getName(){
		return myName;
	}
	
	@Override
	public void display() {
		// TODO Auto-generated method stub

	}

	//	@Override
	//	public abstract void display(); // write a method that will display it in a
	//									// list fashion

}
