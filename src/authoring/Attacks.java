package authoring;

public class Attacks {

    private String myName;
    private int mySpeed;
    private int myDamage;
    private String affectAttribute;
    private int affectValue;
    private boolean affectWho;
    public Attacks(String name, int speed, int damage, String selected, int value, boolean affect){
        myName = name;
        mySpeed = speed;
        myDamage = damage;
        affectAttribute = selected;
        affectValue = value;
        affectWho = affect;

    }

    public String getMyName(){return myName;}
    public int getMySpeed(){return mySpeed;}
    public int getMyDamage(){return myDamage;}
    public String getAffectAttribute(){return affectAttribute;}
    public int getAffectValue(){return affectValue;}
    public boolean getAffectWho(){return affectWho;}
}
