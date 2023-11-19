package edu.project3;

import edu.project3.io.read.Config;
import edu.project3.io.read.IllegalConfigException;
import edu.project3.io.write.Extension;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class ConfigTest {

    @Test
    void parseArgsTest() throws IllegalConfigException {
        String[] args = new String[] {
            "--path", "\"file.txt\"",
            "--from", "2023-08-31",
            "--to", "2023-09-01",
            "--format", "markdown"
        };
        Config expected = new Config("\"file.txt\"", LocalDate.parse("2023-08-31"), LocalDate.parse("2023-09-01"), Extension.MARKDOWN);
        Config actual = Config.parseArgs(args);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void parseArgsExceptionTest() throws IllegalConfigException {
        String[] args = new String[] { "--path" };
        Assertions.assertThrows(IllegalConfigException.class, () -> Config.parseArgs(args));
    }
}
