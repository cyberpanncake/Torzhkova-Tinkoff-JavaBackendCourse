package edu.project3.io.write;

import edu.project3.io.write.table.AdocTablePrinter;
import edu.project3.io.write.table.Table;

public class AdocLogInfoWriter extends LogInfoWriter {
    @Override
    protected String getExtension() {
        return Extension.ADOC.getName();
    }

    @Override
    protected String printTitle(String title) {
        return "==== " + title;
    }

    @Override
    protected String printTable(Table table) {
        return new AdocTablePrinter().printTable(table);
    }
}
