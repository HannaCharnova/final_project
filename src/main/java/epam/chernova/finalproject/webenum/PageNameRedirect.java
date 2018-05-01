package epam.chernova.finalproject.webenum;

public enum PageNameRedirect {
    INDEX("/epam.by/index"),ERROR("/front/jsp/error.jsp");

    private String path;

    PageNameRedirect(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
