package authoring;

public class Item {

    private String itemName;
    private int xlocation;
    private int ylocation;

    public Item(String name){
        itemName = name;
    }

    public Item(String name, int xloc, int yloc){
        itemName = name;
        xlocation = xloc;
        ylocation = yloc;
    }

    public String getItemName(){
            return itemName;
    }

    public int getXlocation(){
        return xlocation;
    }

    public int getYlocation(){
        return ylocation;
    }
}
