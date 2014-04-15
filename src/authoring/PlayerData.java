package authoring;


public class PlayerData {
	private Boolean isAnim;
	private String[] myAnimImages;
	private String myImage;
	private int myXStart;
	private int myYStart;
	
	public PlayerData(Boolean anim, String imName, String[] animImage, int x, int y){
        isAnim = anim;
        myImage = imName;
        myAnimImages = animImage;
        myXStart = x;
        myYStart = y;
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
}
