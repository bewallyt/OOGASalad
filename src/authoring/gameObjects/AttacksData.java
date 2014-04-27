package authoring.gameObjects;

/**
 * @ Pritam M.
 * */

public class AttacksData {

    private String myName;
    private int mySpeed;
    private int myDamage;
    private String affectAttribute;
    private int affectValue;
    private boolean affectPlayer;
    
    public AttacksData(String name, int speed, int damage, String selected, int value, boolean affect){
        myName = name;
        mySpeed = speed;
        myDamage = damage;
        affectAttribute = selected;
        affectValue = value;
        affectPlayer = affect;

    }

    public String getMyName(){return myName;}
    public int getMySpeed(){return mySpeed;}
    public int getMyDamage(){return myDamage;}
    public String getAffectAttribute(){return affectAttribute;}
    public int getAffectValue(){return affectValue;}
    public boolean getAffectPlayer(){return affectPlayer;}
}
