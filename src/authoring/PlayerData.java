package authoring;


public class PlayerData {
	private Boolean isAnim = false;
	private String[] myAnimImages;
	private String myImage;
	private int myXStart;
	private int myYStart;
	
	public PlayerData(){
		
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
}
