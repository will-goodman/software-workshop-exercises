package com.willgoodman.entities.enemies;

import com.willgoodman.entities.weapons.Weapon;

import java.util.ArrayList;

/**
 * Interface for a game entity which carries weapons
 *
 * @author Will Goodman
 */
public interface WeaponCarrier {

    /**
     * Gets the list of weapons currently being carried
     *
     * @return The list of weapons the entity is carrying
     */
    ArrayList<Weapon> getWeapons();

    /**
     * Add a new weapon to the entity
     *
     * @param weapon The weapon to be added
     */
    void addWeapon(Weapon weapon);

}
