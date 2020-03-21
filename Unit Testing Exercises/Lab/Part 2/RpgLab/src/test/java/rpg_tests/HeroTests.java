package rpg_tests;

import org.junit.Assert;
import org.junit.Test;
import rpg_lab.Hero;
import rpg_lab.Target;
import rpg_lab.Weapon;

public class HeroTests {

    private static final int TARGET_XP = 20;
    private static final int WEAPON_ATTACK = 10;
    private static final String HERO_NAME = "Ivan";

    @Test

    public void heroGainsExperienceAfterAttackIfTargetDies(){
        Target fakeTarget = new Target() {
            public void takeAttack(int attackPoints) {
            }
            public int getHealth() {
                return 0;
            }

            public int giveExperience() {
                return TARGET_XP;
            }

            public boolean isDead() {
                return true;
            }
        };
        Weapon fakeWeapon = new Weapon() {
            public void attack(Target target) {
            }
            public int getAttackPoints() {
                return WEAPON_ATTACK;
            }
            public int getDurabilityPoints() {
                return 0;
            }
        };
        Hero hero = new Hero(HERO_NAME, fakeWeapon);
        hero.attack(fakeTarget);
        Assert.assertEquals(20, hero.getExperience());
    }
}
