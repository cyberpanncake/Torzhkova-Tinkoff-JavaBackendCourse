package edu.hw10.task1;

import java.lang.annotation.Annotation;
import java.util.Random;
import java.util.function.BiFunction;

public class ArgumentGenerator {
    private final Random random = new Random();
    private final BiFunction<Random, Annotation[], ?> lambda;

    public ArgumentGenerator(BiFunction<Random, Annotation[], ?> lambda) {
        this.lambda = lambda;
    }

    public Object next(Annotation[] annotations) {
        return lambda.apply(random, annotations);
    }
}
