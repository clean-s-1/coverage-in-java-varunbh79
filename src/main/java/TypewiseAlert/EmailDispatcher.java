package TypewiseAlert;

public class EmailDispatcher implements IAlertTarget{


    @Override
    public String dispatchBreachMsgToTarget(BreachType breachType) {
        if(verifyTemperatureBreach(breachType)) {
            System.out.println("No Breach identified to send as email");
            return breachType.getBreachMsg();
        }
        String recepient = "a.b@c.com";
        String breachMsgType = breachType.getBreachMsg();
        String targetMsg = String.format("To: %s\n %s",recepient,breachMsgType);
        System.out.println(targetMsg);
        return targetMsg;
    }



}
