package heroRepository;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class HeroRepositoryTests {
    //TODO: TEST ALL THE FUNCTIONALITY OF THE PROVIDED CLASS HeroRepository
    private HeroRepository heroRepository;
    private Hero hero1;
    private Hero hero2;
    private Hero hero3;
    private Hero hero4HighestLevel;

    @Before
    public void initialize(){
        heroRepository = new HeroRepository();
        hero1 = new Hero("Vanyo", 10);
        hero2 = new Hero("Rosen", 1);
        hero3 = new Hero("Ico",  69);
        hero4HighestLevel = new Hero("Goku", 9000);

    }
    @Test
    public void getCountShouldReturnCorrectCount(){
        this.heroRepository.create(this.hero1);
        this.heroRepository.create(this.hero2);
        this.heroRepository.create(this.hero3);
        Assert.assertEquals(3, this.heroRepository.getCount());
    }

    @Test(expected = NullPointerException.class)
    public void createWithNullShouldThrowException(){
        heroRepository.create(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createWithSameNameHeroShouldThrowException(){
        this.heroRepository.create(hero1);
        this.heroRepository.create(hero1);
    }

    @Test
    public void createShouldAddTheHero(){
        this.heroRepository.create(hero1);
        Assert.assertEquals(hero1, this.heroRepository.getHero(hero1.getName()));
    }

    @Test(expected = NullPointerException.class)
    public void removeWithNullShouldThrowException(){
        this.heroRepository.remove(null);
    }

    @Test(expected = NullPointerException.class)
    public void removeWithEmptySpaceShouldThrowException(){
        this.heroRepository.remove("  ");
    }

    @Test
    public void removeShouldRemoveTheHero(){
        this.heroRepository.create(hero1);
        this.heroRepository.create(hero2);
        this.heroRepository.remove(hero2.getName());
        boolean heroIsRemoved = true;
        for (Hero hero: this.heroRepository.getHeroes()){
            if (hero.getName().equals(hero2.getName())){
                heroIsRemoved = false;
            }
        }
        Assert.assertTrue(heroIsRemoved);
    }

    @Test
    public void getHeroWitHighestLevelShouldReturnHeroWithHighestLevel(){
        this.heroRepository.create(hero1);
        this.heroRepository.create(hero2);
        this.heroRepository.create(hero4HighestLevel);
        this.heroRepository.create(hero3);
        Assert.assertEquals(hero4HighestLevel, this.heroRepository.getHeroWithHighestLevel());
    }

    @Test
    public void getHeroShouldReturnHero(){
        this.heroRepository.create(hero1);
        this.heroRepository.create(hero2);
        this.heroRepository.create(hero4HighestLevel);
        this.heroRepository.create(hero3);
        Assert.assertEquals(hero4HighestLevel, this.heroRepository.getHero(hero4HighestLevel.getName()));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void getHeroesShouldReturnUnmodifiableCollection(){
        this.heroRepository.create(hero1);
        this.heroRepository.create(hero2);
        this.heroRepository.getHeroes().clear();
    }
}
