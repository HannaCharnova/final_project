package epam.chernova.finalproject.command;

public enum CommandName {
    WRONG_REQUEST("all"),
    SIGN_IN("all"),
    SIGN_UP("all"),
    INDEX("all"),
    CHANGE_LOCALE("all"),
    FIND_BY_TYPE("all"),
    SIGN_OUT("all"),
    ADD_PRODUCT_BASKET("client"),
    REMOVE_PRODUCT_BASKET("client"),
    SHOW_ORDER_CLIENT("client"),
    PAY_FOR_ORDER("client"),
    CLIENT_PROFILE("client"),
    DELETE_PRODUCT("admin"),
    ADD_PRODUCT("admin"),
    SHOW_CLIENT("admin"),
    BAN_CLIENT("admin"),
    SHOW_ADMIN("admin"),
    DELETE_ADMIN("admin"),
    ADD_ADMIN("admin"),
    SHOW_ORDER_ADMIN("admin"),
    EDIT_PROFILE("client"),
    CHANGE_PASSWORD("all"),
    ADD_ACCOUNT("client"),
    DELETE_ACCOUNT("client"),
    EDIT_PRODUCT("admin"),
    CLOSE_ORDER("admin"),
    ADD_REVIEW("client"),
    SHOW_REVIEWS("all");

    private String role;

    CommandName(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

}
