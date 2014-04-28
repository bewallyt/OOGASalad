package authoring.gameObjects;


import java.util.Map;
/**
 * Class that handles data relevant to items
 * @author Pritam M, Richard Cao, Davis Treybig, Jacob Lettie
 *
 */
public class ItemData {

    private String itemName;
    private String itemImage;
    private Map<String,Integer> myItemValues;
    private int myPrice;
    private String myIdentity;

    public ItemData(String name, String image, Map<String,Integer> values, int price, String identity){
        itemName = name;
        itemImage = image;
        myItemValues = values;
        myPrice = price;
        myIdentity = identity;
    }

    public ItemData(String name, int price, String identity){
        itemName = name;
        myPrice = price;
        myIdentity = identity;
    }

    public String getItemName(){
            return itemName;
    }
    public String getItemImage() {return itemImage; }
    public Map<String,Integer> getMyItemValues() { return myItemValues; }
    public String getMyIdentity(){return myIdentity;}
    public int getMyPrice(){return myPrice;}
}
