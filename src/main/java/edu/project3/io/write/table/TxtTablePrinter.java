package edu.project3.io.write.table;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class TxtTablePrinter extends TablePrinter {

    @Override
    protected String printFirstLine(int[] lengths) {
        return "┌" + String.join("┬", Arrays.stream(lengths)
                .mapToObj(l -> "─".repeat(l + 2))
                .toList()) + "┐\n";
    }

    @Override
    protected String printLastLine(int[] lengths) {
        return "└" + String.join("┴", Arrays.stream(lengths)
                .mapToObj(l -> "─".repeat(l + 2))
                .toList()) + "┘\n";
    }

    @Override
    protected String printRow(int[] lengths, String[] row, boolean notLast, boolean isHeader) {
        StringBuilder result = new StringBuilder();
        String[][] lines = rowToLines(lengths, row);
        for (String[] strings : lines) {
            result.append("│").append(String.join("│", strings)).append("│\n");
        }
        if (notLast) {
            String line = String.join(isHeader ? "╪" : "┼", Arrays.stream(lengths)
                .mapToObj(l -> (isHeader ? "═" : "─").repeat(l + 2))
                .toList());
            result.append(isHeader ? "╞" : "├").append(line).append(isHeader ? "╡\n" : "┤\n");
        }
        return result.toString();
    }
}
