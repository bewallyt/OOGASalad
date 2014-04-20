package authoring;


import java.util.List;
import java.util.Map;

public class PlayerData extends EnemyData{

    private String[] myItems;

    public PlayerData(int x, int y, String image, String name, Map<String,Integer> startVals, String[] weps,
                      String[] its) {
        super(x, y, image, name, startVals, weps);
        myItems = its;
    }

    public String[] getMyItems(){ return myItems;}

}
