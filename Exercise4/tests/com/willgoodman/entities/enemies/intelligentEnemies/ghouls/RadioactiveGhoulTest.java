package com.willgoodman.entities.enemies.intelligentEnemies.ghouls;

import org.junit.jupiter.api.Test;

class RadioactiveGhoulTest {

    @Test
    void getRadioactivity() {
        RadioactiveGhoul ghoul = new RadioactiveGhoul("Test Ghoul");

        assert ghoul.getRadioactivity() == 50.0f;
    }

    @Test
    void changeRadioactivity() {
        RadioactiveGhoul ghoul = new RadioactiveGhoul("Test Ghoul");

        ghoul.changeRadioactivity(20.0f);

        assert ghoul.getRadioactivity() == 70.0f;
    }

    @Test
    void changeRadioactivityMinimum() {
        RadioactiveGhoul ghoul = new RadioactiveGhoul("Test Ghoul");

        ghoul.changeRadioactivity(-70.0f);

        assert ghoul.getRadioactivity() == 0.0f;
    }

    @Test
    void changeRadioactivityMaximum() {
        RadioactiveGhoul ghoul = new RadioactiveGhoul("Test Ghoul");

        ghoul.changeRadioactivity(70.0f);

        assert ghoul.getRadioactivity() == 100.0f;
    }

    @Test
    void toStringRadioactiveGhoul() {
        RadioactiveGhoul ghoul = new RadioactiveGhoul("Test Ghoul");

        assert ghoul.toString().equals("Name = Test Ghoul\nHealth = 150.0\nIntelligence = 25.0\nPutridity = 0.0\nRadioactivity = 50.0\n");
    }
}