package epam.chernova.finalproject.webenum;

public enum PageNameRedirect {
    INDEX("/epam.by/index"),
    ERROR("/epam.by/error"),
    FIND_BY_TYPE("/epam.by/find_by_type"),
    ORDERS("/epam.by/show_order_client"),
    CLIENTS("/epam.by/show_client"),
    ADMINS("/epam.by/show_admin");

    private String path;

    PageNameRedirect(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
