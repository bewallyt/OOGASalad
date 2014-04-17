package authoring;

public class Attacks {

    private String myName;
    private int mySpeed;
    private int myDamage;
    public Attacks(String name, int speed, int damage){
        myName = name;
        mySpeed = speed;
        myDamage = damage;
    }

    public String getMyName(){return myName;}
    public int getMySpeed(){return mySpeed;}
    public int getMyDamage(){return myDamage;}
}
