package com.willgoodman.entities.weapons;

import com.willgoodman.entities.Entity;

/**
 * Represents a weapon
 * - A weapon has a type
 *
 * @author Will Goodman
 */
public class Weapon implements Entity {

    private String type;


    /**
     * Constructor
     *
     * @param type String The weapon type
     */
    public Weapon(String type) {
        this.type = type;
    }

    /**
     * Gets the type of the weapon
     *
     * @return The weapon type
     */
    public String getType() {
        return type;
    }


    /**
     * Returns a String representation of the weapon
     *
     * @return The weapon as a String
     */
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(String.format("Type: %s\n", this.type));

        return stringBuilder.toString();
    }
}
