package edu.project3.io.write.table;

import java.util.List;

public record Table(String[] headers, List<String[]> rows) {
}
