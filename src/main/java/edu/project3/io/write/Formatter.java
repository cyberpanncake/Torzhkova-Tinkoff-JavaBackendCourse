package edu.project3.io.write;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.format.DateTimeFormatter;

public class Formatter {
    public static final DecimalFormat INT;

    static {
        INT = new DecimalFormat();
        DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance();
        symbols.setGroupingSeparator('_');
        INT.setDecimalFormatSymbols(symbols);
    }

    public static final DecimalFormat FLOAT;

    static {
        FLOAT = new DecimalFormat();
        DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance();
        symbols.setGroupingSeparator('_');
        symbols.setDecimalSeparator(',');
        FLOAT.setDecimalFormatSymbols(symbols);
        FLOAT.setMaximumFractionDigits(2);
    }

    public static final DateTimeFormatter DATETIME = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");

    public static final DateTimeFormatter FILE_DATE = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");

    private Formatter() {
    }
}
