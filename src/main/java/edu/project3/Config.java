package edu.project3;

import edu.project3.write.OutputFormat;
import java.time.LocalDateTime;
import java.util.List;

public record Config(
    List<String> paths,
    LocalDateTime startDate,
    LocalDateTime endDate,
    OutputFormat outputFormat
) {
}
