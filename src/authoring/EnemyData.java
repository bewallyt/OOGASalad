package authoring;

import java.util.ArrayList;
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

    public EnemyData(int x, int y, String image, String name, Map<String,Integer> startVals, String[] weps,
                     int movement) {
    	super(x, y, image, Constants.ENEMY);
        myName = name;
        myValues = startVals;
        myWeapons = weps;
        myMovement = movement;
        init();
    }
    
    public EnemyData(List<Object> arguments) {
    	super(arguments);
    	myName = (String) arguments.get(Constants.NAME_CONST);
    	myValues = (Map<String, Integer>) arguments.get(4);
    	myWeapons = (String[]) arguments.get(5);
    }
    
    private List<Object> makeArguments() {
    	List<Object> blah = new ArrayList<Object>();
    	return null;
    }

    public String getMyName(){return myName;}
    public Map<String,Integer> getMyValues(){return myValues;}
    public String[] getMyWeapons(){return myWeapons;}
}
