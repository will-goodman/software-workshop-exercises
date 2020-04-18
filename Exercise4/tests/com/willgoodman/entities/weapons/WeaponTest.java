package com.willgoodman.entities.weapons;

import org.junit.jupiter.api.Test;

class WeaponTest {

    @Test
    void getType() {
        Weapon weapon = new Weapon("Axe");

        assert weapon.getType().equals("Axe");
    }

    @Test
    void toStringWeapon() {
        Weapon weapon = new Weapon("Axe");

        assert weapon.toString().equals("Type: Axe\n");
    }
}