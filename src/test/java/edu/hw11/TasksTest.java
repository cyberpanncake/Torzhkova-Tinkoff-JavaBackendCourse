package edu.hw11;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.agent.ByteBuddyAgent;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.IOException;
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

    static class ArithmeticUtils {
        public static int sum(int a, int b) {
            return a + b;
        }
    }

    public static class NewUtils {
        public static int sum(int a, int b) {
            return a * b;
        }
    }

    @Test
    void task2Test() {
        ByteBuddyAgent.install();
        ClassReloadingStrategy classReloadingStrategy = ClassReloadingStrategy
            .fromInstalledAgent();
        DynamicType.Unloaded<ArithmeticUtils> unloaded = new ByteBuddy()
            .redefine(ArithmeticUtils.class)
            .method(ElementMatchers.named("sum"))
            .intercept(MethodDelegation.to(NewUtils.class))
            .make();
        Assertions.assertEquals(6, ArithmeticUtils.sum(2, 4));
        unloaded.load(ArithmeticUtils.class.getClassLoader(), classReloadingStrategy);
        Assertions.assertEquals(8, ArithmeticUtils.sum(2, 4));
    }
}
