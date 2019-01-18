package helper.enums;

public enum ServiceAction {

    ADD("up"),
    REMOVE("down");

    private String action;

    ServiceAction(String action) {
        this.action = action;
    }

    public String getAction() {
        return action;
    }
}