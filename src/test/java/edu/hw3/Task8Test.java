package edu.hw3;

import edu.hw3.task8.BackwardIterator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.ListIterator;

class Task8Test {

    @Test
    public void backwardIteratorTest() {
        List input = List.of(1,2,3);
        BackwardIterator actualIterator = new BackwardIterator<>(input);
        ListIterator expectedIterator = input.reversed().listIterator();
        while (expectedIterator.hasNext()) {
            Assertions.assertEquals(expectedIterator.hasNext(), actualIterator.hasNext());
            Assertions.assertEquals(expectedIterator.next(), actualIterator.next());
        }
    }

    @Test
    public void backwardIteratorExceptionTest() {
            Assertions.assertThrows(IllegalArgumentException.class, () -> new BackwardIterator<>(null));
    }
}
