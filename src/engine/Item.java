package engine;

public class Item extends GridObject {

	private String myName;
	
	public Item(double x, double y, String name) {
		super(x, y, 0, 0);
		myName = name;
	}

	public String getName() {
		return myName;
	}
	
}
