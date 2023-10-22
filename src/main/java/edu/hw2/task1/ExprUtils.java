package edu.hw2.task1;

public class ExprUtils {
    private ExprUtils() {
    }

    public static String getPattern(Expr expr) {
        if (expr instanceof Expr.Constant && ((Expr.Constant) expr).value() >= 0) {
            return "%s";
        } else {
            return "(%s)";
        }
    }
}
