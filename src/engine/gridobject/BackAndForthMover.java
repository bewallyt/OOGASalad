package engine.gridobject;

public class BackAndForthMover extends Enemy {
	private int myXStart;
	private int myYStart;
	private int myXEnd;
	private int myYEnd;
	public BackAndForthMover(String image, double speed, int numTiles,
			int xStart, int xEnd, int yStart, int yEnd, Player player) {
		super(image, speed, numTiles,player);
		myXStart=xStart;
		myYStart=yStart;
		myXEnd=xEnd;
		myYEnd=yEnd;
	}
	@Override
	public void uniqueMove(){
			moveBackAndForth();
	}

	/**
	 * Move back and forth from xStart to xEnd and yStart to yEnd. 
	 * The starting positions must be less than the ending positions.
	 *
	 * @param xStart the x starting pos
	 * @param xEnd the x ending pos
	 * @param yStart the y starting pos
	 * @param yEnd the y ending pos
	 */
	public void moveBackAndForth(){
		if(myX==myXEnd)
			mySpeed *=-1;
		if(myX==myXStart)
			mySpeed *=-1;
		if(myXStart!=0 && myXEnd!=0)myDX=mySpeed;

		if(myY==myYEnd)
			mySpeed *=-1;
		if(myY==myYStart)
			mySpeed *=-1;
		if(myYStart!=0 && myYEnd!=0)myDY=mySpeed;
	}

}
