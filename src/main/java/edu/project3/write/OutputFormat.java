package edu.project3.write;

public enum OutputFormat {
    MARKDOWN("md"),
    ADOC("adoc");

    private final String extention;

    OutputFormat(String extention) {
        this.extention = extention;
    }

    public String getExtention() {
        return extention;
    }
}
