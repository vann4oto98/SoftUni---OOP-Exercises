import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import p02_ExtendedDatabase.Database;
import p02_ExtendedDatabase.Person;

import javax.naming.OperationNotSupportedException;

public class DatabaseExtendedTests {

    private static final Person[] TOO_MUCH_PEOPLE = new Person[17];
    private static final Person[] PEOPLE = new Person[] {new Person(1, "Ivan"), new Person(2, "Pesho")};
    private static final String INVALID_NAME = "Samuil";
    private static final String NAME = "Luffy";
    private Database database;

    @Test(expected = OperationNotSupportedException.class)

    public void databaseWithMoreThan16PeopleShouldThrowException() throws OperationNotSupportedException {
        Database database = new Database(TOO_MUCH_PEOPLE);
    }

    @Test(expected = OperationNotSupportedException.class)

    public void databaseShouldThrowExceptionIfNoPeopleAreAdded() throws OperationNotSupportedException {
        Database database = new Database();
    }

    @Before

    public void initializeDatabase() throws OperationNotSupportedException {
        this.database = new Database(PEOPLE);
    }

    @Test(expected = OperationNotSupportedException.class)
        // If there are multiple users with this id, throw OperationNotSupportedException.

        // fixed the given code in order to work
    public void addPersonWithSameIdShouldThrowException() throws OperationNotSupportedException {
        this.database.add(new Person(1, NAME));
    }

    @Test(expected = OperationNotSupportedException.class)

    //	If negative nor null ids are present, throw OperationNotSupportedException.

    // fixed the given code in order to work

    public void addWithNegativeIdsShouldThrowException() throws OperationNotSupportedException {
        this.database.add(new Person(-1, NAME));
    }

    @Test

    public void removeShouldRemoveLastPerson() throws OperationNotSupportedException {
        Person firstPerson = this.database.getElements()[0];
        int initialSize = this.database.getElements().length;
        this.database.remove();
        Person firstPersonAfterRemove = this.database.getElements()[0];
        Assert.assertEquals(initialSize-1, this.database.getElements().length);
        Assert.assertEquals(firstPerson, firstPersonAfterRemove);

    }

    @Test(expected = OperationNotSupportedException.class)

    public void findUserShouldThrowExceptionWhenNoSuchUser() throws OperationNotSupportedException {
        this.database.findByUsername(INVALID_NAME);
    }

    @Test(expected = OperationNotSupportedException.class)

    public void findUserShouldThrowExceptionWhenNull() throws OperationNotSupportedException {
        this.database.findByUsername(null);
    }

    @Test

    public void findUserShouldReturnUser() throws OperationNotSupportedException {
        this.database.add(new Person(10, NAME));
        Assert.assertEquals(NAME, this.database.findByUsername(NAME).getUsername());
    }

    @Test(expected = OperationNotSupportedException.class)

    public void findByIDShouldThrowExceptionIfNoSuchID() throws OperationNotSupportedException {
        this.database.findById(5);
    }

    @Test

    public void findByIDShouldReturnID() throws OperationNotSupportedException {
        Assert.assertEquals(1, this.database.findById(1).getId());
    }
}
