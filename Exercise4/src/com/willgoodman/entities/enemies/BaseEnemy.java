package com.willgoodman.entities.enemies;

import com.willgoodman.entities.Entity;

/**
 * Represents a basic enemy
 * - All enemies have a name and health value
 * - Health is initially set to the maximum
 * - If the health value is zero then the enemy is dead
 * - The maximum health of a BaseEnemy is 100
 *
 * @author Will Goodman
 */
public class BaseEnemy implements Entity {


    private static final float MIN_HEALTH = 0f;
    private static final float MAX_HEALTH = 100.0f;

    private String name;
    private float health;


    /**
     * Constructor
     *
     * @param name The name of the enemy
     */
    public BaseEnemy(String name) {
        this.name = name;
        this.health = this.getMaxHealth();
    }


    /**
     * Gets the maximum health of the enemy
     *
     * @return The maximum health
     */
    protected float getMaxHealth() {
        return MAX_HEALTH;
    }


    /**
     * Gets the Enemy's name
     *
     * @return The Enemy's name
     */
    public String getName() {
        return this.name;
    }


    /**
     * Gets the health of the enemy
     *
     * @return The health of the enemy
     */
    public float getHealth() {
        return this.health;
    }


    /**
     * Sets the health of the enemy
     * If the given value is outside the allowed values, the health is either set to the minimum or maximum health value
     * Once an enemy is dead, its health cannot be changed
     *
     * @param health float The new health value
     */
    public void setHealth(float health) {
        if (!this.isDead()) {
            if (health < MIN_HEALTH) {
                this.health = MIN_HEALTH;
            } else if (health > this.getMaxHealth()) {
                this.health = this.getMaxHealth();
            } else {
                this.health = health;
            }
        }
    }

    /**
     * Checks if the enemy is dead or not
     *
     * @return True if the enemy is dead, false if the enemy is alive
     */
    public boolean isDead() {
        return this.health == MIN_HEALTH;
    }


    /**
     * Returns the enemy's attributes as a string
     *
     * @return The enemy's attributes in a single string
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(String.format("Name = %s\n", this.name));
        stringBuilder.append(String.format("Health = %.1f\n", this.health));

        return stringBuilder.toString();
    }
}
