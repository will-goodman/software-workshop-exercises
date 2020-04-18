package com.willgoodman.entities.enemies.zombies;


/**
 * Represents a Legendary Zombie
 * - Legendary Zombies are Zombies which are harder to defeat and can mutate
 * - The rate of decomposition is 0.05
 * - The maximum health is 500
 * - The maximum anger is 100
 * - The initial anger is 50
 *
 * @author Will Goodman
 */
public class LegendaryZombie extends Zombie {
    private static final float RATE_OF_DECOMPOSITION = 0.05f;
    private static final float MAX_ANGER = 100.0f;
    private static final float INITIAL_ANGER = 50.0f;
    private static final float MAX_HEALTH = 500.0f;
    

    /**
     * Constructor
     *
     * @param name String The name of the Legendary Zombie
     */
    public LegendaryZombie(String name) {
        super(name);
    }


    /**
     * Gets the rate of decomposition of the legendary zombie
     *
     * @return The rate of decomposition
     */
    @Override
    protected float getRateOfDecomposition() {
        return RATE_OF_DECOMPOSITION;
    }


    /**
     * Gets the max possible anger of the legendary zombie
     *
     * @return The max possible anger level
     */
    @Override
    protected float getMaxAnger() {
        return MAX_ANGER;
    }


    /**
     * Gets the initial anger level of the legendary zombie
     *
     * @return The initial anger level
     */
    @Override
    protected float getInitialAnger() {
        return INITIAL_ANGER;
    }


    /**
     * Gets the maximum health of the legendary zombie
     * @return The maximum health
     */
    @Override
    protected float getMaxHealth() {
        return MAX_HEALTH;
    }


    /**
     * Mutates the legendary zombie by setting its health and anger level to their maximum values
     * A dead zombie cannot mutate
     */
    public void mutate() {
        if(!this.isDead()) {
            this.setHealth(this.getMaxHealth());
            this.setAnger(this.getMaxAnger());
        }
    }

}
