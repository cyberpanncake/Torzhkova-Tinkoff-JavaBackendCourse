package edu.hw5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task6 {

    private Task6() {
    }

    public static boolean checkSubstring(String string, String substring) {
        if (string == null || substring == null) {
            throw new IllegalArgumentException("Входные строки не должны быть null");
        }
        Pattern pattern = Pattern.compile(String.join(".*", substring.split("")));
        Matcher matcher = pattern.matcher(string);
        return matcher.find();
    }
}
