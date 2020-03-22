import org.junit.Assert;
import org.junit.Test;
import p04_BubbleSortTest.Bubble;

import java.util.Arrays;

public class BubbleTest {
    private int[] elements = {13, 42, 69, -1, 0, -2, -34};
    private int[] sorted = Arrays.stream(elements).sorted().toArray();


    @Test
    public void sortShouldSortTheCollection(){
        Bubble.sort(elements);
        Assert.assertArrayEquals(sorted, elements);
    }
}
