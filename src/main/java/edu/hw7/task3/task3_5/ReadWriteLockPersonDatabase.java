package edu.hw7.task3.task3_5;

import edu.hw7.task3.AbstractPersonDatabase;
import edu.hw7.task3.Person;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockPersonDatabase extends AbstractPersonDatabase {
    private ReadWriteLock final lock = new ReentrantReadWriteLock();

    @Override
    protected void addPerson(Person person) {
        lock.writeLock().lock();
        try {
            persons.put(person.id(), person);
            addPersonInReversedKeyMap(person.name(), person, names);
            addPersonInReversedKeyMap(person.address(), person, addresses);
            addPersonInReversedKeyMap(person.phoneNumber(), person, phoneNumbers);
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public void delete(int id) {
        lock.writeLock().lock();
        try {
            Person person = persons.remove(id);
            deletePersonInReversedKeyMap(person.name(), person, names);
            deletePersonInReversedKeyMap(person.address(), person, addresses);
            deletePersonInReversedKeyMap(person.phoneNumber(), person, phoneNumbers);
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public List<Person> findByName(String name) {
        lock.readLock().lock();
        try {
            return names.get(name);
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public List<Person> findByAddress(String address) {
        lock.readLock().lock();
        try {
            return addresses.get(address);
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public List<Person> findByPhone(String phone) {
        lock.readLock().lock();
        try {
            return phoneNumbers.get(phone);
        } finally {
            lock.readLock().unlock();
        }
    }
}
