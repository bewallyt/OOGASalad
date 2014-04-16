package authoring;


public class PlayerData {
	private Boolean isAnim;
	private String[] myAnimImages;
	private String myImage;
	private int myXStart;
	private int myYStart;
    private int pWidth;
    private int pHeight;
    private int speed;
	
	public PlayerData(Boolean anim, String imName, String[] animImage, int x, int y){
        isAnim = anim;
        myImage = imName;
        myAnimImages = animImage;
        myXStart = x;
        myYStart = y;
        pWidth = 36;
        pHeight = 36;
        speed = 10;

	}
	
	public Boolean isAnimated(){
		return isAnim;
	}
	public String getMyImage(){
		return myImage;
	}
	public String[] getMyAnimImages(){
		return myAnimImages;
	}
	
	public void setMyImage(String im){
		myImage = im;
		isAnim = false;
	}
	
	public void setMyAnimImages(String[] ims){
		myAnimImages = ims;
		isAnim = true;
	}

    public void setStartLocation(int xcoor, int ycoor){
        myXStart = xcoor;
        myYStart = ycoor;
    }

    public int getMyXStart(){ return myXStart; }
    public int getMyYStart(){ return myYStart; }
    public int getpWidth(){ return pWidth; }
    public int getpHeight(){ return pHeight; }
    public int getSpeed(){ return speed; }
}
