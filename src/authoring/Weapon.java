package authoring;

import java.util.List;

public class Weapon {

    private String myName;
    private List<Attacks> myAttacks;
    private String myOptional;
    private int myEffectValue;

    public Weapon(String name, List<Attacks> atta, String optional, int effectValue){
        myName = name;
        myAttacks = atta;
        myOptional = optional;
        myEffectValue = effectValue;
    }

    public String getMyName(){ return myName;}
    public List<Attacks> getMyAttacks(){ return myAttacks;}
    public String getMyOptional(){ return myOptional;}
    public int getMyEffectValue(){ return myEffectValue;}
}
