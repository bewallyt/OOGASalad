package authoring;

/**
 * @ Pritam M.
 * */

import java.util.Map;

public class Item {

    private String itemName;
    private String itemImage;
    private Map<String,Integer> myItemValues;

    public Item(String name, String image, Map<String,Integer> values){
        itemName = name;
        itemImage = image;
        myItemValues = values;
    }

    public Item(String name){
        itemName = name;
    }

    public String getItemName(){
            return itemName;
    }
    public String getItemImage() {return itemImage; }
    public Map<String,Integer> getMyItemValues() { return myItemValues; }
}
