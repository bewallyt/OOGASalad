package engine.gridobject.item;

public class Weapon extends Item{

	public Weapon(int x, int y, String image, String name) {
		super(x, y, image, name);	
		super.setDoesHarm(true);
	}

	@Override
	public void useItem() {
		// TODO Auto-generated method stub
		
	}

}
