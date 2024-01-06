package edu.project3.io.write;

import edu.project3.io.write.table.Table;
import edu.project3.io.write.table.TxtTablePrinter;

public class TxtLogInfoWriter extends LogInfoWriter {
    @Override
    protected String getExtension() {
        return Extension.TXT.getExt();
    }

    @Override
    protected String printTitle(String title) {
        return title;
    }

    @Override
    protected String printTable(Table table) {
        return new TxtTablePrinter().printTable(table);
    }
}
