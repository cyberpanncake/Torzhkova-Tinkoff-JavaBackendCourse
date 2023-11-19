package edu.project3.io.write;

import edu.project3.io.read.IllegalConfigException;

public enum Extension {
    MARKDOWN("md"),
    ADOC("adoc"),
    TXT("txt");

    private final String name;

    Extension(String extension) {
        this.name = extension;
    }

    public String getName() {
        return name;
    }

    public static Extension parse(String extentionName) throws IllegalConfigException {
        for (Extension extension : Extension.values()) {
            if (extension.name.toLowerCase().equals(extentionName)) {
                return extension;
            }
        }
        throw new IllegalConfigException("Неверное значение формата вывода");
    }
}
