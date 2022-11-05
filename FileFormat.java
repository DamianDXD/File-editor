package files.actions;

public enum FileFormat {
    TXT(".txt"), AWL(".awl"), DOC(".doc");

    private String format;

    FileFormat(String format) {
        this.format = format;
    }

    public String getFormat() {
        return format;
    }
}
