package authoring;

public class Item {

    private String itemName;
    private int mySpeed;
    private int myAttack;
    private int myDefense;

    public Item(String name, int speed, int attack, int defense){
        itemName = name;
        mySpeed = speed;
        myAttack = attack;
        myDefense = defense;

    }

    public String getItemName(){
            return itemName;
    }

    public int getMySpeed(){
        return mySpeed;
    }

    public int getMyAttack(){
        return myAttack;
    }

    public int getMyDefense() { return myDefense; }
}
