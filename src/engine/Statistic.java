package engine;

public abstract class Statistic implements Listable {
	
	String myName;
	double myValue;
	double myMaxValue;
	
	public Statistic(String name, int maxValue, int value) {
		myName = name;
		myValue = value;
		myMaxValue = maxValue;
	}
	
	public void increaseValue(double inc) {
		myValue += inc;
	}
	
	public void decreaseValue(double dec) {
		myValue -= dec;
	}

	@Override
	public abstract void display(); // write a method that will display it in a list fashion

}
