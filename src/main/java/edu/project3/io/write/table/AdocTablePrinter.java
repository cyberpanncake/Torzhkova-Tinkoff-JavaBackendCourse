package edu.project3.io.write.table;

public class AdocTablePrinter extends TablePrinter {
    private static final String TABLE_LINE = "|===\n";

    @Override
    protected String printFirstLine(int[] lengths) {
        return "";
    }

    @Override
    protected String printLastLine(int[] lengths) {
        return TABLE_LINE;
    }

    @Override
    protected String printRow(int[] lengths, String[] row, boolean notLast, boolean isHeader) {
        StringBuilder result = new StringBuilder();
        if (isHeader) {
            String cols = String.join(",", "<".repeat(row.length).split(""));
            result.append("[options=\"header\", cols=\"").append(cols).append("\"]\n")
                .append(TABLE_LINE);
        }
        String[][] lines = rowToLines(lengths, row);
        for (String[] strings : lines) {
            result.append("|").append(String.join("|", strings)).append("\n");
        }
        return result.toString();
    }
}
