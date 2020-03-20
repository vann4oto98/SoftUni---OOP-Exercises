package rpg_tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import rpg_lab.Axe;
import rpg_lab.Dummy;

public class AxeTests {

    private static final int AXE_ATTACK = 10;
    private static final int AXE_DURABILITY = 10;
    private static final int DUMMY_HEALTH = 20;
    private static final int DUMMY_XP = 20;
    private static final int EXPECTED_DURABILITY = AXE_DURABILITY -1;

    private Axe axe;
    private Dummy dummy;

    @Before
    public void initializeAxeAndDummy(){
        this.axe = new Axe(AXE_ATTACK, AXE_DURABILITY);
        this.dummy = new Dummy(DUMMY_HEALTH, DUMMY_XP);
    }


    @Test

    public void weaponAttackLosesDurability(){
        this.axe.attack(this.dummy);
        Assert.assertEquals(EXPECTED_DURABILITY, this.axe.getDurabilityPoints());
    }

    @Test(expected = IllegalStateException.class)

    public void attackingWithBrokenWeaponShouldThrowException(){
        for (int i = 0; i < 11; i++) {
            this.axe.attack(dummy);
        }
    }



}
