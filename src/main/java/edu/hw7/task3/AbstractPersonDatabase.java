package edu.hw7.task3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractPersonDatabase implements PersonDatabase {
    protected Map<Integer, Person> persons = new HashMap<>();
    protected Map<String, List<Person>> names = new HashMap<>();
    protected Map<String, List<Person>> addresses = new HashMap<>();
    protected Map<String, List<Person>> phoneNumbers = new HashMap<>();

    @Override
    public void add(Person person) {
        if (person == null) {
            throw new IllegalArgumentException("Человек не должен быть null");
        }
        addPerson(person);
    }

    protected abstract void addPerson(Person person);

    protected void addPersonInReversedKeyMap(String key, Person person, Map<String, List<Person>> map) {
        map.putIfAbsent(key, new ArrayList<>());
        map.get(key).add(person);
    }

    protected void deletePersonInReversedKeyMap(String key, Person person, Map<String, List<Person>> map) {
        map.get(key).remove(person);
        if (map.get(key).isEmpty()) {
            map.remove(key);
        }
    }
}
