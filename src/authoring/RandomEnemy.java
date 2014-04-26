package authoring;

import java.util.Map;

public class RandomEnemy extends EnemyData{

    public RandomEnemy(int x, int y, String image, String name, Map<String, Integer> startVals, String[] weps,
                       int movement, int money, int exp) {
        super(x, y, image, name, startVals, weps, movement, money, exp);
    }

    @Override
    public int getX(){return Integer.parseInt(null);}
    @Override
    public int getY(){return Integer.parseInt(null);}
    @Override
    public void init(){}
    @Override
    public int getMyMovement(){return Integer.parseInt(null);}
}
