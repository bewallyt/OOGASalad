package authoring.gameObjects;

/**
 * @ Pritam M.
 * */

import java.util.Map;

public class ItemData {

    private String itemName;
    private String itemImage;
    private Map<String,Integer> myItemValues;

    public ItemData(String name, String image, Map<String,Integer> values){
        itemName = name;
        itemImage = image;
        myItemValues = values;
    }

    public ItemData(String name){
        itemName = name;
    }

    public String getItemName(){
            return itemName;
    }
    public String getItemImage() {return itemImage; }
    public Map<String,Integer> getMyItemValues() { return myItemValues; }
}
