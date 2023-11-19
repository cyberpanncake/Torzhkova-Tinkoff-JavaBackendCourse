package edu.project3.io.write.table;

import java.util.Arrays;

public class MarkdownTablePrinter extends TablePrinter {
    @Override
    protected String printFirstLine(int[] lengths) {
        return "";
    }

    @Override
    protected String printLastLine(int[] lengths) {
        return "";
    }

    @Override
    protected String printRow(int[] lengths, String[] row, boolean notLast, boolean isHeader) {
        StringBuilder result = new StringBuilder();
        String[][] lines = rowToLines(lengths, row);
        for (String[] strings : lines) {
            result.append("|").append(String.join("|", strings)).append("|\n");
        }
        if (isHeader) {
            String line = String.join("|:", Arrays.stream(lengths)
                .mapToObj(l -> ("-").repeat(l + 1))
                .toList());
            result.append("|:").append(line).append("|\n");
        }
        return result.toString();
    }
}
