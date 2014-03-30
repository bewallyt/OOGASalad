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
	
	public void changeValue(int amountToChange){
		myValue+=amountToChange;
	}
	
	public double getValue(){
		return myValue;
	}

}
