package com.willgoodman.entities.enemies.zombies;

import com.willgoodman.entities.weapons.Weapon;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class ZombieTest {

    @Test
    void getRateOfDecomposition(){
        Zombie zombie = new Zombie("Test Zombie");

        assert zombie.getRateOfDecomposition() == 0.5f;
    }

    @Test
    void getMaxAnger() {
        Zombie zombie = new Zombie("Test Zombie");

        assert zombie.getMaxAnger() == 50.0f;
    }

    @Test
    void getInitialAnger() {
        Zombie zombie = new Zombie("Test Zombie");

        assert zombie.getInitialAnger() == 0.0f;
    }

    @Test
    void getAnger() {
        Zombie zombie = new Zombie("Test Zombie");

        assert zombie.getAnger() == 0f;
    }

    @Test
    void setAnger() {
        Zombie zombie = new Zombie("Test Zombie");

        zombie.setAnger(10.0f);

        assert zombie.getAnger() == 10.0f;
    }

    @Test
    void setAngerMinimum() {
        Zombie zombie = new Zombie("Test Zombie");

        zombie.setAnger(-50.0f);

        assert zombie.getAnger() == 0.0f;
    }

    @Test
    void setAngerMaximum() {
        Zombie zombie = new Zombie("Test Zombie");

        zombie.setAnger(100.0f);

        assert zombie.getAnger() == 50.0f;
    }

    @Test
    void getWeapons() {
        Zombie zombie = new Zombie("Test Zombie");

        assert zombie.getWeapons().equals(new ArrayList<Weapon>());
    }

    @Test
    void decompose() {
        Zombie zombie = new Zombie("Test Zombie");

        zombie.decompose();

        assert zombie.getHealth() == 99.5f;
    }


    @Test
    void addWeapon() {
        Zombie zombie = new Zombie("Test Zombie");
        Weapon weapon = new Weapon("Test Weapon");

        zombie.addWeapon(weapon);

        ArrayList<Weapon> expectedWeapons = new ArrayList<>();
        expectedWeapons.add(weapon);

        assert zombie.getWeapons().equals(expectedWeapons);
    }

    @Test
    void addWeaponMaximum() {
        Zombie zombie = new Zombie("Test Zombie");
        Weapon weapon = new Weapon("Test Weapon");
        Weapon weapon2 = new Weapon("Test Weapon2");
        Weapon weapon3 = new Weapon("Test Weapon3");

        zombie.addWeapon(weapon);
        zombie.addWeapon(weapon2);
        zombie.addWeapon(weapon3);

        ArrayList<Weapon> expectedWeapons = new ArrayList<>();
        expectedWeapons.add(weapon);
        expectedWeapons.add(weapon2);

        assert zombie.getWeapons().equals(expectedWeapons);
    }

    @Test
    void toStringZombie() {
        Zombie zombie = new Zombie("Test Zombie");

        assert zombie.toString().equals("Name = Test Zombie\nHealth = 100.0\nAnger = 0.0\nWeapons = []\n");
    }
}