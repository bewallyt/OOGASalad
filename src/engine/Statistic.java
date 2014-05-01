package engine;

public class Statistic {

	String myName;
	int myValue;
	int myMaxValue;

	public Statistic(String name, int value, int maxValue) {
		myName = name;
		myValue = value;
		myMaxValue=maxValue;
	}

	public void changeValue(int amountToChange) {
		if(myValue+amountToChange<=myMaxValue)
			myValue += amountToChange;
		else{
			myValue=myMaxValue;
		}
	}
	public void setValue(int value){
		myValue=value;
	}

	public int getValue() {
		return myValue;
	}

	public String getName(){
		return myName;
	}
	
	public int getMaxValue(){
		return myMaxValue;
	}
	
	public void setToMax(){
		myValue=myMaxValue;
	}
}
