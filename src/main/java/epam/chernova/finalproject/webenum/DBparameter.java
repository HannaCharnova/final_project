package epam.chernova.finalproject.webenum;

public enum DBparameter {
    DRIVER("db.driver"),
    USER("db.user"),
    PASSWORD("db.password"),
    URL("db.url"),
    POOL_SIZE("db.poolsize"),
    WAITING_TIME("db.waitingtime");

    private String value;

    DBparameter(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
