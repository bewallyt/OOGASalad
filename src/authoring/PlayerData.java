package authoring;


import java.util.List;
import java.util.Map;

public class PlayerData {
	private Boolean isAnim;
	private String[] myAnimImages;
	private String myImage;
	private int myXStart;
	private int myYStart;
    private Map<String,Integer> myPlayerValues;
    private String[] myWeapons;
    private String[] myItems;
	
	public PlayerData(String charName, int x, int y, Map<String, Integer> startVals,
    String[] weps, String[] its){

        myImage = charName;

        myXStart = x;
        myYStart = y;

        myPlayerValues = startVals;
        myWeapons = weps;
        myItems = its;

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
    public Map<String,Integer> getMyPlayerValues(){ return myPlayerValues;}
    public String[] getMyWeapons(){ return  myWeapons;}
    public String[] getMyItems(){ return myItems;}

}
