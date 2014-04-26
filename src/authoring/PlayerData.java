package authoring;


import java.util.List;
import java.util.Map;

public class PlayerData extends EnemyData{

    private String[] myItems;
    
    public PlayerData(int x, int y, String image, String name, Map<String,Integer> startVals, String[] weps,
                      String[] its) {
        super(x,y,image,name,startVals,weps, 0,0,0);
        myItems = its;

    }
   
    public String[] getMyItems(){ return myItems;}

}
