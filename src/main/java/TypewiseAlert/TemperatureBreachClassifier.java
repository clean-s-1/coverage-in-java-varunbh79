package TypewiseAlert;


public class TemperatureBreachClassifier {

    public  BreachType inferBreach(double value, double lowerLimit, double upperLimit) {

        if (value < lowerLimit) {
            return BreachType.TOO_LOW;
        } else if (value > upperLimit) {
            return BreachType.TOO_HIGH;
        }
        return BreachType.NORMAL;
    }

}