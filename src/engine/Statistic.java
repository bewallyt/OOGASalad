package engine;

public class Statistic implements Listable {

	String myName;
	int myValue;

	public Statistic(String name, int value) {
		myName = name;
		myValue = value;
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
	
	public void incrementByOne(){
		myValue++;
	}
	
	@Override
	public void display() {
		// TODO Auto-generated method stub

	}

	//	@Override
	//	public abstract void display(); // write a method that will display it in a
	//									// list fashion

}
