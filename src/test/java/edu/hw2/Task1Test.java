package edu.hw2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import edu.hw2.task1.Expr.*;

class Task1Test {

    @Test
    public void exprEvaluate1Test() {
        double expected = 1;

        var res = new Constant(1);

        double actual = res.evaluate();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void exprEvaluateMinus1Test() {
        double expected = -1;

        var res = new Constant(-1);

        double actual = res.evaluate();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void exprEvaluateNegate1Test() {
        double expected = -1;

        var one = new Constant(1);
        var res = new Negate(one);

        double actual = res.evaluate();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void exprEvaluate2Plus3Test() {
        double expected = 5;

        var two = new Constant(2);
        var three = new Constant(3);
        var res = new Addition(two, three);

        double actual = res.evaluate();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void exprEvaluate2Minus3Test() {
        double expected = -1;

        var two = new Constant(2);
        var three = new Negate(new Constant(3));
        var res = new Addition(two, three);

        double actual = res.evaluate();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void exprEvaluate2Mul3Test() {
        double expected = 6;

        var two = new Constant(2);
        var three = new Constant(3);
        var res = new Multiplication(two, three);

        double actual = res.evaluate();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void exprEvaluate2Pow3Test() {
        double expected = 8;

        var two = new Constant(2);
        var res = new Exponent(two, 3);

        double actual = res.evaluate();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void exprEvaluate37Test() {
        double expected = 37;

        var two = new Constant(2);
        var four = new Constant(4);
        var negOne = new Negate(new Constant(1));
        var sumTwoFour = new Addition(two, four);
        var mult = new Multiplication(sumTwoFour, negOne);
        var exp = new Exponent(mult, 2);
        var res = new Addition(exp, new Constant(1));

        double actual = res.evaluate();
        Assertions.assertEquals(expected, actual);
    }
}
