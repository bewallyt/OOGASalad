package engine;

public class Statistic implements Listable {

	String myName;
	double myValue;
	double myMaxValue;

	public Statistic(String name, int maxValue, int value) {
		myName = name;
		myValue = value;
		myMaxValue = maxValue;
	}

	public void changeValue(int amountToChange) {
		myValue += amountToChange;
	}

	public double getValue() {
		return myValue;
	}

	@Override
	public void display() {
		// TODO Auto-generated method stub
		
	}

//	@Override
//	public abstract void display(); // write a method that will display it in a
//									// list fashion

}
