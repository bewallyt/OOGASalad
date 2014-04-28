package authoring.gameObjects;


import java.util.Map;
/**
 * Class that holds information needed to create a RandomEnemy
 * @author Pritam M, Richard Cao, Davis Treybig, Jacob Lettie
 *
 */
public class RandomEnemy extends EnemyData{

    public RandomEnemy(int x, int y, String image, String name, Map<String, Integer> startVals, String[] weps,
                       int movement, int money, int exp) {
        super(x, y, image, name, startVals, weps, movement, money, exp);
    }

    /**
     * Returns the X value of the random enemy (always 0)
     */
    @Override
    public int getX(){return 0;}
    /**
     * Returns the y value of the random enemy (always 0)
     */
    @Override
    public int getY(){return 0;}
    /**
     * Returns the movement pattern of the random enemy (always 0)
     */
    @Override
    public int getMyMovement(){return 0;}
}
