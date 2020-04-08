package blueOrigin;

import org.junit.Assert;
import org.junit.Test;

public class SpaceshipTests {
    //TODO: TEST ALL THE FUNCTIONALITY OF THE PROVIDED CLASS Spaceship

    @Test
    public  void getCount_ShouldReturnCorrectResult(){
        Spaceship spaceship = new Spaceship("Name one", 10);
        Assert.assertEquals(0, spaceship.getCount());
    }

    @Test
    public  void getName_ShouldReturnCorrectResult(){
        Spaceship spaceship = new Spaceship("Name one", 10);
        Assert.assertEquals("Name one", spaceship.getName());
    }

    @Test
    public  void getCapacity_ShouldReturnCorrectResult(){
        Spaceship spaceship = new Spaceship("Name one", 10);
        Assert.assertEquals(10, spaceship.getCapacity());
    }

    @Test(expected = IllegalArgumentException.class)
    public void add_ShouldThrowExceptionWhenNoCapacity(){
        Spaceship spaceship = new Spaceship("Name one", 1);
        spaceship.add(new Astronaut("Name one", 12));
        spaceship.add(new Astronaut("Name two", 12));
    }

    @Test(expected = IllegalArgumentException.class)
    public void add_ShouldThrowExceptionWhenAstronautIsTheSame(){
        Spaceship spaceship = new Spaceship("Name one", 5);
        spaceship.add(new Astronaut("Name one", 12));
        spaceship.add(new Astronaut("Name one", 12));
    }

    @Test
    public void add_ShouldWorkCorrectly(){
        Spaceship spaceship = new Spaceship("Name one", 5);
        spaceship.add(new Astronaut("Name one", 12));
        spaceship.add(new Astronaut("Name two", 12));
        Assert.assertEquals(2, spaceship.getCount());
    }

    @Test
    public void remove_WithInvalidNameShouldReturnFalse(){
        Spaceship spaceship = new Spaceship("Name one", 5);
        spaceship.add(new Astronaut("Name one", 12));
        spaceship.add(new Astronaut("Name two", 12));
        Assert.assertFalse(spaceship.remove("Sasho"));
    }
    @Test
    public void remove_WithValidNameShouldReturnTrue(){
        Spaceship spaceship = new Spaceship("Name one", 5);
        spaceship.add(new Astronaut("Name one", 12));
        spaceship.add(new Astronaut("Name two", 12));
        Assert.assertTrue(spaceship.remove("Name one"));
    }

    @Test
    public void remove_WithValidNameShouldRemoveAstronaut(){
        Spaceship spaceship = new Spaceship("Name one", 5);
        spaceship.add(new Astronaut("Name one", 12));
        spaceship.add(new Astronaut("Name two", 12));
        spaceship.remove("Name one");
        Assert.assertEquals(1, spaceship.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void invalidCapacityValidation_ShouldThrowException(){
        Spaceship spaceship = new Spaceship("Koraba", -5);
    }

    @Test(expected = NullPointerException.class)
    public void invalidNameValidation_ShouldThrowException(){
        Spaceship spaceship = new Spaceship(null, 5);
    }





}
