package engine.gridobject.person;


public class Enemy extends NPC {
	private boolean nearPlayer=false;
	private Player myPlayer;

	public Enemy(String image, double speed, int numTilesWidth, int numTilesHeight, Player player) {
		super(image,speed, numTilesWidth, numTilesHeight);
		myPlayer = player;
	}

	public boolean isNearPlayer() {
		return nearPlayer;
	}

	public void setIsNearPlayer(boolean nearPlayer) {
		this.nearPlayer = nearPlayer;
	}
}
