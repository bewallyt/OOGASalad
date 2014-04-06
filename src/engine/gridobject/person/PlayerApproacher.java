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
		if(howFarFromPlayer()<20){	
			if(getX()<player.getX())
				setDX(Math.abs(getSpeed()));
			else{setDX(-Math.abs(getSpeed()));}

			if(getY()<player.getY())
				setDY(Math.abs(getSpeed()));
			else{setDY(-Math.abs(getSpeed()));}
		}
	}

	

}
