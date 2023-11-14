package edu.hw3.task8;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class BackwardIterator<E> implements Iterator<E> {
    private final List<E> collection;
    private int cursor;

    public BackwardIterator(List<E> collection) {
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
        return (E) collection.get(cursor);
    }
}
