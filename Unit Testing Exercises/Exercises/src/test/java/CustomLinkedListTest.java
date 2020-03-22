import org.junit.Assert;
import org.junit.Test;
import p05_CustomLinkedList.CustomLinkedList;

public class CustomLinkedListTest {

    @Test
    public void addingShouldWork(){
        CustomLinkedList<Integer> list = new CustomLinkedList<>();
        list.add(13);
        Assert.assertTrue(list.contains(13));
    }

    @Test
    public void removingShouldWork(){
        CustomLinkedList<Integer> list = new CustomLinkedList<>();
        list.add(13);
        list.remove(13);
        Assert.assertFalse(list.contains(13));
    }

    @Test
    public void indexOfShouldReturnOnlyFirstOccurrenceOfValue(){
        CustomLinkedList<Integer> list = new CustomLinkedList<>();
        list.add(13);
        list.add(42);
        list.add(69);
        list.add(13);
        Assert.assertEquals(list.indexOf(13), 0);
    }
}
