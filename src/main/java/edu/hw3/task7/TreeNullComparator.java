package edu.hw3.task7;

import java.util.Comparator;

public class TreeNullComparator<T> implements Comparator<T> {
    private final boolean nullFirst;

    private final Comparator<T> wrappedComparator;

    public TreeNullComparator(boolean nullFirst, Comparator<T> wrappedComparator) {
        this.nullFirst = nullFirst;
        this.wrappedComparator = wrappedComparator;
    }

    @Override
    public int compare(T a, T b) {
        if (a == null) {
            return (b == null) ? 0 : (nullFirst ? -1 : 1);
        } else if (b == null) {
            return nullFirst ? 1 : -1;
        } else {
            return (wrappedComparator == null) ? 0 : wrappedComparator.compare(a, b);
        }
    }
}

