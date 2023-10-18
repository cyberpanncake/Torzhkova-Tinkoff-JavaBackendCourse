package edu.hw2.task1;

public sealed interface Expr {
    double evaluate();

    public record Constant() implements Expr {
        @Override
        public double evaluate() {
            return 0;
        }
    }
//    public record Negate implements Expr {}
//    public record Exponent implements Expr {}
//    public record Addition implements Expr {}
//    public record Multiplication implements Expr {}
}
