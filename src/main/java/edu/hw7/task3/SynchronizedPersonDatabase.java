package edu.hw7.task3;

import java.util.List;

public class SynchronizedPersonDatabase extends AbstractPersonDatabase {

    @Override
    protected synchronized void addPerson(Person person) {
        persons.put(person.id(), person);
        addPersonInReversedKeyMap(person.name(), person, names);
        addPersonInReversedKeyMap(person.address(), person, addresses);
        addPersonInReversedKeyMap(person.phoneNumber(), person, phoneNumbers);
    }

    @Override
    public synchronized void delete(int id) {
        Person person = persons.remove(id);
        if (person != null) {
            deletePersonInReversedKeyMap(person.name(), person, names);
            deletePersonInReversedKeyMap(person.address(), person, addresses);
            deletePersonInReversedKeyMap(person.phoneNumber(), person, phoneNumbers);
        }
    }

    @Override
    public synchronized List<Person> findByName(String name) {
        return names.get(name);
    }

    @Override
    public synchronized List<Person> findByAddress(String address) {
        return addresses.get(address);
    }

    @Override
    public synchronized List<Person> findByPhone(String phone) {
        return phoneNumbers.get(phone);
    }
}
