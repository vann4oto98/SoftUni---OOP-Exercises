package viceCity.models.guns;

public class Pistol extends BaseGun{
    private static final int BULLETS_PER_BARREL = 10;
    private static final int TOTAL_BULLETS = 100;
    private static final int BULLETS_PER_FIRE = 1;

    public Pistol(String name) {
        super(name, BULLETS_PER_BARREL, TOTAL_BULLETS);
    }

    @Override
    public int fire() {
        if (this.getBulletsPerBarrel() < BULLETS_PER_BARREL){
           this.reload();
        }

        return this.decreaseBulletsInBarrel(BULLETS_PER_FIRE);

    }
}
