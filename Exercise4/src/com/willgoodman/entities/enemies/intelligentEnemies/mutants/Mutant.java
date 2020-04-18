package com.willgoodman.entities.enemies.intelligentEnemies.mutants;
import com.willgoodman.entities.enemies.WeaponCarrier;
import com.willgoodman.entities.weapons.Weapon;
import com.willgoodman.entities.enemies.intelligentEnemies.IntelligentEnemy;

import java.util.ArrayList;


/**
 * Represents a Mutant
 * - A Mutant is an Intelligent Enemy which can carry weapons
 * - The maximum number of weapons is four
 * - Weapons can never be dropped
 * - Whenever health is increased, intelligence is increased by 2
 * - Whenever health is decreased, intelligence is decreased by 2
 *
 * @author Will Goodman
 */
public class Mutant extends IntelligentEnemy implements WeaponCarrier {

    private static final float HEALTH_INTELLIGENCE_EFFECT = 2.0f;
    private static final int MAX_WEAPONS = 4;

    private ArrayList<Weapon> weapons;

    /**
     * Constructor
     *
     * @param name String The name of the Mutant
     */
    public Mutant(String name) {
        super(name);
        this.weapons = new ArrayList<>();
    }


    /**
     * Gets the value the intelligence changes by when the Mutant's health is changed
     *
     * @return The amount to change the intelligence by
     */
    @Override
    protected float getHealthIntelligenceEffect() {
        return HEALTH_INTELLIGENCE_EFFECT;
    }


    /**
     * Gets a list of the weapons held by the Mutant
     *
     * @return The weapons held by the Mutant
     */
    @Override
    public ArrayList<Weapon> getWeapons() {
        return this.weapons;
    }


    /**
     * Add a new weapon to the Mutant
     * Dead mutants cannot pick up new weapons
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
     * Represents the Mutant's attributes together as a single String
     *
     * @return The Mutant's attributes as a String
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(super.toString());
        stringBuilder.append(String.format("Weapons = %s\n", this.weapons));

        return stringBuilder.toString();
    }

}
