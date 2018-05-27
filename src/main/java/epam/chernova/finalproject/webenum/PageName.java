package epam.chernova.finalproject.webenum;

public enum PageName {
    INDEX("/front/jsp/index.jsp"),
    ERROR("/front/jsp/error.jsp"),
    PRODUCTS("/front/jsp/common/product.jsp"),
    ORDERS("/front/jsp/common/order.jsp"),
    PROFILE("/front/jsp/client/profile.jsp"),
    CLIENTS("/front/jsp/admin/clientlist.jsp"),
    ADMINS("/front/jsp/admin/adminlist.jsp"),
    REVIEWS("/front/jsp/common/reviews.jsp");


    private String path;

    PageName(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
