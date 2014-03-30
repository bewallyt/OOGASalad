package engine;

public abstract class Item extends GridObject implements Listable {

	private String myName;
	
	public Item(double x, double y, String name) {
		super(x, y, 0, 0);
		myName = name;
	}

	public String getName() {
		return myName;
	}

	public abstract void useItem();
	
	@Override
	public void display() {
		// write method to write name / picture to a Menu
		
	}
	
}
