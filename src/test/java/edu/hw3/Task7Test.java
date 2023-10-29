package edu.hw3;

import edu.hw3.task7.TreeNullComparator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.Comparator;
import java.util.TreeMap;

public class Task7Test {

    @Test
    public void canPutNullTest() {
        TreeMap<String, String>
            tree = new TreeMap<>(new TreeNullComparator<String>(false, Comparator.naturalOrder()));
        tree.put(null, "s");
        Assertions.assertTrue(tree.containsKey(null));
    }

    @Test
    public void nullFirstTest() {
        TreeMap<Integer, Integer>
            tree = new TreeMap<>(new TreeNullComparator<Integer>(true, Comparator.naturalOrder()));
        tree.put(1, 1);
        tree.put(null, 2);
        tree.put(3, 3);
        Assertions.assertNull(tree.firstEntry().getKey());
    }

    @Test
    public void nullLastTest() {
        TreeMap<Integer, Integer>
            tree = new TreeMap<>(new TreeNullComparator<Integer>(false, Comparator.naturalOrder()));
        tree.put(1, 1);
        tree.put(null, 2);
        tree.put(3, 3);
        Assertions.assertNull(tree.lastEntry().getKey());
    }

    @Test
    public void sortWithNullsTest() {
        Integer[] expected = new Integer[] {null, 1, 2, 3};

        TreeMap<Integer, Integer>
            tree = new TreeMap<>(new TreeNullComparator<Integer>(true, Comparator.naturalOrder()));
        tree.put(1, 1);
        tree.put(null, 2);
        tree.put(3, 3);
        tree.put(2, 2);

        Assertions.assertArrayEquals(expected, tree.keySet().toArray());
    }

    @Test
    public void treeNullComparatorTest() {
        Integer[] expected = new Integer[] {null, 3};

        TreeMap<Integer, Integer>
            tree = new TreeMap<>(new TreeNullComparator<>(true, null));
        tree.put(3, 1);
        tree.put(null, 2);

        Assertions.assertArrayEquals(expected, tree.keySet().toArray());
    }
}
