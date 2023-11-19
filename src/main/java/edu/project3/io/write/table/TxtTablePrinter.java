package edu.project3.io.write.table;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class TxtTablePrinter extends TablePrinter {

    @Override
    public String printTable(Table table) {
        String[] headers = table.headers();
        List<String[]> rows = table.rows();
        StringBuilder result = new StringBuilder();
        int[] lengths = countLengths(headers, rows);
        result.append(printFirstLine(lengths));
        result.append(printRow(lengths, headers, !rows.isEmpty(), true));
        for (final String[] row : rows) {
            result.append(printRow(lengths, row, row != rows.getLast(), false));
        }
        result.append(printLastLine(lengths));
        return result.toString();
    }

    private int[] countLengths(String[] headers, List<String[]> rows) {
        int n = headers.length;
        int[] lengths = new int[n];
        for (int i = 0; i < n; i++) {
            final int ind = i;
            lengths[i] = Math.max(headers[i].length(), rows.stream()
                .map(r -> r[ind].length())
                .max(Comparator.naturalOrder())
                .orElse(0));
        }
        return lengths;
    }

    private String printFirstLine(int[] lengths) {
        return "┌" + String.join("┬", Arrays.stream(lengths)
                .mapToObj(l -> "─".repeat(l + 2))
                .toList()) + "┐\n";
    }

    private String printLastLine(int[] lengths) {
        return "└" + String.join("┴", Arrays.stream(lengths)
                .mapToObj(l -> "─".repeat(l + 2))
                .toList()) + "┘\n";
    }

    private String printRow(int[] lengths, String[] row, boolean notLast, boolean isHeader) {
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

    private String[][] rowToLines(int[] lengths, String[] row) {
        int n = lengths.length;
        String[][] tabRow = new String[n][];
        int maxL = 1;
        for (int i = 0; i < n; i++) {
            tabRow[i] = row[i].split("\n");
            maxL = Math.max(maxL, tabRow[i].length);
        }
        String[][] lines = new String[maxL][n];
        for (int i = 0; i < maxL; i++) {
            for (int j = 0; j < n; j++) {
                lines[i][j] = (" %-" + lengths[j] + "s ").formatted(tabRow[j].length > i ? tabRow[j][i] : "");
            }
        }
        return lines;
    }
}
