package parkingSystem;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SoftParkTest {
  //TODO: TEST ALL THE FUNCTIONALITY OF THE PROVIDED CLASS SoftPark

    private SoftPark softPark;
    private Car car;

    @Before
    public void initialize(){
        softPark = new SoftPark();
        car = new Car("Audi", "CA100");
    }

    @Test
    public void constructorShouldBeValid(){
        int actualCount = softPark.getParking().size();
        Assert.assertEquals(12, actualCount);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void  getParkingShouldBeUnmodifiable(){
        softPark.getParking().remove("A1");
    }

    @Test(expected = IllegalArgumentException.class)
    public void parkCarShouldThrowExceptionWhenNoSuchSpot(){
        softPark.parkCar("U21223", car);
    }

    @Test(expected = IllegalArgumentException.class)
    public void parkCarShouldThrowExceptionWhenSpotIsOccupied(){
        softPark.parkCar("A1", car);
        Car secondCar = new Car("Lambo", "A 6969 A");
        softPark.parkCar("A1", secondCar);
    }

    @Test
    public void parkCarShouldReturnMessage(){
        String expected = "Car:CA100 parked successfully!";
        String message = softPark.parkCar("A1", car);
        Assert.assertEquals(expected, message);
    }

    @Test
    public void parkCarShouldAddCar(){
        softPark.parkCar("A1", car);
        Assert.assertEquals(car, softPark.getParking().get("A1"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void removeCarShouldThrowExceptionWhenNoSuchSpot(){
        softPark.parkCar("A1", car);
        softPark.removeCar("W1", car);
    }

    @Test(expected = IllegalArgumentException.class)
    public void removeCarShouldThrowExceptionWhenNoSuchCar(){
        softPark.parkCar("A1", car);
        Car otherCar = new Car("BMW", "CN5");
        softPark.removeCar("A1", otherCar);
    }

    @Test
    public void removeCarShouldRemoveCar(){
        softPark.parkCar("A1", car);
        softPark.removeCar("A1", car);
        Assert.assertNull(softPark.getParking().get("A1"));
    }

    @Test
    public void removeCarShouldReturnMessage(){
        softPark.parkCar("A1", car);
        String expected = "Remove car:CA100 successfully!";
        Assert.assertEquals(expected, softPark.removeCar("A1", car));
    }


}