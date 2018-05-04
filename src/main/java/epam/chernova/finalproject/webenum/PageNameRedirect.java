package epam.chernova.finalproject.webenum;

public enum PageNameRedirect {
    INDEX("/epam.by/index"),
    ERROR("/epam.by/error"),
    MENU("/epam.by/menu");

    private String path;

    PageNameRedirect(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
