package TypewiseAlert;


public class AlertDispatcherFactory {


    public static IAlertTarget getRelevantDispatcherTarget(AlertTarget alertTarget) {

        if(AlertTarget.TO_CONTROLLER.equals(alertTarget)) {
            return new DeviceController();
        } else if(AlertTarget.TO_EMAIL.equals(alertTarget)) {
            return new EmailDispatcher();
        }
       return null;
    }


}
