package authoring;

import java.util.List;
import java.util.Map;

import Data.FileLister;
import util.Constants;

/**
 * Created by Prit on 4/19/14.
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
        myImages=createSpriteImages();
        myMoney = money;
        myExperience = exp;
    }
   
    //accomodate Random Enemy constructor
    public EnemyData(int x, int y, String image, String name, Map<String, Integer> startVals, String[] weps) {}

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
<<<<<<< HEAD
    public int getMyMoney(){return myMoney;}
    public int getMyExperience(){return myExperience;}
}
=======
 }
>>>>>>> f43f60c3fc327495bd4af98c0a5af24a99a354a5
