package engine;

public class Statistic {
	
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

}
