package com.willgoodman.entities.enemies.intelligentEnemies.mutants;

import com.willgoodman.entities.weapons.Weapon;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class MutantTest {

    @Test
    void getHealthIntelligenceEffect() {
        Mutant mutant = new Mutant("Test Mutant");

        assert mutant.getHealthIntelligenceEffect() == 2.0f;
    }

    @Test
    void getWeapons() {
        Mutant mutant = new Mutant("Test Mutant");

        assert mutant.getWeapons().equals(new ArrayList<Weapon>());
    }

    @Test
    void addWeapon() {
        Mutant mutant = new Mutant("Test Mutant");
        Weapon weapon = new Weapon("Test Weapon");

        mutant.addWeapon(weapon);

        ArrayList<Weapon> expectedWeapons = new ArrayList<>();
        expectedWeapons.add(weapon);

        assert mutant.getWeapons().equals(expectedWeapons);
    }

    @Test
    void addWeaponMaximum() {
        Mutant mutant = new Mutant("Test Mutant");
        Weapon weapon = new Weapon("Test Weapon");
        Weapon weapon2 = new Weapon("Test Weapon2");
        Weapon weapon3 = new Weapon("Test Weapon3");
        Weapon weapon4 = new Weapon("Test Weapon4");
        Weapon weapon5 = new Weapon("Test Weapon5");

        mutant.addWeapon(weapon);
        mutant.addWeapon(weapon2);
        mutant.addWeapon(weapon3);
        mutant.addWeapon(weapon4);
        mutant.addWeapon(weapon5);

        ArrayList<Weapon> expectedWeapons = new ArrayList<>();
        expectedWeapons.add(weapon);
        expectedWeapons.add(weapon2);
        expectedWeapons.add(weapon3);
        expectedWeapons.add(weapon4);

        assert mutant.getWeapons().equals(expectedWeapons);
    }


    @Test
    void toStringMutant() {
        Mutant mutant = new Mutant("Test Mutant");

        assert mutant.toString().equals("Name = Test Mutant\nHealth = 100.0\nIntelligence = 25.0\nWeapons = []\n");
    }
}