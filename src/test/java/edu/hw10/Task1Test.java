package edu.hw10;

import edu.hw10.task1.RandomObjectGenerator;
import edu.hw10.task1.annotation.Max;
import edu.hw10.task1.annotation.Min;
import edu.hw10.task1.annotation.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import java.lang.reflect.InvocationTargetException;

class Task1Test {
    private static final RandomObjectGenerator rog = new RandomObjectGenerator();

    @RepeatedTest(100)
    void nextObjectTest() {
        Assertions.assertDoesNotThrow(() -> (SimpleRecord) rog.nextObject(SimpleRecord.class));
    }

    @RepeatedTest(100)
    void nextObjectMinTest()
        throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        AnnotatedClass obj = (AnnotatedClass) rog.nextObject(AnnotatedClass.class);
        Assertions.assertTrue(obj.iMin() >= 1);
    }

    @RepeatedTest(100)
    void nextObjectNotNullTest()
        throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        AnnotatedClass obj = (AnnotatedClass) rog.nextObject(AnnotatedClass.class);
        Assertions.assertNotNull(obj.s());
    }

    @RepeatedTest(100)
    void nextObjectMaxTest()
        throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        AnnotatedClass obj = (AnnotatedClass) rog.nextObject(AnnotatedClass.class);
        Assertions.assertTrue(obj.iMax() <= 5);
    }

    @RepeatedTest(100)
    void nextObjectCreateTest() {
        Assertions.assertDoesNotThrow(() -> (AnnotatedClass)
            rog.nextObject(AnnotatedClass.class, "create"));
    }

    public record SimpleRecord(int i, String s, boolean b) {}

    public static class AnnotatedClass {
        private final int iMin;
        private final String s;
        private final int iMax;

        public AnnotatedClass(@Min(1) int iMin, @NotNull String s, @Max(5) int iMax) {
            this.iMin = iMin;
            this.s = s;
            this.iMax = iMax;
        }

        public static AnnotatedClass create(int iMin, String s, int iMax) {
            return new AnnotatedClass(iMin, s, iMax);
        }

        public int iMin() {
            return iMin;
        }

        public String s() {
            return s;
        }

        public int iMax() {
            return iMax;
        }
    }
}
