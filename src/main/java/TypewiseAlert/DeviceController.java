package TypewiseAlert;

public class DeviceController implements IAlertTarget{

    @Override
    public String dispatchBreachMsgToTarget(BreachType breachType) {
        if(verifyTemperatureBreach(breachType)) {
            System.out.println("No Breach identified to report to Controller");
            return breachType.getBreachMsg();
        }
        int header = 0xfeed;
        String breachMsg = breachType.getBreachMsg();
        String targetMsg = String.format("%s : %s",header,breachMsg);
        System.out.println(targetMsg);
        return targetMsg;
    }

    @Override
    public boolean verifyTemperatureBreach(BreachType breachType) {
        return breachType == BreachType.NORMAL;
    }
}
