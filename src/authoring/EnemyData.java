package authoring;

import java.util.List;
import java.util.Map;

import util.Constants;

/**
 * Created by Prit on 4/19/14.
 */
public class EnemyData extends GridObjectData {

    private Map<String,Integer> myValues;
    private String[] myWeapons;
    private String myName;
    private int myMovement;
    
    public EnemyData(int x, int y, String image, String name, Map<String,Integer> startVals, String[] weps, int movement) {
    	super(x, y, image, name, startVals, weps, movement, Constants.ENEMY);

        myName = name;
        myValues = startVals;
        myWeapons = weps;
        myMovement = movement;
    }

    public EnemyData() {
        myMovement=Integer.parseInt(null);
    }

    public String getMyName(){return myName;}
    public Map<String,Integer> getMyValues(){return myValues;}
    public String[] getMyWeapons(){return myWeapons;}
    public int getMyMovement(){return myMovement;}
}
