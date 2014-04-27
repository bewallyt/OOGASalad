package authoring.gameObjects;

/**
 * @ Pritam M.
 * */

import java.util.Map;

public class RandomEnemy extends EnemyData{

    public RandomEnemy(int x, int y, String image, String name, Map<String, Integer> startVals, String[] weps,
                       int movement, int money, int exp) {
        super(x, y, image, name, startVals, weps, movement, money, exp);
    }

    @Override
    public int getX(){return 0;}
    @Override
    public int getY(){return 0;}
    @Override
    public void init(){}
    @Override
    public int getMyMovement(){return 0;}
}
