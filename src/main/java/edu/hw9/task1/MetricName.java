package edu.hw9.task1;

import java.util.Arrays;
import java.util.function.Function;

public enum MetricName {
    SUM(arr -> Arrays.stream(arr).sum()),
    AVG(arr -> Arrays.stream(arr).sum() / arr.length),
    MIN(arr -> Arrays.stream(arr).min().orElse(0)),
    MAX(arr -> Arrays.stream(arr).max().orElse(0));

    private final Function<double[], Double> function;

    MetricName(Function<double[], Double> function) {
        this.function = function;
    }

    public Function<double[], Double> function() {
        return function;
    }

    public Metric count(double[] data) {
        return new Metric(this, function.apply(data));
    }
}
