package engine.gridobject;


public class Enemy extends NPC {
	protected boolean nearPlayer=false;
	private Player myPlayer;

	public Enemy(String image, double speed, int numTiles, Player player) {
		super(image,speed, numTiles);
		myPlayer = player;
	}
}
