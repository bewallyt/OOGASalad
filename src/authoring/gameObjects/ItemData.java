package authoring.gameObjects;

/**
 * @ Pritam M.
 * */

import java.util.Map;

public class ItemData {

    private String itemName;
    private String itemImage;
    private Map<String,Integer> myItemValues;
    private int myPrice;

    public ItemData(String name, String image, Map<String,Integer> values, int price){
        itemName = name;
        itemImage = image;
        myItemValues = values;
        myPrice = price;
    }

    public ItemData(String name, int price){
        itemName = name;
        myPrice = price;
    }

    public String getItemName(){
            return itemName;
    }
    public String getItemImage() {return itemImage; }
    public Map<String,Integer> getMyItemValues() { return myItemValues; }
}
