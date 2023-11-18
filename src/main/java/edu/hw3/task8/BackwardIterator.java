package edu.hw3.task8;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class BackwardIterator<E> implements Iterator<E> {
    private final ArrayList<E> collection;
    private int cursor;

    public BackwardIterator(Collection<E> collection) {
        if (collection == null) {
            throw new IllegalArgumentException("Коллекция не должна быть пустой");
        }
        this.collection = new ArrayList<>(collection);
        this.cursor = collection.size();
    }

    @Override
    public boolean hasNext() {
        return cursor >= 0;
    }

    @Override
    public E next() {
        cursor--;
        return collection.get(cursor);
    }
}
