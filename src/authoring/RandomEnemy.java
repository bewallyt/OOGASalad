package authoring;

import java.util.Map;

public class RandomEnemy extends PlayerData{


    public RandomEnemy(int x, int y, String image, String name, Map<String, Integer> startVals, String[] weps,
                       String[] its) {
        super(x, y, image, name, startVals, weps, its);
    }

    @Override
    public int getX(){return Integer.parseInt(null);}
    @Override
    public int getY(){return Integer.parseInt(null);}
    @Override
    public void init(){}
}
