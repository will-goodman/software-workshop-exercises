package com.willgoodman.entities.enemies.intelligentEnemies.ghouls;

import com.willgoodman.entities.enemies.intelligentEnemies.IntelligentEnemy;

/**
 * Represents a Ghoul
 * - A Ghoul is an Intelligent Enemy with a putridity level
 * - Putridity can increase, although never decrease
 * - The initial putridity level is zero
 * - The maximum putridity is 1
 * - Putridity increases in increments of 0.1
 * - The maximum health of a Ghoul is 150
 *
 * @author Will Goodman
 */
public class Ghoul extends IntelligentEnemy {

    private static final float MAX_HEALTH = 150.0f;
    private static final float MIN_PUTRIDITY_LEVEL = 0f;
    private static final float MAX_PUTRIDITY_LEVEL = 1.0f;
    private static final float PUTRIDITY_INCREMENT = 0.1f;

    private float putridity;

    /**
     * Constructor
     *
     * @param name String The name of the Ghoul
    */
    public Ghoul(String name) {
        super(name);
        this.putridity = MIN_PUTRIDITY_LEVEL;
    }


    /**
     * Gets the maximum health value of the Ghoul
     *
     * @return The Ghoul's maximum possible health
     */
    @Override
    protected float getMaxHealth() {
        return MAX_HEALTH;
    }


    /**
     * Gets the Ghoul's putridity
     *
     * @return The Ghoul's putridity
     */
    public float getPutridity() {
        return this.putridity;
    }


    /**
     * Increases the Ghoul's putridity in increments
     * If the the maximum putridity is reached, the Ghoul's health is set to the maximum. This is a one-off increase
     *
     * A dead Ghoul's putridity cannot be increased
     */
    public void increasePutridity() {
        if (!this.isDead() && (this.putridity < MAX_PUTRIDITY_LEVEL)) {
            this.putridity += PUTRIDITY_INCREMENT;

            if (this.putridity > MAX_PUTRIDITY_LEVEL) {
                this.putridity = MAX_PUTRIDITY_LEVEL;
                this.setHealth(this.getMaxHealth());
            }
        }
    }


    /**
     * Formats the ghoul's attributes as a single String
     *
     * @return The Ghoul represented as a String
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(super.toString());
        stringBuilder.append(String.format("Putridity = %.1f\n", this.putridity));

        return stringBuilder.toString();
    }
}
