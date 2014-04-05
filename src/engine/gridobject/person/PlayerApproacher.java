package engine.gridobject.person;

public class PlayerApproacher extends Enemy{

	private Player myPlayer;
	
	public PlayerApproacher(String image, double speed, int numTilesWidth, int numTilesHeight,
			Player player) {
		super(image, speed, numTilesWidth, numTilesHeight, player);
		myPlayer = player;
	}
	
	@Override
	public void uniqueMove(){
			approachPlayer(myPlayer);
	}
	
	public void approachPlayer(Player player){
		if(getDistance(getX(), super.getY(), player.getX(), player.getY())<70 || isNearPlayer()){
			setIsNearPlayer(true);
			if(super.getX()<player.getX())
				setDX(Math.abs(getSpeed()));
			else{setDX(-Math.abs(getSpeed()));}

			if(super.getY()<player.getY())
				setDY(Math.abs(getSpeed()));
			else{setDY(-Math.abs(getSpeed()));}

			System.out.println("near");
		}
	}

	private int getDistance(int x1, int y1, int x2, int y2){
		return (int) Math.sqrt(Math.pow(x2-x1, 2) + Math.pow(y2-y1,2));
	}

}
