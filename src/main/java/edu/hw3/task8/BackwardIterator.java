package edu.hw3.task8;

import java.util.Collection;
import java.util.Iterator;

public class BackwardIterator<E> implements Iterator<E> {
    private final Object[] collection;
    private int cursor;

    public BackwardIterator(Collection<E> collection) {
        if (collection == null) {
            throw new IllegalArgumentException("Коллекция не должна быть пустой");
        }
        this.collection = collection.toArray(new Object[0]);
        this.cursor = collection.size();
    }

    @Override
    public boolean hasNext() {
        return cursor >= 0;
    }

    @Override
    public E next() {
        cursor--;
        return (E) collection[cursor];
    }
}
