package viceCity.models.guns;

import viceCity.common.ExceptionMessages;

public abstract class BaseGun implements Gun {
    private String name;
    private int bulletsPerBarrel;
    private int totalBullets;
    private int barrelCapacity;

    protected BaseGun(String name, int bulletsPerBarrel, int totalBullets){
        this.setName(name);
        this.setBulletsPerBarrel(bulletsPerBarrel);
        this.setTotalBullets(totalBullets);
        this.barrelCapacity = bulletsPerBarrel;

    }


    @Override
    public String getName() {
        return this.name;
    }

    private void setName(String name){
        if (name == null || name.trim().isEmpty()){
            throw new NullPointerException(ExceptionMessages.NAME_NULL);
        }
        this.name = name;
    }

    @Override
    public int getBulletsPerBarrel() {
        return this.bulletsPerBarrel;
    }

    private void setBulletsPerBarrel(int bulletsPerBarrel){
        if (bulletsPerBarrel < 0) {
            throw new IllegalArgumentException(ExceptionMessages.BULLETS_LESS_THAN_ZERO);
        }

        this.bulletsPerBarrel = bulletsPerBarrel;
    }

    @Override
    public boolean canFire() {
        return this.bulletsPerBarrel > 0 || totalBullets > 0;
    }

    @Override
    public int getTotalBullets() {
        return this.totalBullets;
    }

    private void setTotalBullets(int totalBullets){
        if (totalBullets< 0){
            throw new IllegalArgumentException(ExceptionMessages.TOTAL_BULLETS_LESS_THAN_ZERO);
        }
        this.totalBullets = totalBullets;
    }

    protected int decreaseBulletsInBarrel(int bullets){
        this.bulletsPerBarrel -= bullets;
        int firedBullets = bullets;
        if (this.bulletsPerBarrel < 0) {
            this.bulletsPerBarrel = 0;
            firedBullets = 0;
        }
        return firedBullets;
    }

    protected void reload(){
        if (this.totalBullets >= barrelCapacity){
            this.bulletsPerBarrel = barrelCapacity;
            this.totalBullets -= barrelCapacity;
        }

    }

}
