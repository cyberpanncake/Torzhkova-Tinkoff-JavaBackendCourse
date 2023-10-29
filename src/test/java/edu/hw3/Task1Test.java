package edu.hw3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;

class Task1Test {
    private static final Task1 TASK1 = new Task1();

    @ParameterizedTest
    @MethodSource("strings")
    public void atbashTest(String input, String expected) {
        String actual = TASK1.atbash(input);
        Assertions.assertEquals(expected, actual);
    }

    private static Stream<Arguments> strings() {
        return Stream.of(
            Arguments.of("Hello world!", "Svool dliow!"),
            Arguments.of(                "Any fool can write code that a computer can understand. "
                + "Good programmers write code that humans can understand. ― Martin Fowler",
                "Zmb ullo xzm dirgv xlwv gszg z xlnkfgvi xzm fmwvihgzmw. "
                + "Tllw kiltiznnvih dirgv xlwv gszg sfnzmh xzm fmwvihgzmw. ― Nzigrm Uldovi")
        );
    }

    @Test
    public void atbashNullExceptionTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> TASK1.atbash(null));
    }

    @Test
    public void atbashEmptyExceptionTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> TASK1.atbash(""));
    }
}
