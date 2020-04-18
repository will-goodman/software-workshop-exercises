package com.willgoodman.entities.enemies.zombies;

import com.willgoodman.entities.weapons.Weapon;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;


class LegendaryZombieTest {

    @Test
    void getHealth() {
        LegendaryZombie enemy = new LegendaryZombie("Test Enemy");

        assert enemy.getHealth() == 500.0f;
    }

    @Test
    void setHealth() {
        LegendaryZombie enemy = new LegendaryZombie("Test Enemy");
        enemy.setHealth(250.0f);

        assert enemy.getHealth() == 250.0;
    }

    @Test
    void setHealthAboveMaximum() {
        LegendaryZombie enemy = new LegendaryZombie("Test Enemy");
        enemy.setHealth(640.0f);

        assert enemy.getHealth() == 500.0f;
    }

    @Test
    void getAnger() {
        LegendaryZombie zombie = new LegendaryZombie("Test Zombie");

        assert zombie.getAnger() == 50.0f;
    }

    @Test
    void setAnger() {
        LegendaryZombie zombie = new LegendaryZombie("Test Zombie");

        zombie.setAnger(70.0f);

        assert zombie.getAnger() == 70.0f;
    }

    @Test
    void setAngerMinimum() {
        LegendaryZombie zombie = new LegendaryZombie("Test Zombie");

        zombie.setAnger(-50.0f);

        assert zombie.getAnger() == 0.0f;
    }

    @Test
    void setAngerMaximum() {
        LegendaryZombie zombie = new LegendaryZombie("Test Zombie");

        zombie.setAnger(150.0f);

        assert zombie.getAnger() == 100.0f;
    }

    @Test
    void decompose() {
        LegendaryZombie zombie = new LegendaryZombie("Test Zombie");

        zombie.decompose();

        assert zombie.getHealth() == 499.95f;
    }

    @Test
    void getRateOfDecomposition(){
        LegendaryZombie zombie = new LegendaryZombie("Test Zombie");

        assert zombie.getRateOfDecomposition() == 0.05f;
    }

    @Test
    void getMaxAnger(){
        LegendaryZombie zombie = new LegendaryZombie("Test Zombie");

        assert zombie.getMaxAnger() == 100.0f;
    }

    @Test
    void getInitialAnger(){
        LegendaryZombie zombie = new LegendaryZombie("Test Zombie");

        assert zombie.getInitialAnger() == 50.0f;
    }

    @Test
    void getMaxHealth(){
        LegendaryZombie zombie = new LegendaryZombie("Test Zombie");

        assert zombie.getMaxHealth() == 500.0f;
    }

    @Test
    void mutate() {
        LegendaryZombie zombie = new LegendaryZombie("Test Zombie");

        zombie.setHealth(50.0f);
        zombie.mutate();

        assert zombie.getHealth() == 500.0f;
        assert zombie.getAnger() == 100.0f;
    }

    @Test
    void mutateDead() {
        LegendaryZombie zombie = new LegendaryZombie("Test Zombie");

        zombie.setHealth(-500.0f);
        zombie.mutate();

        assert zombie.getHealth() == 0.0f;
        assert zombie.getAnger() == 50.0f;
        assert zombie.isDead();
    }

    @Test
    void toStringLegendaryZombie() {
        LegendaryZombie zombie = new LegendaryZombie("Test Zombie");

        assert zombie.toString().equals("Name = Test Zombie\nHealth = 500.0\nAnger = 50.0\nWeapons = []\n");
    }
}