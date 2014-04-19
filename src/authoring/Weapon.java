package authoring;

import java.util.List;

public class Weapon {

    private String myName;
    private List<Attacks> myAttacks;
    private String myOptional;
    private int myEffectValue;
    private int mySpeed;
    private int myDamage;

    public Weapon(String name, int speed, int damage, List<Attacks> attacks, String optional, int effectValue){
        myName = name;
        myAttacks = attacks;
        myOptional = optional;
        myEffectValue = effectValue;
        mySpeed = speed;
        myDamage = damage;
    }

    public String getMyName(){ return myName;}
    public List<Attacks> getMyAttacks(){ return myAttacks;}
    public String getMyOptional(){ return myOptional;}
    public int getMyEffectValue(){ return myEffectValue;}
    public int getMySpeed(){ return mySpeed;}
    public int getMyDamage(){ return myDamage;}
}
