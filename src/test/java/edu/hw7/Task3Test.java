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
import java.util.stream.Stream;

public class Task3Test {

    @ParameterizedTest
    @MethodSource("db")
    void personDatabaseTest(AbstractPersonDatabase db) throws InterruptedException {
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
        Thread findingThread1 = new Thread(() -> findAttributesAssert(db, persons));
        Thread deletingThread = new Thread(() -> {
            for (Person person : persons) {
                db.delete(person.id());
            }
        });
        Thread findingThread2 = new Thread(() -> findAttributesAssert(db, persons));

        addingThread.start();
        findingThread1.start();
        deletingThread.start();
        findingThread2.start();

        addingThread.join();
        findingThread1.join();
        deletingThread.join();
        findingThread2.join();
    }

    private void findAttributesAssert(AbstractPersonDatabase db, List<Person> persons) {
        for (Person person : persons) {
            boolean findName = db.findByName(person.name()).contains(person);
            boolean findAddress = db.findByAddress(person.address()).contains(person);
            boolean findPhone = db.findByPhone(person.phoneNumber()).contains(person);
            Assertions.assertTrue(findName == findAddress && findAddress == findPhone);
        }
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
