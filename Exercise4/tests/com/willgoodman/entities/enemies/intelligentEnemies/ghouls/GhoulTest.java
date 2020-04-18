package com.willgoodman.entities.enemies.intelligentEnemies.ghouls;

import org.junit.jupiter.api.Test;

class GhoulTest {

    @Test
    void getHealth() {
        Ghoul enemy = new Ghoul("Test Enemy");

        assert enemy.getHealth() == 150.0f;
    }

    @Test
    void setHealth() {
        Ghoul enemy = new Ghoul("Test Enemy");
        enemy.setHealth(120.0f);

        assert enemy.getHealth() == 120.0f;
    }

    @Test
    void setHealthAboveMaximum() {
        Ghoul enemy = new Ghoul("Test Enemy");
        enemy.setHealth(180.0f);

        assert enemy.getHealth() == 150.0f;
    }

    @Test
    void getMaxHealth() {
        Ghoul ghoul = new Ghoul("Test Ghoul");

        assert ghoul.getMaxHealth() == 150.0f;
    }

    @Test
    void getPutridity() {
        Ghoul ghoul = new Ghoul("Test Ghoul");

        assert ghoul.getPutridity() == 0.0f;
    }

    @Test
    void increasePutridity() {
        Ghoul ghoul = new Ghoul("Test Ghoul");

        ghoul.increasePutridity();

        assert ghoul.getPutridity() == 0.1f;
    }

    @Test
    void increasePutridityMaximum() {
        Ghoul ghoul = new Ghoul("Test Ghoul");

        for (int count = 0; count < 15; count++) {
            ghoul.increasePutridity();
        }

        assert ghoul.getPutridity() == 1.0f;
        assert ghoul.getHealth() == 150.0f;

        ghoul.increasePutridity();
        assert ghoul.getHealth() == 150.0f;
    }

    @Test
    void toStringGhoul() {
        Ghoul ghoul = new Ghoul("Test Ghoul");

        assert ghoul.toString().equals("Name = Test Ghoul\nHealth = 150.0\nIntelligence = 25.0\nPutridity = 0.0\n");
    }
}