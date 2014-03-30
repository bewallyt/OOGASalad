package engine.gridobject;

public class NPC extends RuleFollower {

	public NPC(int x, int y,double speed) {
		super(x,y,speed);
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
	public void moveBackAndForth(int xStart, int xEnd, int yStart, int yEnd){
		if(myX==xEnd)
			mySpeed *=-1;
		if(myX==xStart)
			mySpeed *=-1;
		myDX=mySpeed;
		
		if(myY==yEnd)
			mySpeed *=-1;
		if(myY==yStart)
			mySpeed *=-1;
		myDY=mySpeed;
	}

}


