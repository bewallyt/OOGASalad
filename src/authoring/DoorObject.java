package authoring;

public class DoorObject extends GridObject {
 
	
	private int toX;
	private int toY;
	private String toMap;
	
	public DoorObject(int x, int y, String image, int toX, int toY, String toMap) {
		super(x,y,image);
		this.toX=toX;
		this.toY=toY;
		this.toMap=toMap;
	}
	public int getToX(){
		return toX;
	}
	public int getToY(){
		return toY;
	}
	public String toMap(){
		return toMap;
	}

}
