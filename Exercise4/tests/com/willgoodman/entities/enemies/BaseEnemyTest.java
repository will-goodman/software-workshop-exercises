package com.willgoodman.entities.enemies;

import org.junit.jupiter.api.Test;


class BaseEnemyTest {

    @Test
    void getMaxHealth() {
        BaseEnemy enemy = new BaseEnemy("Test Enemy");

        assert enemy.getMaxHealth() == 100.0f;
    }

    @Test
    void getName() {
        BaseEnemy enemy = new BaseEnemy("Test Enemy");

        assert enemy.getName().equals("Test Enemy");
    }

    @Test
    void getHealth() {
        BaseEnemy enemy = new BaseEnemy("Test Enemy");

        assert enemy.getHealth() == 100.0f;
    }

    @Test
    void setHealth() {
        BaseEnemy enemy = new BaseEnemy("Test Enemy");
        enemy.setHealth(50.0f);

        assert enemy.getHealth() == 50.0f;
    }

    @Test
    void setHealthBelowMinimum() {
        BaseEnemy enemy = new BaseEnemy("Test Enemy");
        enemy.setHealth(-150.0f);

        assert enemy.getHealth() == 0.0f;
    }

    @Test
    void setHealthAboveMaximum() {
        BaseEnemy enemy = new BaseEnemy("Test Enemy");
        enemy.setHealth(140.0f);

        assert enemy.getHealth() == 100.0f;
    }

    @Test
    void isDeadAliveEnemy() {
        BaseEnemy enemy = new BaseEnemy("Test Enemy");

        assert !enemy.isDead();
    }

    @Test
    void isDeadDeadEnemy() {
        BaseEnemy enemy = new BaseEnemy("Test Enemy");
        enemy.setHealth(0.0f);

        assert enemy.isDead();
    }

    @Test
    void toStringEnemy() {
        BaseEnemy enemy = new BaseEnemy("Test Enemy");

        assert enemy.toString().equals("Name = Test Enemy\nHealth = 100.0\n");
    }
}