package edu.project3.io.write;

import edu.project3.io.read.IllegalConfigException;

public enum Extension {
    MARKDOWN("md"),
    ADOC("adoc"),
    TXT("txt");

    private final String ext;

    Extension(String extension) {
        this.ext = extension;
    }

    public String getExt() {
        return ext;
    }

    public static Extension parse(String extentionName) throws IllegalConfigException {
        for (Extension extension : Extension.values()) {
            if (extension.name().toLowerCase().equals(extentionName)) {
                return extension;
            }
        }
        throw new IllegalConfigException("Неверное значение формата вывода");
    }
}
