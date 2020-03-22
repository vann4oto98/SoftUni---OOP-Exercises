import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import p03_IteratorTest.ListIterator;

import javax.naming.OperationNotSupportedException;

public class ListIteratorTest {
    private static final String[] VALUES = {"A", "B", "C", "D", "E"};

    private ListIterator list;

    @Before
    public void createList() throws OperationNotSupportedException {
        this.list = new ListIterator(VALUES);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void creatingListIteratorShouldThrowExceptionIfNullIsPassed() throws OperationNotSupportedException {
        new ListIterator(null);
    }

    @Test
    public void moveShouldReturnTrueIfAbleToMove(){
        for (int i = 0; i < VALUES.length-1; i++) {
           Assert.assertTrue(list.move());
        }
    }

    @Test
    public void moveShouldReturnFalseIfNoNextElement(){
        for (int i = 0; i < VALUES.length-1; i++) {
            list.move();
        }
        Assert.assertFalse(list.move());
    }

    @Test
    public void hasNextShouldReturnTrueIfHasNext() throws OperationNotSupportedException {
        ListIterator list = new ListIterator("A", "B");
        Assert.assertTrue(list.hasNext());
    }

    @Test
    public void hasNextShouldReturnFalseIfNoNext() throws OperationNotSupportedException {
        ListIterator list = new ListIterator("A");
        Assert.assertFalse(list.hasNext());
    }

    @Test(expected = IllegalStateException.class)
    public void printShouldThrowExceptionOnEmptyCollection() throws OperationNotSupportedException {
        new ListIterator().print();
    }

    @Test
    public void printShouldReturnCorrectElement(){
        Assert.assertEquals(this.list.print(), "A");
    }
}
