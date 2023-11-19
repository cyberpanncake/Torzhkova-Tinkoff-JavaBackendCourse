package edu.project3.io.write;

import edu.project3.io.write.table.MarkdownTablePrinter;
import edu.project3.io.write.table.Table;

public class MarkdownLogInfoWriter extends LogInfoWriter {
    @Override
    protected String getExtension() {
        return Extension.MARKDOWN.getName();
    }

    @Override
    protected String printTitle(String title) {
        return "#### " + title;
    }

    @Override
    protected String printTable(Table table) {
        return new MarkdownTablePrinter().printTable(table);
    }
}
