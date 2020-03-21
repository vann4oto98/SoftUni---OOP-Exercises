import org.junit.Assert;
import org.junit.Test;
import p01_Database.Database;

import javax.naming.OperationNotSupportedException;

public class DatabaseTests {

    private static Integer[] MAX_ARRAY = new Integer[17];
    private static Integer[] SMALL_ARRAY = new Integer[] {1, 2};



    @Test(expected = OperationNotSupportedException.class)

    public void constructorWith17ElementsShouldThrowException() throws OperationNotSupportedException {
        Database database = new Database(MAX_ARRAY);
    }

    @Test(expected = OperationNotSupportedException.class)

    public void constructorWithNoElementsShouldThrowException() throws OperationNotSupportedException {
        Database database = new Database();
    }


    @Test(expected = OperationNotSupportedException.class)

    public void addWithNullShouldThrowException() throws OperationNotSupportedException {
        Database database = new Database(SMALL_ARRAY);
        database.add(null);
    }

    @Test(expected = OperationNotSupportedException.class)

    public void removeFromEmptyDatabaseShouldThrowException() throws OperationNotSupportedException {
        Database database = new Database(SMALL_ARRAY);
        database.remove();
        database.remove();
        database.remove();
    }

    @Test

    public void addShouldAddAtTheNextFreeCell() throws OperationNotSupportedException {
        Database database = new Database(SMALL_ARRAY);
        database.add(10);
        int element = database.getElements()[2];
        Assert.assertEquals(10, element);
    }

    @Test

    public void removeShouldRemoveElementAtTheLastIndex() throws OperationNotSupportedException {
        Database database = new Database(SMALL_ARRAY);
        int firstElement = database.getElements()[0];
        database.remove();
        int firstElementAfterRemove = database.getElements()[0];
        Assert.assertEquals(firstElement, firstElementAfterRemove);
    }


}
