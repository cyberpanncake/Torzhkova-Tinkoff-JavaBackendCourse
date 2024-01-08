package edu.project3.log;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogRowMapper {
    private static final String LOG_PATTERN =
        "^(.*?) - (.*?) \\Q[\\E(.+)\\Q]\\E \"([A-Z]+)"
        + " ([-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|])"
        + " (.*?)\" (\\d{3}) (\\d+) \"(.*?)\" \"(.*?)\"(?: \"(.*?)\")?$";
    private static final DateTimeFormatter DATE_TIME_FORMAT
        = DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ss Z")
        .localizedBy(Locale.US);

    private LogRowMapper() {
    }

    @SuppressWarnings("MagicNumber")
    public static Log map(String row) throws IllegalLogException {
        try {
            Pattern pattern = Pattern.compile(LOG_PATTERN);
            Matcher matcher = pattern.matcher(row);
            if (matcher.find()) {
                return new Log(
                    matcher.group(1),
                    matcher.group(2),
                    LocalDateTime.parse(matcher.group(3), DATE_TIME_FORMAT),
                    matcher.group(4),
                    matcher.group(5),
                    matcher.group(6),
                    HttpResponse.toResponse(Integer.parseInt(matcher.group(7))),
                    Long.parseLong(matcher.group(8)),
                    matcher.group(9),
                    matcher.group(10),
                    matcher.group(11));
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            throw new IllegalLogException("Неверный формат строки лога");
        }
    }
}
