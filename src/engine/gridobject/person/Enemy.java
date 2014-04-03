package engine.gridobject.person;


public class Enemy extends NPC {
	protected boolean nearPlayer=false;
	private Player myPlayer;

	public Enemy(String image, double speed, int numTilesWidth, int numTilesHeight, Player player) {
		super(image,speed, numTilesWidth, numTilesHeight);
		myPlayer = player;
	}
}
