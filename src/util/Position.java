package util;

/**
 * @author Peter Yom
 * Makes it easier to contain points
 */
public class Position {

	private double xPosition;
	private double yPosition;

	public Position(double x, double y) {
		this.xPosition = x;
		this.yPosition = y;
	}

	public double getX() {
		return xPosition;
	}

	public double getY() {
		return yPosition;
	}
	
	public void setX(double x) {
		xPosition = x;
	}
	
	public void setY(double y) {
		yPosition = y;
	}

}
