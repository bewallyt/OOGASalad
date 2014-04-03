package engine.gridobject;

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
		System.out.println(getDistance(myX, myY, player.myX, player.myY));
		if(getDistance(myX, myY, player.myX, player.myY)<70 || nearPlayer){
			nearPlayer=true;
			if(myX<player.myX)
				myDX=Math.abs(mySpeed);
			else{myDX=-Math.abs(mySpeed);}

			if(myY<player.myY)
				myDY = Math.abs(mySpeed);
			else{myDY=-Math.abs(mySpeed);}

			System.out.println("near");
		}
	}

	private int getDistance(int x1, int y1, int x2, int y2){
		return (int) Math.sqrt(Math.pow(x2-x1, 2) + Math.pow(y2-y1,2));
	}

}
