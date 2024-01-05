package edu.hw7;

import edu.hw7.task3.AbstractPersonDatabase;
import edu.hw7.task3.Person;
import edu.hw7.task3.SynchronizedPersonDatabase;
import edu.hw7.task3.task3_5.ReadWriteLockPersonDatabase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Stream;

public class Task3Test {

    @ParameterizedTest
    @MethodSource("db")
    void personDatabaseTest(AbstractPersonDatabase db) throws InterruptedException {
        AtomicBoolean result = new AtomicBoolean(true);
        List<Person> persons = List.of(
            new Person(1, "name1", "address1", "phoneNumber2"),
            new Person(2, "name1", "address2", "phoneNumber1"),
            new Person(3, "name2", "address1", "phoneNumber1")
        );
        Thread addingThread = new Thread(() -> {
            for (Person person : persons) {
                db.add(person);
            }
        });
        Thread findingThread1 = new Thread(() -> findAttributesAssert(db, persons,result));
        Thread deletingThread = new Thread(() -> {
            for (Person person : persons) {
                db.delete(person.id());
            }
        });
        Thread findingThread2 = new Thread(() -> findAttributesAssert(db, persons, result));

        addingThread.start();
        findingThread1.start();
        deletingThread.start();
        findingThread2.start();

        addingThread.join();
        findingThread1.join();
        deletingThread.join();
        findingThread2.join();

        Assertions.assertTrue(result.get());
    }

    private void findAttributesAssert(AbstractPersonDatabase db, List<Person> persons, AtomicBoolean result) {
        for (Person person : persons) {
            if (db instanceof SynchronizedPersonDatabase) {
                synchronized (db) {
                    checkAttributes(db, person, result);
                }
            } else {
                ((ReadWriteLockPersonDatabase) db).getLock().readLock().lock();
                try {
                    checkAttributes(db, person, result);
                } finally {
                    ((ReadWriteLockPersonDatabase) db).getLock().readLock().unlock();
                }
            }
        }
    }

    private void checkAttributes(AbstractPersonDatabase db, Person person, AtomicBoolean result) {
        List<Person> lName = db.findByName(person.name());
        boolean findName = lName != null && lName.contains(person);
        List<Person> lAddress = db.findByAddress(person.address());
        boolean findAddress = lAddress != null && lAddress.contains(person);
        List<Person> lPhone = db.findByPhone(person.phoneNumber());
        boolean findPhone = lPhone != null && lPhone.contains(person);
        result.set(result.get() && (findName == findAddress && findAddress == findPhone));
    }

    @SuppressWarnings("MagicNumber")
    private static Stream<Arguments> db() {
        return Stream.of(
            Arguments.of(new SynchronizedPersonDatabase()),
            Arguments.of(new ReadWriteLockPersonDatabase())
        );
    }

    @ParameterizedTest
    @MethodSource("db")
    void personDatabaseExceptionTest(AbstractPersonDatabase db) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> db.add(null));
    }
}
