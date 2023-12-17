package edu.hw11;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.FixedValue;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static net.bytebuddy.matcher.ElementMatchers.named;

public class TasksTest {

    @Test
    public void task1Test() throws Exception {
        String expected = "Hello, ByteBuddy!";
        Class<?> dynamicType = new ByteBuddy()
            .subclass(Object.class)
            .method(named("toString")).intercept(FixedValue.value(expected))
            .make()
            .load(getClass().getClassLoader(), ClassLoadingStrategy.Default.WRAPPER)
            .getLoaded();
        String actual = dynamicType.getDeclaredConstructor().newInstance().toString();
        Assertions.assertEquals(expected, actual);
    }
}
