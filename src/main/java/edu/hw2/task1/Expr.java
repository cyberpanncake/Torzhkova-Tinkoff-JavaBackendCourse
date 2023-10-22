package edu.hw2.task1;

public sealed interface Expr {

    double evaluate();

    record Constant(double value) implements Expr {
        @Override
        public String toString() {
            return "%.3f".formatted(value);
        }

        @Override
        public double evaluate() {
            return value;
        }
    }

    record Negate(Expr expr) implements Expr {
        @Override
        public String toString() {
            return ("- " + ExprUtils.getPattern(expr)).formatted(expr.toString());
        }

        @Override
        public double evaluate() {
            return -expr.evaluate();
        }
    }

    record Exponent(Expr expr, double power) implements Expr {
        @Override
        public String toString() {
            return (ExprUtils.getPattern(expr) + " ^ %.3f").formatted(expr.toString(), power);
        }

        @Override
        public double evaluate() {
            return Math.pow(expr.evaluate(), power);
        }
    }

    record Addition(Expr expr1, Expr expr2) implements Expr {
        @Override
        public String toString() {
            return (ExprUtils.getPattern(expr1) + " + " + ExprUtils.getPattern(expr2))
                .formatted(expr1.toString(), expr2.toString());
        }

        @Override
        public double evaluate() {
            return expr1.evaluate() + expr2.evaluate();
        }
    }

    record Multiplication(Expr expr1, Expr expr2) implements Expr {
        @Override
        public String toString() {
            return (ExprUtils.getPattern(expr1) + " * " + ExprUtils.getPattern(expr2))
                .formatted(expr1.toString(), expr2.toString());
        }

        @Override
        public double evaluate() {
            return expr1.evaluate() * expr2.evaluate();
        }
    }
}
