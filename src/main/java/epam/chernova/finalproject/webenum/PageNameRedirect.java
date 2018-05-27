package epam.chernova.finalproject.webenum;

public enum PageNameRedirect {
    INDEX("/epam.by/index"),
    ERROR("/epam.by/error"),
    FIND_BY_TYPE("/epam.by/find_by_type"),
    ORDERS_CLIENT("/epam.by/show_order_client"),
    ORDERS_ADMIN("/epam.by/show_order_admin"),
    CLIENTS("/epam.by/show_client"),
    ADMINS("/epam.by/show_admin"),
    PROFILE("/epam.by/client_profile"),
    REVIEWS("/epam.by/show_reviews");

    private String path;

    PageNameRedirect(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
