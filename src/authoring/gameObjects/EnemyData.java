package authoring.gameObjects;

import java.util.List;
import java.util.Map;

import Data.FileLister;
import util.Constants;

/**
 * Class that handles the GUI window and creation of enemies and players
 * @author Pritam M, Richard Cao, Davis Treybig, Jacob Lettie
 *
 */
public class EnemyData extends GridObjectData {

    private Map<String,Integer> myValues;
    private String[] myWeapons;
    private String myName;
    private int myMovement;
    private String[] myImages;
    private int myMoney;
    private int myExperience;
    
    public EnemyData(int x, int y, String image, String name, Map<String,Integer> startVals, String[] weps,
                     int movement,int money, int exp) {
    	super(x, y, image, name, startVals, weps, movement, Constants.ENEMY);

        myName = name;
        myValues = startVals;
        myWeapons = weps;
        myMovement = movement;
        myImages=createSpriteImages(image);
        myImage=image;
        myMoney = money;
        myExperience = exp;
        System.out.println(image);
    }

    public String getMyName(){return myName;}
    public Map<String,Integer> getMyValues(){return myValues;}
    public String[] getMyWeapons(){return myWeapons;}
    public int getMyMovement(){return myMovement;}
    public void setImages(String[] images){
    	myImages=images;
    }
    public String[] getImages(){
    	return myImages;
    }
    public int getMyMoney(){return myMoney;}
    public int getMyExperience(){return myExperience;}
    public void setWeapons(String[] weps) {
    	myWeapons = weps;
    }
    public void setMyMoney(int mon) {
    	myMoney = mon;
    }
    public void setMyExperience(int exp) {
    	myExperience = exp;
    }
}


