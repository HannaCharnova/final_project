package epam.chernova.finalproject.webenum;

public enum PageName {
    INDEX("/front/jsp/index.jsp"),
    ERROR("/front/jsp/error.jsp"),
    PRODUCTS("/front/jsp/common/product.jsp");

    private String path;

    PageName(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
