package com.willgoodman.entities.enemies.intelligentEnemies;

import org.junit.jupiter.api.Test;

class IntelligentEnemyTest {

    @Test
    void getHealthIntelligenceEffect() {
        IntelligentEnemy enemy = new IntelligentEnemy("Test Enemy");

        assert enemy.getHealthIntelligenceEffect() == 1.0f;
    }

    @Test
    void setHealthDecrease() {
        IntelligentEnemy enemy = new IntelligentEnemy("Test Enemy");
        enemy.setHealth(50.0f);

        assert enemy.getHealth() == 50.0f;
        assert enemy.getIntelligence() == 24.0f;
    }

    @Test
    void setHealthIncrease() {
        IntelligentEnemy enemy = new IntelligentEnemy("Test Enemy");
        enemy.setHealth(50.0f);
        enemy.setHealth(70.0f);
        enemy.setHealth(80.0f);

        assert enemy.getHealth() == 80.0f;
        assert enemy.getIntelligence() == 26.0f;
    }


    @Test
    void getIntelligence() {
        IntelligentEnemy enemy = new IntelligentEnemy("Test Enemy");

        assert enemy.getIntelligence() == 25.0f;
    }

    @Test
    void setIntelligence() {
        IntelligentEnemy enemy = new IntelligentEnemy("Test Enemy");

        enemy.setIntelligence(5.0f);

        assert enemy.getIntelligence() == 5.0f;
    }

    @Test
    void setIntelligenceMaximum() {
        IntelligentEnemy enemy = new IntelligentEnemy("Test Enemy");

        enemy.setIntelligence(70.0f);

        assert enemy.getIntelligence() == 50.0f;
    }

    @Test
    void setIntelligenceMinimum() {
        IntelligentEnemy enemy = new IntelligentEnemy("Test Enemy");

        enemy.setIntelligence(-50.0f);

        assert enemy.getIntelligence() == 0.0f;
    }

    @Test
    void toStringIntelligentEnemy() {
        IntelligentEnemy enemy = new IntelligentEnemy("Test Enemy");

        assert enemy.toString().equals("Name = Test Enemy\nHealth = 100.0\nIntelligence = 25.0\n");
    }
}