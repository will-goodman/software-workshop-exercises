package com.willgoodman.entities.enemies.zombies;

import com.willgoodman.entities.enemies.WeaponCarrier;
import com.willgoodman.entities.weapons.Weapon;
import com.willgoodman.entities.enemies.BaseEnemy;

import java.util.ArrayList;

/**
 * Represents a Zombie
 * - A Zombie is a Basic Enemy with a decomposition rate, anger level, and the ability to carry weapons
 * - Zombies can carry up to two weapons
 * - Weapons cannot be dropped once they have been picked up
 * - The rate of decomposition is 0.5
 * - The initial anger level is zero
 * - The maximum anger level is 50
 *
 * @author Will Goodman
 */
public class Zombie extends BaseEnemy implements WeaponCarrier {

    private final static float RATE_OF_DECOMPOSITION = 0.5f;
    private final static float MIN_ANGER = 0f;
    private final static float MAX_ANGER = 50.0f;
    private final static int MAX_WEAPONS = 2;

    private float anger;
    private ArrayList<Weapon> weapons;


    /**
     * Constructor
     *
     * @param name String The zombie's name
     */
    public Zombie(String name) {
        super(name);
        this.anger = this.getInitialAnger();
        this.weapons = new ArrayList<>();
    }


    /**
     * Gets the rate of decomposition of the zombie
     *
     * @return The rate of decomposition
     */
    protected float getRateOfDecomposition() {
        return RATE_OF_DECOMPOSITION;
    }


    /**
     * Gets the max possible anger of the zombie
     *
     * @return The max possible anger level
     */
    protected float getMaxAnger() {
        return MAX_ANGER;
    }


    /**
     * Gets the initial anger level of the zombie
     *
     * @return The initial anger level
     */
    protected float getInitialAnger() {
        return MIN_ANGER;
    }


    /**
     * Gets the Zombie's anger
     *
     * @return The anger level
     */
    public float getAnger() {
        return this.anger;
    }


    /**
     * Sets the Zombie's anger
     * If the given value is outside the allowed values, the anger is either set to the minimum or maximum anger
     * Once an enemy is dead, its anger cannot be changed
     *
     * @param anger float the Zombie's new anger
     */
    public void setAnger(float anger) {
        if (!this.isDead()) {
            if (anger < MIN_ANGER) {
                this.anger = MIN_ANGER;
            } else if (anger > this.getMaxAnger()) {
                this.anger = this.getMaxAnger();
            } else {
                this.anger = anger;
            }
        }
    }


    /**
     * Gets a list of the weapons held by the Zombie
     *
     * @return The weapons held by the Zombie
     */
    @Override
    public ArrayList<Weapon> getWeapons() {
        return this.weapons;
    }


    /**
     * Add a new weapon to the zombie
     * Once a Zombie has a weapon it cannot drop it
     * Dead Zombies cannot pick up weapons
     *
     * @param weapon Weapon The new weapon
     */
    @Override
    public void addWeapon(Weapon weapon) {
        if (this.weapons.size() < MAX_WEAPONS && !this.isDead()) {
            weapons.add(weapon);
        }
    }


    /**
     * Decomposes the zombie by reducing its health
     */
    public void decompose() {
        this.setHealth(this.getHealth() - this.getRateOfDecomposition());
    }


    /**
     * Represents the zombie's attributes as a single string
     *
     * @return The zombie as a String
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(super.toString());
        stringBuilder.append(String.format("Anger = %.1f\n", this.anger));
        stringBuilder.append(String.format("Weapons = %s\n", this.weapons));

        return stringBuilder.toString();
    }

}
