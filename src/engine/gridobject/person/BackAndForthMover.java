package engine.gridobject.person;

public class BackAndForthMover extends Enemy {
	private int myXStart;
	private int myYStart;
	private int myXEnd;
	private int myYEnd;
	public BackAndForthMover(String image, double speed, int numTilesWidth, int numTilesHeight,
			int xStart, int xEnd, int yStart, int yEnd, Player player) {
		super(image, speed, numTilesWidth, numTilesHeight,player);
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
		if(getX()==myXEnd)
			setSpeed(getSpeed() * -1);
		if(getX()==myXStart)
			setSpeed(getSpeed() * -1);
		if(myXStart!=0 && myXEnd!=0)setDX(getSpeed());

		if(getY()==myYEnd)
			setSpeed(getSpeed() * -1);
		if(getY()==myYStart)
			setSpeed(getSpeed() * -1);
		if(myYStart!=0 && myYEnd!=0)setDY(getSpeed());
	}

}
