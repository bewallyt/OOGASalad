package authoring;

import java.util.Map;

/**
 * Created by Prit on 4/19/14.
 */
public class EnemyData extends GridObjectData {

    private Map<String,Integer> myValues;
    private String[] myWeapons;
    private String myName;

    public EnemyData(int x, int y, String image, String name, Map<String,Integer> startVals, String[] weps) {
        super(x, y, image);
        myName = name;
        myValues = startVals;
        myWeapons = weps;
        init();
    }

    public String getMyName(){return myName;}
    public Map<String,Integer> getMyValues(){return myValues;}
    public String[] getMyWeapons(){return myWeapons;}
}
