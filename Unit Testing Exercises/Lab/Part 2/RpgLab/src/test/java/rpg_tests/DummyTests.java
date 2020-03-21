package rpg_tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import rpg_lab.Axe;
import rpg_lab.Dummy;
import rpg_lab.Hero;
import rpg_lab.Weapon;

public class DummyTests {
    private static final int AXE_ATTACK = 10;
    private static final int AXE_DURABILITY = 10;
    private static final int DUMMY_HEALTH = 20;
    private static final int DUMMY_XP = 20;
    private static final String HERO_NAME = "Ivan";
    private static final int ZERO_XP = 0;
    private static final int EXPECTED_DUMMY_XP = DUMMY_XP - AXE_ATTACK;

    private Weapon weapon;
    private Dummy dummy;
    private Hero hero;

    @Before
    public void initializeHeroWeaponAndDummy(){
        this.weapon = new Axe(AXE_ATTACK, AXE_DURABILITY);
        this.dummy = new Dummy(DUMMY_HEALTH, DUMMY_XP);
        this.hero = new Hero(HERO_NAME, this.weapon);
    }


    @Test

    public void dummyShouldLoseHealthWhenAttacked(){
        this.weapon.attack(this.dummy);
        Assert.assertEquals(EXPECTED_DUMMY_XP, this.dummy.getHealth());
    }

    @Test(expected = IllegalStateException.class)

    public void deadDummyThrowsExceptionWhenAttacked(){
        for (int i = 0; i < 3; i++) {
            this.weapon.attack(this.dummy);
        }
    }

    @Test

    public void deadDummyShouldGiveXP(){
        for (int i = 0; i < 2; i++) {
            this.hero.attack(this.dummy);
        }
        Assert.assertEquals(DUMMY_XP, hero.getExperience());
    }

    @Test

    public void aliveDummyShouldNotGiveXP(){
        this.hero.attack(this.dummy);
        Assert.assertEquals(ZERO_XP, hero.getExperience());
    }


}
