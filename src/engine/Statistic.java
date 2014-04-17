package engine;

public class Statistic implements Listable {

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

	public int getValue() {
		return myValue;
	}

	public String getName(){
		return myName;
	}
	
	public int getMaxValue(){
		return myMaxValue;
	}
	
	@Override
	public void display() {
		// TODO Auto-generated method stub

	}
	public void setToMax(){
		myValue=myMaxValue;
	}

	//	@Override
	//	public abstract void display(); // write a method that will display it in a
	//									// list fashion

}
