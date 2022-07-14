package TypewiseAlert;

public enum BreachType {
    NORMAL(null),
    TOO_LOW("Hi, the temperature is too low"),
    TOO_HIGH("Hi, the temperature is too high");

    private String breachMsg;

    BreachType(String breachMsg) {
        this.breachMsg = breachMsg;
    }

    public String getBreachMsg() {
        return breachMsg;
    }

    public void setBreachMsg(String breachMsg) {
        this.breachMsg = breachMsg;
    }
}
