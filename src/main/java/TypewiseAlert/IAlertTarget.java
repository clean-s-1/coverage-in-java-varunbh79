package TypewiseAlert;

public interface IAlertTarget {

    String dispatchBreachMsgToTarget(BreachType breachType);

    default boolean verifyTemperatureBreach(BreachType breachType) {
        return breachType != BreachType.NORMAL;
    }
}
