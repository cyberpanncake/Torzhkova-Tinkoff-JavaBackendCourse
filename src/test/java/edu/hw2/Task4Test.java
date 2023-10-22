package edu.hw2;

import java.util.function.Supplier;
import edu.hw2.task4.CallingInfo;
import edu.hw2.task4.CallingInfoUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Task4Test {
    @Test
    public void callingInfoTest() {
        CallingInfo info = CallingInfoUtils.callingInfo();

        Assertions.assertEquals(info.className(), this.getClass().getName());
        Assertions.assertEquals(info.methodName(), "callingInfoTest");
    }

    @Test
    public void callingInfoNestedTest() {
        CallingInfo info = callingInfoNested();

        Assertions.assertEquals(info.className(), this.getClass().getName());
        Assertions.assertEquals(info.methodName(), "callingInfoNested");
    }

    private CallingInfo callingInfoNested() {
        return CallingInfoUtils.callingInfo();
    }

    @Test
    public void callingInfoInsideLambdaTest() {
        Supplier<CallingInfo> supplier = () -> CallingInfoUtils.callingInfo();
        CallingInfo info = supplier.get();

        Assertions.assertEquals(info.className(), this.getClass().getName());
        Assertions.assertTrue(info.methodName().startsWith("lambda"));
        Assertions.assertTrue(info.methodName().contains("callingInfoInsideLambda"));
    }
}
