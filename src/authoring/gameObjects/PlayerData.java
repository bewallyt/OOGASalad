package authoring.gameObjects;

import java.util.Map;
/**
 * Class that holds information relevant to a player
 * @author Pritam M, Richard Cao, Davis Treybig, Jacob Lettie
 *
 */
public class PlayerData extends EnemyData{

    private String[] myItems;
    
    public PlayerData(int x, int y, String image, String name, Map<String,Integer> startVals, String[] weps,
                      String[] its) {
        super(x,y,image,name,startVals,weps, 0,0,0);
        myItems = its;
        myImage=image;

    }
   /**
    * Returns the player's items
    * @return String[] of item names
    */
    public String[] getMyItems(){ return myItems;}
    
   /**
    * Sets the players items
    * @param items String[] of item names
    */
    public void setItems(String[] items) {
    	myItems = items;
    }

}
