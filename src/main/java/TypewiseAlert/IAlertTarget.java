package TypewiseAlert;

public interface IAlertTarget {

    String dispatchBreachMsgToTarget(BreachType breachType);

    boolean verifyTemperatureBreach(BreachType breachType);
}
