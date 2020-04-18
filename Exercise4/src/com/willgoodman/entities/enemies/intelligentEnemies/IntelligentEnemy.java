package com.willgoodman.entities.enemies.intelligentEnemies;

import com.willgoodman.entities.enemies.BaseEnemy;

/**
 * Represents an intelligent enemy
 * - An Intelligent Enemy is a Basic Enemy with an intelligence value
 * - The maximum intelligence is 50
 * - The initial intelligence is 25
 * - When the health is increased, intelligence is increased by 1
 * - When the health is decreased, intelligence is decreased by 1
 *
 * @author Will Goodman
 */
public class IntelligentEnemy extends BaseEnemy {

    private static final float MIN_INTELLIGENCE = 0f;
    private static final float MAX_INTELLIGENCE = 50.0f;
    private static final float INITIAL_INTELLIGENCE = 25.0f;
    private static final float HEALTH_INTELLIGENCE_EFFECT = 1.0f;

    private float intelligence;


    /**
     * Constructor
     *
     * @param name String The name of the intelligent enemy
     */
    public IntelligentEnemy(String name) {
        super(name);

        this.intelligence = INITIAL_INTELLIGENCE;
    }


    /**
     * Gets the value the intelligence changes by when the Enemy's health is changed
     *
     * @return The amount to change the intelligence by
     */
    protected float getHealthIntelligenceEffect() {
        return HEALTH_INTELLIGENCE_EFFECT;
    }


    /**
     * Sets the health of the enemy
     * If the given value is outside the allowed values, the health is either set to the minimum or maximum health value
     * Once an enemy is dead, its health cannot be changed
     *
     * The enemy's intelligence is increased/decreased depending on whether the health is increased/decreased
     *
     * @param health float The new health value
     */
    @Override
    public void setHealth(float health) {
        if (!this.isDead()) {
            float oldHealth = this.getHealth();
            super.setHealth(health);

            if (health < oldHealth) {
                this.setIntelligence(this.getIntelligence() - this.getHealthIntelligenceEffect());
            } else if (health > oldHealth) {
                this.setIntelligence(this.getIntelligence() + this.getHealthIntelligenceEffect());
            }
        }
    }


    /**
     * Gets the object's intelligence
     *
     * @return The enemy's intelligence
     */
    public float getIntelligence() {
        return this.intelligence;
    }


    /**
     * Sets the enemy's intelligence
     * If the given value is outside the allowed values, the intelligence is either set to the minimum or maximum intelligence
     * Once an enemy is dead, its intelligence cannot be changed
     *
     * @param intelligence float The new intelligence
     */
    public void setIntelligence(float intelligence) {
        if (!this.isDead()) {
            if (intelligence < MIN_INTELLIGENCE) {
                this.intelligence = MIN_INTELLIGENCE;
            } else if (intelligence > MAX_INTELLIGENCE) {
                this.intelligence = MAX_INTELLIGENCE;
            } else {
                this.intelligence = intelligence;
            }
        }
    }


    /**
     * Represents the object's attributes as a single string
     *
     * @return The object as a String
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(super.toString());
        stringBuilder.append(String.format("Intelligence = %.1f\n", this.intelligence));

        return stringBuilder.toString();
    }

}
