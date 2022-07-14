package TypewiseAlert;

public class TypewiseAlert 
{
    private static BreachType classifyTemperatureBreach(CoolingType coolingType, double tempInput) {
        Integer lowerLimit = coolingType.getLowTemp();
        Integer upperLimit = coolingType.getHighTemp();
        TemperatureBreachClassifier temperatureBreachClassifier = new TemperatureBreachClassifier();
        return temperatureBreachClassifier.inferBreach(tempInput,lowerLimit,upperLimit);
    }

    public String sendAlert(AlertTarget alertTarget, CoolingType coolingType, double inputTemp) {
        BreachType breachType = classifyTemperatureBreach(coolingType, inputTemp);
        IAlertTarget target = AlertDispatcherFactory.getRelevantDispatcherTarget(alertTarget);
        if(target != null) {
          return target.dispatchBreachMsgToTarget(breachType);
        }
        return  null;
    }

}
